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
package com.alipay.sofa.rpc.core.container;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.rpc.register.binding.RpcBinding;
import com.alipay.sofa.rpc.registry.Registry;
import com.alipay.sofa.runtime.spi.binding.Contract;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Provider configuration holder. Responsible for programming interface cache.
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class ProviderConfigContainer {
    private static final Logger                                logger              = LoggerFactory
                                                                                       .getLogger(ProviderConfigContainer.class);

    private static boolean                                     allowPublish        = false;

    /***
     * RPC Service 缓存,用于根据这个元数据信息回溯 RPC ,如移除服务等
     */
    private static final ConcurrentMap<String, ProviderConfig> rpcServiceContainer = new ConcurrentHashMap<String, ProviderConfig>(
                                                                                       256);

    public static void addProviderConfig(String key, ProviderConfig providerConfig) {
        if (providerConfig != null) {
            if (rpcServiceContainer.containsKey(key)) {
                logger.warn("已经存在相同的服务及协议,key[" + key + "];protocol[" + providerConfig.getServer().get(0) + "]");
            } else {
                rpcServiceContainer.put(key, providerConfig);

            }
        }
    }

    public static ProviderConfig getProviderConfig(String key) {
        return rpcServiceContainer.get(key);
    }

    public static void removeProviderConfig(String key) {
        rpcServiceContainer.remove(key);
    }

    public static Collection<ProviderConfig> getAllProviderConfig() {
        return rpcServiceContainer.values();
    }

    public static void publishAllProviderConfig(Registry registry) {
        for (ProviderConfig providerConfig : ProviderConfigContainer.getAllProviderConfig()) {

            ServerConfig serverConfig = (ServerConfig) providerConfig.getServer().get(0);
            if (!serverConfig.getProtocol().equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_DUBBO)) {
                providerConfig.setRegister(true);
                registry.register(providerConfig);
            }

        }
    }

    public static void exportAllDubboProvideConfig() {
        for (ProviderConfig providerConfig : ProviderConfigContainer.getAllProviderConfig()) {

            ServerConfig serverConfig = (ServerConfig) providerConfig.getServer().get(0);
            if (serverConfig.getProtocol().equalsIgnoreCase(SofaBootRpcConfigConstants.RPC_PROTOCOL_DUBBO)) {
                providerConfig.setRegister(true);
                providerConfig.export();
            }
        }
    }

    public static void unExportAllProviderConfig() {
        for (ProviderConfig providerConfig : ProviderConfigContainer.getAllProviderConfig()) {
            providerConfig.unExport();
        }

    }

    public static boolean isAllowPublish() {
        return allowPublish;
    }

    public static void setAllowPublish(boolean allowPublish) {
        ProviderConfigContainer.allowPublish = allowPublish;
    }

    public static String createUniqueName(Contract contract, RpcBinding binding) {
        String uniqueId = "";
        String version = ":1.0";
        String protocol = "";
        if (StringUtils.hasText(contract.getUniqueId())) {
            uniqueId = ":" + contract.getUniqueId();
        }
        if (StringUtils.hasText(contract.getProperty("version"))) {
            version = ":" + contract.getProperty("version");
        }
        if (StringUtils.hasText(binding.getBindingType().getType())) { //dubbo can not merge to bolt
            protocol = ":" + binding.getBindingType().getType();
        }

        return new StringBuffer(contract.getInterfaceType().getName()).append(version)
            .append(uniqueId).append(protocol).toString();
    }

}
