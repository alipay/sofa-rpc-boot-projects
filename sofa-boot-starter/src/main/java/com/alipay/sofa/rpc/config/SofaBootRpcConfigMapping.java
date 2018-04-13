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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class SofaBootRpcConfigMapping {

    private static Map<String, String> configMap = new HashMap<String, String>();

    static {

        /** application */
        configMap.put(SofaBootRpcConfigConstants.APP_NAME, SofaBootRpcConfigConstants.APP_NAME_LINE);
        configMap.put(SofaBootRpcConfigConstants.LOG_PATH, SofaBootRpcConfigConstants.LOG_PATH_LINE);
        configMap.put(SofaBootRpcConfigConstants.CORE_PROXY_URL, SofaBootRpcConfigConstants.CORE_PROXY_URL_LINE);

        /** fault-tolerance */
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE,
            SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_DEGRADE_EFFECTIVE,
            SofaBootRpcConfigConstants.RPC_AFT_DEGRADE_EFFECTIVE_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_TIME_WINDOW,
            SofaBootRpcConfigConstants.RPC_AFT_TIME_WINDOW_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_LEAST_CALL_COUNT,
            SofaBootRpcConfigConstants.RPC_AFT_LEAST_CALL_COUNT_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_LEAST_WINDOW_EXCEPTION_RATE_MULTIPLE,
            SofaBootRpcConfigConstants.RPC_AFT_LEAST_WINDOW_EXCEPTION_RATE_MULTIPLE_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_WEIGHT_DEGRADE_RATE,
            SofaBootRpcConfigConstants.RPC_AFT_WEIGHT_DEGRADE_RATE_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_WEIGHT_RECOVER_RATE,
            SofaBootRpcConfigConstants.RPC_AFT_WEIGHT_RECOVER_RATE_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_DEGRADE_LEAST_WEIGHT,
            SofaBootRpcConfigConstants.RPC_AFT_DEGRADE_LEAST_WEIGHT_LINE);
        configMap.put(SofaBootRpcConfigConstants.RPC_AFT_DEGRADE_MAX_IP_COUNT,
            SofaBootRpcConfigConstants.RPC_AFT_DEGRADE_MAX_IP_COUNT_LINE);

        /** bolt */
        configMap.put(SofaBootRpcConfigConstants.BOLT_PORT, SofaBootRpcConfigConstants.BOLT_PORT_LINE);
        configMap.put(SofaBootRpcConfigConstants.BOLT_IO_THREAD_COUNT,
            SofaBootRpcConfigConstants.BOLT_IO_THREAD_COUNT_LINE);
        configMap.put(SofaBootRpcConfigConstants.BOLT_EXECUTOR_THREAD_COUNT,
            SofaBootRpcConfigConstants.BOLT_EXECUTOR_THREAD_COUNT_LINE);
        configMap
            .put(SofaBootRpcConfigConstants.BOLT_ACCEPTS_COUNT, SofaBootRpcConfigConstants.BOLT_ACCEPTS_COUNT_LINE);

        /** rest */
        configMap.put(SofaBootRpcConfigConstants.REST_HOSTNAME, SofaBootRpcConfigConstants.REST_HOSTNAME_LINE);
        configMap.put(SofaBootRpcConfigConstants.REST_PORT, SofaBootRpcConfigConstants.REST_PORT_LINE);
        configMap.put(SofaBootRpcConfigConstants.REST_IO_THREAD_COUNT,
            SofaBootRpcConfigConstants.REST_IO_THREAD_COUNT_LINE);
        configMap.put(SofaBootRpcConfigConstants.REST_EXECUTOR_THREAD_COUNT,
            SofaBootRpcConfigConstants.REST_EXECUTOR_THREAD_COUNT_LINE);
        configMap.put(SofaBootRpcConfigConstants.REST_MAX_REQUEST_SIZE,
            SofaBootRpcConfigConstants.REST_MAX_REQUEST_SIZE_LINE);
        configMap.put(SofaBootRpcConfigConstants.REST_TELNET, SofaBootRpcConfigConstants.REST_TELNET_LINE);
        configMap.put(SofaBootRpcConfigConstants.REST_DAEMON, SofaBootRpcConfigConstants.REST_DAEMON_LINE);

        /** dubbo */
        configMap.put(SofaBootRpcConfigConstants.DUBBO_PORT, SofaBootRpcConfigConstants.DUBBO_PORT_LINE);
        configMap.put(SofaBootRpcConfigConstants.DUBBO_IO_THREAD_COUNT,
            SofaBootRpcConfigConstants.DUBBO_IO_THREAD_COUNT_LINE);
        configMap.put(SofaBootRpcConfigConstants.DUBBO_EXECUTOR_THREAD_COUNT,
            SofaBootRpcConfigConstants.DUBBO_EXECUTOR_THREAD_COUNT_LINE);
        configMap
            .put(SofaBootRpcConfigConstants.DUBBO_ACCEPTS_COUNT, SofaBootRpcConfigConstants.DUBBO_ACCEPTS_COUNT_LINE);

        /** registry */
        configMap.put(SofaBootRpcConfigConstants.REGISTRY_PROTOCOL, SofaBootRpcConfigConstants.REGISTRY_PROTOCOL_LINE);
        configMap
            .put(SofaBootRpcConfigConstants.REGISTRY_FILE_PATH, SofaBootRpcConfigConstants.REGISTRY_FILE_PATH_LINE);

    }

    public static String getUnderlineKey(String pointKey) {
        return configMap.get(pointKey);
    }

    public static Set<String> getPointKeys() {
        return configMap.keySet();
    }
}