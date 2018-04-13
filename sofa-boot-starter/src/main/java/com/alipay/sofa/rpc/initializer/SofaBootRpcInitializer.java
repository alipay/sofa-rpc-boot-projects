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
package com.alipay.sofa.rpc.initializer;

import com.alipay.sofa.common.log.Constants;
import com.alipay.sofa.infra.log.space.SofaBootLogSpaceIsolationInit;
import com.alipay.sofa.rpc.common.SofaBootRpcRuntimeException;
import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.log.RpcLoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * Initialize for SOFABoot RPC
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class SofaBootRpcInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (SofaBootRpcConfig.getEnvironment() == null) {
            SofaBootRpcConfig.setEnvironment(applicationContext.getEnvironment());
        }

        checkAppName();

        initLog(applicationContext.getEnvironment());

    }

    private void checkAppName() {
        String appName = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.APP_NAME);
        if (!StringUtils.hasText(appName)) {
            throw new SofaBootRpcRuntimeException("Please add '" + SofaBootRpcConfigConstants.APP_NAME +
                "' in application.properties");
        }

    }

    private void initLog(Environment environment) {
        //log level
        String logLevelKey = Constants.LOG_LEVEL_PREFIX + RpcLoggerFactory.REST_LOG_SPACE;
        SofaBootLogSpaceIsolationInit.initSofaBootLogger(environment, logLevelKey);
    }
}
