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

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * zookeeper configuration of parsing abstractions.
 *
 * configuration format: rpc.registry.protocol=zookeeper://xxx:2181?k1=v1
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class ZookeeperConfiger {

    private static final String              FILE        = "file";

    private static String                    address;

    private static final Map<String, String> paramMap    = new HashMap<String, String>();

    private static boolean                   alreayParse = false;

    public static String getParamValue(String key) {
        if (StringUtils.hasText(key)) {
            return paramMap.get(key);
        } else {
            return null;
        }
    }

    public static String getFile() {
        String file = paramMap.get(FILE);

        if (!StringUtils.hasText(file)) {
            file = SofaBootRpcConfigConstants.REGISTRY_FILE_PATH_DEFAULT;
        }

        return file;

    }

    public static void parseConfig(String config) {
        if (StringUtils.hasText(config) && config.startsWith("zookeeper")) {

            String value = config.substring(12);

            if (!value.contains("?")) {
                address = value;
            } else {
                address = value.substring(0, value.lastIndexOf("?"));
                String paramString = value.substring(value.lastIndexOf("?") + 1);
                parseParam(paramString);

            }
        }
    }

    public static void parseConfig() {
        if (alreayParse == false) {
            String config = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL);
            parseConfig(config);

            alreayParse = true;
        }

    }

    private static void parseParam(String paramString) {
        if (paramString.contains("&")) {
            String[] paramSplit = paramString.split("&");
            for (String param : paramSplit) {
                parseKeyValue(param);
            }
        } else {
            parseKeyValue(paramString);
        }
    }

    private static void parseKeyValue(String kv) {
        String[] kvSplit = kv.split("=");
        String key = kvSplit[0];
        String value = kvSplit[1];
        paramMap.put(key, value);
    }

    /**
     * Getter method for property <tt>address</tt>.
     *
     * @return property value of address
     */
    public static String getAddress() {
        return address;
    }

    /**
     * Setter method for property <tt>address</tt>.
     *
     * @param address  value to be assigned to property address
     */
    public static void setAddress(String address) {
        ZookeeperConfiger.address = address;
    }
}