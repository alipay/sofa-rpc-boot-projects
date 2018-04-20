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
package com.alipay.sofa.rpc.boot.container;

import com.alipay.sofa.rpc.boot.common.RpcThreadPoolMonitor;
import com.alipay.sofa.rpc.boot.common.SofaBootRpcRuntimeException;
import com.alipay.sofa.rpc.boot.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.boot.config.SofaBootRpcProperties;
import com.alipay.sofa.rpc.boot.log.SofaBootRpcLoggerFactory;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.server.Server;
import com.alipay.sofa.rpc.server.ServerFactory;
import com.alipay.sofa.rpc.server.bolt.BoltServer;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ServiceConfig 工厂
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class ServerConfigContainer implements InitializingBean {

    private static final Logger   LOGGER     = SofaBootRpcLoggerFactory.getLogger(ServerConfigContainer.class);

    private SofaBootRpcProperties sofaBootRpcProperties;
    /**
     * bolt ServerConfig
     */
    private volatile ServerConfig boltServerConfig;
    private final Object          BOLT_LOCK  = new Object();

    /**
     * rest ServerConfig
     */
    private volatile ServerConfig restServerConfig;
    private final Object          REST_LOCK  = new Object();

    /**
     * dubbo ServerConfig
     */
    private volatile ServerConfig dubboServerConfig;
    private final Object          DUBBO_LOCK = new Object();

    public ServerConfigContainer(SofaBootRpcProperties sofaBootRpcProperties) {
        this.sofaBootRpcProperties = sofaBootRpcProperties;
    }

    /**
     * 是否需要开启Server
     *
     * @return 是否需要开启
     */
    public boolean isNeedStart() {
        return boltServerConfig != null || restServerConfig != null || dubboServerConfig != null;
    }

    /**
     * 开启所有 ServerConfig 对应的 Server
     */
    public void startServers() {
        if (boltServerConfig != null) {
            boltServerConfig.buildIfAbsent().start();

            BoltServer server = (BoltServer) boltServerConfig.getServer();
            ThreadPoolExecutor threadPoolExecutor = server.getBizThreadPool();

            if (threadPoolExecutor != null) {
                new RpcThreadPoolMonitor(threadPoolExecutor).start();
            } else {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("the business threadpool can not be get");
                }
            }
        }

        if (restServerConfig != null) {
            restServerConfig.buildIfAbsent().start();
        }
    }

    /**
     * 获取 ServerConfig
     *
     * @param protocol 协议
     * @return the ServerConfig
     */
    public ServerConfig getServerConfig(String protocol) {

        if (protocol.equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_BOLT)) {
            if (boltServerConfig == null) {
                synchronized (BOLT_LOCK) {
                    if (boltServerConfig == null) {
                        boltServerConfig = createBoltServerConfig();
                    }
                }
            }

            return boltServerConfig;
        } else if (protocol.equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_REST)) {
            if (restServerConfig == null) {
                synchronized (REST_LOCK) {
                    if (restServerConfig == null) {
                        restServerConfig = createRestServerConfig();
                    }
                }
            }

            return restServerConfig;
        } else if (protocol.equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_DUBBO)) {

            if (dubboServerConfig == null) {
                synchronized (DUBBO_LOCK) {
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

    /**
     * 获取 Server
     *
     * @param protocol 协议
     * @return the Server
     */
    public Server getServer(String protocol) {
        return ServerFactory.getServer(getServerConfig(protocol));
    }

    /**
     * 创建 bolt ServerConfig。rest 的 配置不需要外层 starter 设置默认值。
     *
     * @return Bolt 的服务端配置信息
     */
    ServerConfig createBoltServerConfig() {
        String portStr = sofaBootRpcProperties.getBoltPort();
        String ioThreadCountStr = sofaBootRpcProperties.getBoltIoThreadCount();
        String executorThreadCountStr = sofaBootRpcProperties.getBoltExecutorThreadCount();
        String acceptsCountStr = sofaBootRpcProperties.getBoltAcceptsCount();

        ServerConfig serverConfig = new ServerConfig();

        if (StringUtils.hasText(portStr)) {
            serverConfig.setPort(Integer.parseInt(portStr));
        } else {
            serverConfig.setPort(12200);
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
     * 创建 rest ServerConfig。rest 的 配置需要外层 starter 设置默认值。
     *
     * @return rest ServerConfig
     */
    ServerConfig createRestServerConfig() {
        String hostName = sofaBootRpcProperties.getRestHostname();
        String portStr = sofaBootRpcProperties.getRestPort();
        String ioThreadCountStr = sofaBootRpcProperties.getRestIoThreadCount();
        String executorThreadCountStr = sofaBootRpcProperties.getRestExecutorThreadCount();
        String maxRequestSizeStr = sofaBootRpcProperties.getRestMaxRequestSize();
        String telnetStr = sofaBootRpcProperties.getRestTelnet();
        String daemonStr = sofaBootRpcProperties.getRestDaemon();

        int port;
        int ioThreadCount;
        int executorThreadCount;
        int maxRequestSize;
        boolean telnet;
        boolean daemon;

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
     * 创建 dubbo ServerConfig。会设置 Dubbo 的默认端口，其余配置不会由外层 Starter 设置默认值。
     *
     * @return dubbo ServerConfig
     */
    ServerConfig createDubboServerConfig() {
        String portStr = sofaBootRpcProperties.getDubboPort();
        String ioThreadCountStr = sofaBootRpcProperties.getDubboIoThreadCount();
        String executorThreadCountStr = sofaBootRpcProperties.getDubboExecutorThreadCount();
        String acceptsCountStr = sofaBootRpcProperties.getDubboAcceptsCount();

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

    /**
     * 释放所有 ServerConfig 对应的资源，并移除所有的 ServerConfig。
     */
    public void closeAllServer() {
        if (boltServerConfig != null) {
            boltServerConfig.destroy();
            boltServerConfig = null;
        }

        if (restServerConfig != null) {
            restServerConfig.destroy();
            restServerConfig = null;
        }

        if (dubboServerConfig != null) {
            dubboServerConfig.destroy();
            dubboServerConfig = null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}