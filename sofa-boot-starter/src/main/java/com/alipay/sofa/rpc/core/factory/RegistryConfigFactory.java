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
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.config.ZookeeperConfiger;
import com.alipay.sofa.rpc.registry.Registry;
import com.alipay.sofa.rpc.registry.RegistryFactory;

/**
 * Factory of registry config . Encapsulates registry related RPC API programming and maintain corresponding singleton.
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class RegistryConfigFactory {

    private static volatile RegistryConfig loaclRegistryConfig;

    private static volatile RegistryConfig zookeeperRegistryConfig;

    public static Registry getRegistry() {
        Registry registry = RegistryFactory.getRegistry(getRegistryConfig());
        registry.init();
        registry.start();

        return registry;
    }

    public static RegistryConfig getRegistryConfig() throws SofaBootRpcRuntimeException {

        String registryConfig = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL);

        if (StringUtils.isBlank(registryConfig) ||
            registryConfig.startsWith(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL_LOCAL)) {

            if (loaclRegistryConfig == null) {
                synchronized (RegistryConfigFactory.class) {
                    if (loaclRegistryConfig == null) {
                        loaclRegistryConfig = createLoaclRegistryConfig();
                    }
                }
            }

            return loaclRegistryConfig;
        } else if (registryConfig.startsWith(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL_ZOOKEEPER)) {

            if (zookeeperRegistryConfig == null) {
                synchronized (RegistryConfigFactory.class) {
                    if (zookeeperRegistryConfig == null) {
                        zookeeperRegistryConfig = createZookeeperRegistryConfig();
                    }
                }
            }

            return zookeeperRegistryConfig;
        } else {
            throw new SofaBootRpcRuntimeException("protocl[" + registryConfig + "] is not supported");
        }
    }

    private static RegistryConfig createLoaclRegistryConfig() {

        String filePath = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.REGISTRY_FILE_PATH);
        if (StringUtils.isBlank(filePath)) {
            filePath = SofaBootRpcConfigConstants.REGISTRY_FILE_PATH_DEFAULT;
        }

        RegistryConfig registryConfig = new RegistryConfig()
            .setFile(filePath)
            .setProtocol(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL_LOCAL);

        return registryConfig;

    }

    private static RegistryConfig createZookeeperRegistryConfig() {
        ZookeeperConfiger.parseConfig();

        String address = ZookeeperConfiger.getAddress();
        String filePath = ZookeeperConfiger.getFile();

        RegistryConfig registryConfig = new RegistryConfig()
            .setAddress(address)
            .setFile(filePath)
            .setProtocol(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL_ZOOKEEPER);

        return registryConfig;
    }

    public static void removeAllRegistryConfig() {
        loaclRegistryConfig = null;
        zookeeperRegistryConfig = null;
    }
}