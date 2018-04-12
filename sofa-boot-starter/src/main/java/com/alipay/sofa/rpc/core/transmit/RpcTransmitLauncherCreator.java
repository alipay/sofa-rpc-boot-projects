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
package com.alipay.sofa.rpc.core.transmit;

import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.core.factory.RegistryConfigFactory;
import com.alipay.sofa.rpc.log.RpcLoggerFactory;
import com.alipay.sofa.rpc.transmit.TransmitConfig;
import com.alipay.sofa.rpc.transmit.TransmitConfigHelper;
import com.alipay.sofa.rpc.transmit.TransmitLauncher;
import com.alipay.sofa.rpc.transmit.TransmitLauncherFactory;
import com.alipay.sofa.rpc.transmit.registry.TransmitRegistry;
import com.alipay.sofa.rpc.transmit.registry.TransmitRegistryFactory;
import org.slf4j.Logger;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class RpcTransmitLauncherCreator {

    private static final Logger logger = RpcLoggerFactory.getLogger(RpcTransmitLauncherCreator.class);

    public TransmitLauncher createTransmitLauncher() {

        String transmitConfiger = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.CORE_PROXY_URL);
        if (StringUtils.isBlank(transmitConfiger)) {
            return null;
        }

        String registryProtocol = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL);
        if (StringUtils.isBlank(registryProtocol) ||
            !registryProtocol.equalsIgnoreCase(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL_ZOOKEEPER)) {
            logger.error("transmit is no effective. because protocol is missed or not supported.");
            return null;
        }

        RegistryConfig registryConfig = RegistryConfigFactory.getRegistryConfig();
        TransmitRegistry transmitRegistry = TransmitRegistryFactory.getIpTransmitRegistry(registryConfig);

        TransmitLauncher transmitLauncher = TransmitLauncherFactory.getTransmitLauncher("ip");
        //transmitLauncher.setRegistry(transmitRegistry);

        String appName = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.APP_NAME);
        TransmitConfig transmitConfig = TransmitConfigHelper.parseTransmitConfig(appName, transmitConfiger);
        transmitLauncher.load(appName, transmitConfig);

        return transmitLauncher;
    }

}