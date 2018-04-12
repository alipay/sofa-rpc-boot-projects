/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.core.factory;

import com.alipay.sofa.rpc.common.SofaBootRpcRuntimeException;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.core.monitor.RpcThreadPoolMonitor;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.server.Server;
import com.alipay.sofa.rpc.server.ServerFactory;
import com.alipay.sofa.rpc.server.bolt.BoltServer;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class ServerConfigFactory {

    private static final Logger          logger    = LoggerFactory.getLogger(ServerConfigFactory.class);

    private volatile static ServerConfig boltServerConfig;
    private final static Object          boltLock  = new Object();

    private volatile static ServerConfig restServerConfig;
    private final static Object          restLock  = new Object();

    private volatile static ServerConfig dubboServerConfig;
    private final static Object          dubboLock = new Object();

    public static boolean isNeedStart() {
        if (boltServerConfig != null || restServerConfig != null || dubboServerConfig != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void startServers() {
        if (boltServerConfig != null) {
            boltServerConfig.buildIfAbsent().start();

            BoltServer server = (BoltServer) boltServerConfig.getServer();
            ThreadPoolExecutor threadPoolExecutor = server.getBizThreadPool();

            if (threadPoolExecutor != null) {
                new RpcThreadPoolMonitor(threadPoolExecutor).start();
            } else {
                logger.warn("the business threadpool can not be get");
            }
        }

        if (restServerConfig != null) {
            restServerConfig.buildIfAbsent().start();
        }

    }

    public static ServerConfig getServerConfig(String protocol) {

        if (protocol.equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_BOLT)) {
            if (boltServerConfig == null) {
                synchronized (boltLock) {
                    if (boltServerConfig == null) {
                        boltServerConfig = createBoltServerConfig();
                    }
                }
            }

            return boltServerConfig;
        } else if (protocol.equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_REST)) {
            if (restServerConfig == null) {
                synchronized (restLock) {
                    if (restServerConfig == null) {
                        restServerConfig = createRestServerConfig();
                    }
                }
            }

            return restServerConfig;
        } else if (protocol.equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_DUBBO)) {

            if (dubboServerConfig == null) {
                synchronized (dubboLock) {
                    if (dubboServerConfig == null) {
                        dubboServerConfig = createDubboServerConfig();
                    }
                }
            }

            return dubboServerConfig;
        } else {
            throw new SofaBootRpcRuntimeException("protocol [" + protocol + "] is not supported");
        }

    }

    public static Server getServer(String protocol) {
        return ServerFactory.getServer(getServerConfig(protocol));
    }

    private static ServerConfig createBoltServerConfig() {
        String portStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.BOLT_PORT);
        String ioThreadCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.BOLT_IO_THREAD_COUNT);
        String executorThreadCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.BOLT_EXECUTOR_THREAD_COUNT);
        String acceptsCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.BOLT_ACCEPTS_COUNT);

        ServerConfig serverConfig = new ServerConfig();

        if (StringUtils.hasText(portStr)) {
            serverConfig.setPort(Integer.parseInt(portStr));
        }

        if (StringUtils.hasText(ioThreadCountStr)) {
            serverConfig.setIoThreads(Integer.parseInt(ioThreadCountStr));
        }

        if (StringUtils.hasText(executorThreadCountStr)) {
            serverConfig.setMaxThreads(Integer.parseInt(executorThreadCountStr));
        }

        if (StringUtils.hasText(acceptsCountStr)) {
            serverConfig.setAccepts(Integer.parseInt(acceptsCountStr));
        }

        serverConfig.setAutoStart(false);
        return serverConfig.setProtocol(SofaBootRpcConfigConstants.RPC_PROTOCOL_BOLT);
    }

    /**
     * Rest requires starter to set default values.
     * @return
     */
    private static ServerConfig createRestServerConfig() {
        String hostName = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_HOSTNAME);
        String portStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_PORT);
        String ioThreadCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_IO_THREAD_COUNT);
        String executorThreadCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_EXECUTOR_THREAD_COUNT);
        String maxRequestSizeStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_MAX_REQUEST_SIZE);
        String telnetStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_TELNET);
        String daemonStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.REST_DAEMON);

        int port = 0;
        int ioThreadCount = 0;
        int executorThreadCount = 0;
        int maxRequestSize = 0;
        boolean telnet = false;
        boolean daemon = true;

        if (!StringUtils.hasText(hostName)) {
            hostName = null;
        }

        if (!StringUtils.hasText(portStr)) {
            port = SofaBootRpcConfigConstants.REST_PORT_DEFAULT;
        } else {
            port = Integer.parseInt(portStr);
        }

        if (!StringUtils.hasText(ioThreadCountStr)) {
            ioThreadCount = SofaBootRpcConfigConstants.REST_IO_THREAD_COUNT_DEFAULT;
        } else {
            ioThreadCount = Integer.parseInt(ioThreadCountStr);
        }

        if (!StringUtils.hasText(executorThreadCountStr)) {
            executorThreadCount = SofaBootRpcConfigConstants.REST_EXECUTOR_THREAD_COUNT_DEFAULT;
        } else {
            executorThreadCount = Integer.parseInt(executorThreadCountStr);
        }

        if (!StringUtils.hasText(maxRequestSizeStr)) {
            maxRequestSize = SofaBootRpcConfigConstants.REST_MAX_REQUEST_SIZE_DEFAULT;
        } else {
            maxRequestSize = Integer.parseInt(maxRequestSizeStr);
        }

        if (!StringUtils.hasText(telnetStr)) {
            telnet = SofaBootRpcConfigConstants.REST_TELNET_DEFAULT;
        } else {
            telnet = Boolean.parseBoolean(telnetStr);
        }

        if (!StringUtils.hasText(daemonStr)) {
            daemon = SofaBootRpcConfigConstants.REST_DAEMON_DEFAULT;
        } else {
            daemon = Boolean.parseBoolean(daemonStr);
        }

        ServerConfig serverConfig = new ServerConfig()
            .setBoundHost(hostName)
            .setPort(port)
            .setIoThreads(ioThreadCount)
            .setMaxThreads(executorThreadCount)
            .setPayload(maxRequestSize)
            .setTelnet(telnet)
            .setDaemon(daemon);

        serverConfig.setAutoStart(false);
        return serverConfig.setProtocol(SofaBootRpcConfigConstants.RPC_PROTOCOL_REST);
    }

    /**
     * Dubbo requires starter to set default values. At the moment, all but the ports are bolt defaults.
     * @return
     */
    private static ServerConfig createDubboServerConfig() {

        String portStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.DUBBO_PORT);
        String ioThreadCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.DUBBO_IO_THREAD_COUNT);
        String executorThreadCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.DUBBO_EXECUTOR_THREAD_COUNT);
        String acceptsCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.DUBBO_ACCEPTS_COUNT);

        ServerConfig serverConfig = new ServerConfig();

        if (StringUtils.hasText(portStr)) {
            serverConfig.setPort(Integer.parseInt(portStr));
        } else {
            serverConfig.setPort(SofaBootRpcConfigConstants.DUBBO_PORT_DEFAULT);
        }

        if (StringUtils.hasText(ioThreadCountStr)) {
            serverConfig.setIoThreads(Integer.parseInt(ioThreadCountStr));
        }

        if (StringUtils.hasText(executorThreadCountStr)) {
            serverConfig.setMaxThreads(Integer.parseInt(executorThreadCountStr));
        }

        if (StringUtils.hasText(acceptsCountStr)) {
            serverConfig.setAccepts(Integer.parseInt(acceptsCountStr));
        }

        serverConfig.setAutoStart(false);
        return serverConfig.setProtocol(SofaBootRpcConfigConstants.RPC_PROTOCOL_DUBBO);

    }

    public static void closeAllServer() {
        if (boltServerConfig != null) {
            boltServerConfig.destroy();
        }

        if (restServerConfig != null) {
            restServerConfig.destroy();
        }

        if (dubboServerConfig != null) {
            dubboServerConfig.destroy();
        }

    }
}