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
package com.alipay.sofa.rpc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class SofaBootRpcConfig {
    private static final Logger logger      = LoggerFactory.getLogger(SofaBootRpcConfig.class);

    private static Environment  environment = null;

    public static String getProperty(String key) {

        if (environment != null) {
            return environment.getProperty(key);
        } else {
            return null;
        }

    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static void setEnvironment(Environment environment) {
        SofaBootRpcConfig.environment = environment;
    }

    /**
     * Reads the value of the specified key from the system properties and configuration files.
     * If this value exists in the system property, the value of the system attribute is preferred.
     * It will try to read the value in the key naming mode of XXX.XXX.XXX
     * If I do not try xxx_xxx_xxx, It will read it.
     * The mapping of the two naming methods is defined {@link SofaBootRpcConfigMapping}
     * @param key
     * @return
     */
    public static String getPropertyAllCircumstances(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }

        String value = System.getProperty(key);
        if (!StringUtils.hasText(value)) {
            value = System.getProperty(SofaBootRpcConfigMapping.getUnderlineKey(key));
        }
        if (!StringUtils.hasText(value)) {
            value = SofaBootRpcConfig.getProperty(key);
        }
        if (!StringUtils.hasText(value)) {
            value = SofaBootRpcConfig.getProperty(SofaBootRpcConfigMapping.getUnderlineKey(key));
        }

        return value;
    }

}