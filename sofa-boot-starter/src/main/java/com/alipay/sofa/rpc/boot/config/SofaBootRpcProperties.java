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
package com.alipay.sofa.rpc.boot.config;

import com.google.common.base.CaseFormat;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @author khotyn
 */
@ConfigurationProperties(SofaBootRpcProperties.PREFIX)
public class SofaBootRpcProperties {
    static final String PREFIX = "com.alipay.sofa.rpc";

    private Environment environment;

    /* fault-tolerance start */
    private String      aftRegulationEffective;
    private String      aftDegradeEffective;
    private String      aftTimeWindow;
    private String      aftLeastWindowCount;
    private String      aftLeastWindowExceptionRateMultiple;
    private String      aftWeightDegradeRate;
    private String      aftWeightRecoverRate;
    private String      aftDegradeLeastWeight;
    private String      aftDegradeMaxIpCount;
    /* fault-tolerance end */

    /* Bolt start*/
    private String      boltPort;
    private String      boltThreadPoolCoreSize;
    private String      boltThreadPoolMaxSize;
    private String      boltThreadPoolQueueSize;
    private String      boltAcceptsSize;
    /* Bolt end*/

    /* rest start*/
    private String      restHostname;
    private String      restPort;
    private String      restIoThreadSize;
    // has no use
    private String      restThreadPoolCoreSize;
    private String      restThreadPoolMaxSize;
    private String      restMaxRequestSize;
    private String      restTelnet;
    private String      restDaemon;
    /* rest end */

    /* dubbo  start*/
    private String      dubboPort;
    private String      dubboIoThreadSize;
    //has no use
    private String      dubboThreadPoolCoreSize;
    private String      dubboThreadPoolMaxSize;
    //has no use
    private String      dubboThreadPoolQueueSize;
    private String      dubboAcceptsSize;
    /* dubbo  end*/

    /* registry */
    private String      registryAddress;

    //publish to registry
    private String      virtualHost;

    //publish to registry
    private String      virtualPort;

    //publish to registry virtual host range
    private String      enabledIpRange;

    private String      bindNetworkInterface;

    // bound server
    private String      boundHost;

    public SofaBootRpcProperties(Environment environment) {
        this.environment = environment;
    }

    public String getAftRegulationEffective() {
        return fetchTransformedString(aftRegulationEffective);
    }

    public void setAftRegulationEffective(String aftRegulationEffective) {
        this.aftRegulationEffective = aftRegulationEffective;
    }

    public String getAftDegradeEffective() {
        return fetchTransformedString(aftDegradeEffective);
    }

    public void setAftDegradeEffective(String aftDegradeEffective) {
        this.aftDegradeEffective = aftDegradeEffective;
    }

    public String getAftTimeWindow() {
        return fetchTransformedString(aftTimeWindow);
    }

    public void setAftTimeWindow(String aftTimeWindow) {
        this.aftTimeWindow = aftTimeWindow;
    }

    public String getAftLeastWindowCount() {
        return fetchTransformedString(aftLeastWindowCount);
    }

    public void setAftLeastWindowCount(String aftLeastWindowCount) {
        this.aftLeastWindowCount = aftLeastWindowCount;
    }

    public String getAftLeastWindowExceptionRateMultiple() {
        return fetchTransformedString(aftLeastWindowExceptionRateMultiple);
    }

    public void setAftLeastWindowExceptionRateMultiple(String aftLeastWindowExceptionRateMultiple) {
        this.aftLeastWindowExceptionRateMultiple = aftLeastWindowExceptionRateMultiple;
    }

    public String getAftWeightDegradeRate() {
        return fetchTransformedString(aftWeightDegradeRate);
    }

    public void setAftWeightDegradeRate(String aftWeightDegradeRate) {
        this.aftWeightDegradeRate = aftWeightDegradeRate;
    }

    public String getAftWeightRecoverRate() {
        return fetchTransformedString(aftWeightRecoverRate);
    }

    public void setAftWeightRecoverRate(String aftWeightRecoverRate) {
        this.aftWeightRecoverRate = aftWeightRecoverRate;
    }

    public String getAftDegradeLeastWeight() {
        return fetchTransformedString(aftDegradeLeastWeight);
    }

    public void setAftDegradeLeastWeight(String aftDegradeLeastWeight) {
        this.aftDegradeLeastWeight = aftDegradeLeastWeight;
    }

    public String getAftDegradeMaxIpCount() {
        return fetchTransformedString(aftDegradeMaxIpCount);
    }

    public void setAftDegradeMaxIpCount(String aftDegradeMaxIpCount) {
        this.aftDegradeMaxIpCount = aftDegradeMaxIpCount;
    }

    public String getBoltPort() {
        return fetchTransformedString(boltPort);
    }

    public void setBoltPort(String boltPort) {
        this.boltPort = boltPort;
    }

    public String getDubboIoThreadSize() {
        return fetchTransformedString(dubboIoThreadSize);
    }

    public void setDubboIoThreadSize(String dubboIoThreadSize) {
        this.dubboIoThreadSize = dubboIoThreadSize;
    }

    public String getBoltThreadPoolCoreSize() {
        return fetchTransformedString(boltThreadPoolCoreSize);
    }

    public void setBoltThreadPoolCoreSize(String boltThreadPoolCoreSize) {
        this.boltThreadPoolCoreSize = boltThreadPoolCoreSize;
    }

    public String getBoltThreadPoolMaxSize() {
        return fetchTransformedString(boltThreadPoolMaxSize);
    }

    public void setBoltThreadPoolMaxSize(String boltThreadPoolMaxSize) {
        this.boltThreadPoolMaxSize = boltThreadPoolMaxSize;
    }

    public String getBoltAcceptsSize() {
        return fetchTransformedString(boltAcceptsSize);
    }

    public void setBoltAcceptsSize(String boltAcceptsSize) {
        this.boltAcceptsSize = boltAcceptsSize;
    }

    public String getRestHostname() {
        return fetchTransformedString(restHostname);
    }

    public void setRestHostname(String restHostname) {
        this.restHostname = restHostname;
    }

    public String getRestPort() {
        return fetchTransformedString(restPort);
    }

    public void setRestPort(String restPort) {
        this.restPort = restPort;
    }

    public String getRestIoThreadSize() {
        return fetchTransformedString(restIoThreadSize);
    }

    public void setRestIoThreadSize(String restIoThreadSize) {
        this.restIoThreadSize = restIoThreadSize;
    }

    public String getRestThreadPoolMaxSize() {
        return fetchTransformedString(restThreadPoolMaxSize);
    }

    public void setRestThreadPoolMaxSize(String restThreadPoolMaxSize) {
        this.restThreadPoolMaxSize = restThreadPoolMaxSize;
    }

    public String getRestMaxRequestSize() {
        return fetchTransformedString(restMaxRequestSize);
    }

    public void setRestMaxRequestSize(String restMaxRequestSize) {
        this.restMaxRequestSize = restMaxRequestSize;
    }

    public String getRestTelnet() {
        return fetchTransformedString(restTelnet);
    }

    public void setRestTelnet(String restTelnet) {
        this.restTelnet = restTelnet;
    }

    public String getRestDaemon() {
        return fetchTransformedString(restDaemon);
    }

    public void setRestDaemon(String restDaemon) {
        this.restDaemon = restDaemon;
    }

    public String getDubboPort() {
        return fetchTransformedString(dubboPort);
    }

    public void setDubboPort(String dubboPort) {
        this.dubboPort = dubboPort;
    }

    public String getDubboThreadPoolMaxSize() {
        return fetchTransformedString(dubboThreadPoolMaxSize);
    }

    public void setDubboThreadPoolMaxSize(String dubboThreadPoolMaxSize) {
        this.dubboThreadPoolMaxSize = dubboThreadPoolMaxSize;
    }

    public String getDubboAcceptsSize() {
        return fetchTransformedString(dubboAcceptsSize);
    }

    public void setDubboAcceptsSize(String dubboAcceptsSize) {
        this.dubboAcceptsSize = dubboAcceptsSize;
    }

    public String getRegistryAddress() {
        return fetchTransformedString(registryAddress);
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getBoltThreadPoolQueueSize() {
        return fetchTransformedString(boltThreadPoolQueueSize);
    }

    public void setBoltThreadPoolQueueSize(String boltThreadPoolQueueSize) {
        this.boltThreadPoolQueueSize = boltThreadPoolQueueSize;
    }

    public String getDubboThreadPoolCoreSize() {
        return fetchTransformedString(dubboThreadPoolCoreSize);
    }

    public void setDubboThreadPoolCoreSize(String dubboThreadPoolCoreSize) {
        this.dubboThreadPoolCoreSize = dubboThreadPoolCoreSize;
    }

    public String getDubboThreadPoolQueueSize() {
        return fetchTransformedString(dubboThreadPoolQueueSize);
    }

    public void setDubboThreadPoolQueueSize(String dubboThreadPoolQueueSize) {
        this.dubboThreadPoolQueueSize = dubboThreadPoolQueueSize;
    }

    public String getRestThreadPoolCoreSize() {
        return fetchTransformedString(restThreadPoolCoreSize);
    }

    public void setRestThreadPoolCoreSize(String restThreadPoolCoreSize) {
        this.restThreadPoolCoreSize = restThreadPoolCoreSize;
    }

    public String getVirtualHost() {
        return fetchTransformedString(virtualHost);
    }

    private String fetchTransformedString(String orginalValue) {
        return StringUtils.isEmpty(orginalValue) ? getDotString(new Object() {
        }.getClass().getEnclosingMethod().getName()) : orginalValue;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getBoundHost() {
        return fetchTransformedString(boundHost);
    }

    public void setBoundHost(String boundHost) {
        this.boundHost = boundHost;
    }

    public String getVirtualPort() {
        return fetchTransformedString(virtualPort);
    }

    public void setVirtualPort(String virtualPort) {
        this.virtualPort = virtualPort;
    }

    public String getEnabledIpRange() {
        return fetchTransformedString(enabledIpRange);
    }

    public void setEnabledIpRange(String enabledIpRange) {
        this.enabledIpRange = enabledIpRange;
    }

    public String getBindNetworkInterface() {
        return fetchTransformedString(bindNetworkInterface);
    }

    public void setBindNetworkInterface(String bindNetworkInterface) {
        this.bindNetworkInterface = bindNetworkInterface;
    }

    private String getDotString(String enclosingMethodName) {
        if (environment == null) {
            return null;
        }
        return environment.getProperty(PREFIX + "." + camelToDot(enclosingMethodName.substring(3)));
    }

    String camelToDot(String camelCaseString) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, camelCaseString).replaceAll("-", ".");
    }

}
