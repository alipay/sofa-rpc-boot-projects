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
package com.alipay.sofa.rpc.register.listener;

import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.core.container.ProviderConfigContainer;
import com.alipay.sofa.rpc.core.factory.RegistryConfigFactory;
import com.alipay.sofa.rpc.core.factory.ServerConfigFactory;
import com.alipay.sofa.rpc.core.transmit.RpcTransmitLauncherCreator;
import com.alipay.sofa.rpc.register.event.SofaBootRpcStartEvent;
import com.alipay.sofa.rpc.registry.Registry;
import com.alipay.sofa.rpc.transmit.TransmitLauncher;
import com.alipay.sofa.rpc.transmit.registry.TransmitRegistryFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用户监听RPC server export完成。准备启动server和注册服务
 * 
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
@Component
public class SofaBootRpcStartListener implements ApplicationListener<SofaBootRpcStartEvent> {

    @Override
    public void onApplicationEvent(SofaBootRpcStartEvent event) {

        //start server
        ServerConfigFactory.startServers();

        //init registry
        Registry registry = RegistryConfigFactory.getRegistry();

        //set allow all publish
        ProviderConfigContainer.setAllowPublish(true);

        //transmit
        TransmitLauncher transmitLauncher = new RpcTransmitLauncherCreator().createTransmitLauncher();
        if (transmitLauncher != null) {
            transmitLauncher.setRegistry(TransmitRegistryFactory.getIpTransmitRegistry(RegistryConfigFactory
                .getRegistryConfig()));
            transmitLauncher.startTransmit(SofaBootRpcConfig
                .getPropertyAllCircumstances(SofaBootRpcConfigConstants.APP_NAME));
        }

        //register registry
        ProviderConfigContainer.publishAllProviderConfig(registry);

        //export dubbo
        ProviderConfigContainer.exportAllDubboProvideConfig();

    }
}