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

import com.alipay.sofa.rpc.common.SystemInfo;

/**
 *
 * @author <a href="mailto:lw111072@antfin.com">LiWei</a>
 */
public class SofaBootRpcConfigConstants {

    /** key start ********************************************************/

    /** application */
    public static final String  APP_NAME                                          = "spring.application.name";
    public static final String  APP_NAME_LINE                                     = "spring_application_name";

    public static final String  LOG_PATH                                          = "logging.path";
    public static final String  LOG_PATH_LINE                                     = "logging_path";

    public static final String  CORE_PROXY_URL                                    = "core.proxy.url";
    public static final String  CORE_PROXY_URL_LINE                               = "core_proxy_url";

    /** fault-tolerance */
    public static final String  RPC_AFT_REGULATION_EFFECTIVE                      = "rpc.aft.regulation.effective";
    public static final String  RPC_AFT_REGULATION_EFFECTIVE_LINE                 = "rpc_aft_regulation_effective";

    public static final String  RPC_AFT_DEGRADE_EFFECTIVE                         = "rpc.aft.degrade.effective";
    public static final String  RPC_AFT_DEGRADE_EFFECTIVE_LINE                    = "rpc_aft_degrade_effective";

    public static final String  RPC_AFT_TIME_WINDOW                               = "rpc.aft.time.window";
    public static final String  RPC_AFT_TIME_WINDOW_LINE                          = "rpc_aft_time_window";

    public static final String  RPC_AFT_LEAST_CALL_COUNT                          = "rpc.aft.least.call.count";
    public static final String  RPC_AFT_LEAST_CALL_COUNT_LINE                     = "rpc_aft_least_call_count";

    public static final String  RPC_AFT_LEAST_WINDOW_EXCEPTION_RATE_MULTIPLE      = "rpc.aft.least.window.exception.rate.multiple";
    public static final String  RPC_AFT_LEAST_WINDOW_EXCEPTION_RATE_MULTIPLE_LINE = "rpc_aft_least_window_exception_rate_multiple";

    public static final String  RPC_AFT_WEIGHT_DEGRADE_RATE                       = "rpc.aft.weight.degrade.rate";
    public static final String  RPC_AFT_WEIGHT_DEGRADE_RATE_LINE                  = "rpc_aft_weight_degrade_rate";

    public static final String  RPC_AFT_WEIGHT_RECOVER_RATE                       = "rpc.aft.weight.recover.rate";
    public static final String  RPC_AFT_WEIGHT_RECOVER_RATE_LINE                  = "rpc_aft_weight_recover_rate";

    public static final String  RPC_AFT_DEGRADE_LEAST_WEIGHT                      = "rpc.aft.degrade.least.weight";
    public static final String  RPC_AFT_DEGRADE_LEAST_WEIGHT_LINE                 = "rpc_aft_degrade_least_weight";

    public static final String  RPC_AFT_DEGRADE_MAX_IP_COUNT                      = "rpc.aft.degrade.max.ip.count";
    public static final String  RPC_AFT_DEGRADE_MAX_IP_COUNT_LINE                 = "rpc_aft_degrade_max_ip_count";

    /** bolt */
    public static final String  BOLT_PORT                                         = "bolt.port";
    public static final String  BOLT_PORT_LINE                                    = "bolt_port";

    public static final String  BOLT_IO_THREAD_COUNT                              = "bolt.io.thread.count";
    public static final String  BOLT_IO_THREAD_COUNT_LINE                         = "bolt_io_thread_count";

    public static final String  BOLT_EXECUTOR_THREAD_COUNT                        = "bolt.executor.thread.count";
    public static final String  BOLT_EXECUTOR_THREAD_COUNT_LINE                   = "bolt_executor_thread_count";

    public static final String  BOLT_ACCEPTS_COUNT                                = "bolt.accepts.count";
    public static final String  BOLT_ACCEPTS_COUNT_LINE                           = "bolt_accepts_count";

    /** rest */
    public static final String  REST_HOSTNAME                                     = "rest.hostname";
    public static final String  REST_HOSTNAME_LINE                                = "rest_hostname";

    public static final String  REST_PORT                                         = "rest.port";
    public static final String  REST_PORT_LINE                                    = "rest_port";

    public static final String  REST_IO_THREAD_COUNT                              = "rest.io.thread.count";
    public static final String  REST_IO_THREAD_COUNT_LINE                         = "rest_io_thread_count";

    public static final String  REST_EXECUTOR_THREAD_COUNT                        = "rest.executor.thread.count";
    public static final String  REST_EXECUTOR_THREAD_COUNT_LINE                   = "rest_executor_thread_count";

    public static final String  REST_MAX_REQUEST_SIZE                             = "rest.max.request.size";
    public static final String  REST_MAX_REQUEST_SIZE_LINE                        = "rest_max_request_size";

    public static final String  REST_TELNET                                       = "rest.telnet";
    public static final String  REST_TELNET_LINE                                  = "rest_telnet";

    public static final String  REST_DAEMON                                       = "rest.daemon";
    public static final String  REST_DAEMON_LINE                                  = "rest_daemon";

    /** dubbo */
    public static final String  DUBBO_PORT                                        = "dubbo.port";
    public static final String  DUBBO_PORT_LINE                                   = "dubbo_port";

    public static final String  DUBBO_IO_THREAD_COUNT                             = "dubbo.io.thread.count";
    public static final String  DUBBO_IO_THREAD_COUNT_LINE                        = "dubbo_io_thread_count";

    public static final String  DUBBO_EXECUTOR_THREAD_COUNT                       = "dubbo.executor.thread.count";
    public static final String  DUBBO_EXECUTOR_THREAD_COUNT_LINE                  = "dubbo_executor_thread_count";

    public static final String  DUBBO_ACCEPTS_COUNT                               = "dubbo.accepts.count";
    public static final String  DUBBO_ACCEPTS_COUNT_LINE                          = "dubbo_accepts_count";

    /** registry */
    public static final String  REGISTRY_PROTOCOL                                 = "rpc.registry.protocol";
    public static final String  REGISTRY_PROTOCOL_LINE                            = "rpc_registry_protocol";

    public static final String  REGISTRY_FILE_PATH                                = "rpc.registry.file.path";
    public static final String  REGISTRY_FILE_PATH_LINE                           = "rpc_registry_file_path";

    /** key end ********************************************************/

    /** default config value start ********************************************************/

    /** rest */
    public static final int     REST_PORT_DEFAULT                                 = 8341;
    public static final int     REST_IO_THREAD_COUNT_DEFAULT                      = SystemInfo.getCpuCores() * 2;
    public static final int     REST_EXECUTOR_THREAD_COUNT_DEFAULT                = 16;
    public static final int     REST_MAX_REQUEST_SIZE_DEFAULT                     = 1024 * 1024 * 10;
    public static final boolean REST_TELNET_DEFAULT                               = true;
    public static final boolean REST_DAEMON_DEFAULT                               = true;

    /** dubbo */
    public static final int     DUBBO_PORT_DEFAULT                                = 20800;

    /** registry */
    public static final String  REGISTRY_FILE_PATH_DEFAULT                        = System.getProperty("user.home")
                                                                                      +
                                                                                      System
                                                                                          .getProperty("file.separator") +
                                                                                      "localFileRegisty"
                                                                                      +
                                                                                      System
                                                                                          .getProperty("file.separator") +
                                                                                      "localRegistry.reg";

    /** default config value end ********************************************************/

    /** possible config value start ********************************************************/

    /** registry */
    public static final String  REGISTRY_PROTOCOL_LOCAL                           = "local";
    public static final String  REGISTRY_PROTOCOL_ZOOKEEPER                       = "zookeeper";

    /** server */
    public static final String  RPC_PROTOCOL_BOLT                                 = "bolt";
    public static final String  RPC_PROTOCOL_REST                                 = "rest";
    public static final String  RPC_PROTOCOL_DUBBO                                = "dubbo";

    /** possible config value end ********************************************************/

}