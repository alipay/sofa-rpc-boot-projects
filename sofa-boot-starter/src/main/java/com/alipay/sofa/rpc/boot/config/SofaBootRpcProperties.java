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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author khotyn
 */
@ConfigurationProperties("com.alipay.sofa.rpc")
public class SofaBootRpcProperties {
    /* fault-tolerance */
    private String aftRegulationEffective;
    private String aftDegradeEffective;
    private String aftTimeWindow;
    private String aftLeastWindowCount;
    private String aftLeastWindowExceptionRateMultiple;
    private String aftWeightDegradeRate;
    private String aftWeightRecoverRate;
    private String aftDegradeLeastWeight;
    private String aftDegradeMaxIpCount;
    /* Bolt */
    private String boltPort;
    private String boltIoThreadCount;
    private String boltExecutorThreadCount;
    private String boltAcceptsCount;
    /* rest */
    private String restHostname;
    private String restPort;
    private String restIoThreadCount;
    private String restExecutorThreadCount;
    private String restMaxRequestSize;
    private String restTelnet;
    private String restDaemon;
    /* dubbo */
    private String dubboPort;
    private String dubboIoThreadCount;
    private String dubboExecutorThreadCount;
    private String dubboAcceptsCount;
    /* registry */
    private String registryProtocol;

    public String getAftRegulationEffective() {
        return aftRegulationEffective;
    }

    public void setAftRegulationEffective(String aftRegulationEffective) {
        this.aftRegulationEffective = aftRegulationEffective;
    }

    public String getAftDegradeEffective() {
        return aftDegradeEffective;
    }

    public void setAftDegradeEffective(String aftDegradeEffective) {
        this.aftDegradeEffective = aftDegradeEffective;
    }

    public String getAftTimeWindow() {
        return aftTimeWindow;
    }

    public void setAftTimeWindow(String aftTimeWindow) {
        this.aftTimeWindow = aftTimeWindow;
    }

    public String getAftLeastWindowCount() {
        return aftLeastWindowCount;
    }

    public void setAftLeastWindowCount(String aftLeastWindowCount) {
        this.aftLeastWindowCount = aftLeastWindowCount;
    }

    public String getAftLeastWindowExceptionRateMultiple() {
        return aftLeastWindowExceptionRateMultiple;
    }

    public void setAftLeastWindowExceptionRateMultiple(String aftLeastWindowExceptionRateMultiple) {
        this.aftLeastWindowExceptionRateMultiple = aftLeastWindowExceptionRateMultiple;
    }

    public String getAftWeightDegradeRate() {
        return aftWeightDegradeRate;
    }

    public void setAftWeightDegradeRate(String aftWeightDegradeRate) {
        this.aftWeightDegradeRate = aftWeightDegradeRate;
    }

    public String getAftWeightRecoverRate() {
        return aftWeightRecoverRate;
    }

    public void setAftWeightRecoverRate(String aftWeightRecoverRate) {
        this.aftWeightRecoverRate = aftWeightRecoverRate;
    }

    public String getAftDegradeLeastWeight() {
        return aftDegradeLeastWeight;
    }

    public void setAftDegradeLeastWeight(String aftDegradeLeastWeight) {
        this.aftDegradeLeastWeight = aftDegradeLeastWeight;
    }

    public String getAftDegradeMaxIpCount() {
        return aftDegradeMaxIpCount;
    }

    public void setAftDegradeMaxIpCount(String aftDegradeMaxIpCount) {
        this.aftDegradeMaxIpCount = aftDegradeMaxIpCount;
    }

    public String getBoltPort() {
        return boltPort;
    }

    public void setBoltPort(String boltPort) {
        this.boltPort = boltPort;
    }

    public String getBoltIoThreadCount() {
        return boltIoThreadCount;
    }

    public void setBoltIoThreadCount(String boltIoThreadCount) {
        this.boltIoThreadCount = boltIoThreadCount;
    }

    public String getBoltExecutorThreadCount() {
        return boltExecutorThreadCount;
    }

    public void setBoltExecutorThreadCount(String boltExecutorThreadCount) {
        this.boltExecutorThreadCount = boltExecutorThreadCount;
    }

    public String getBoltAcceptsCount() {
        return boltAcceptsCount;
    }

    public void setBoltAcceptsCount(String boltAcceptsCount) {
        this.boltAcceptsCount = boltAcceptsCount;
    }

    public String getRestHostname() {
        return restHostname;
    }

    public void setRestHostname(String restHostname) {
        this.restHostname = restHostname;
    }

    public String getRestPort() {
        return restPort;
    }

    public void setRestPort(String restPort) {
        this.restPort = restPort;
    }

    public String getRestIoThreadCount() {
        return restIoThreadCount;
    }

    public void setRestIoThreadCount(String restIoThreadCount) {
        this.restIoThreadCount = restIoThreadCount;
    }

    public String getRestExecutorThreadCount() {
        return restExecutorThreadCount;
    }

    public void setRestExecutorThreadCount(String restExecutorThreadCount) {
        this.restExecutorThreadCount = restExecutorThreadCount;
    }

    public String getRestMaxRequestSize() {
        return restMaxRequestSize;
    }

    public void setRestMaxRequestSize(String restMaxRequestSize) {
        this.restMaxRequestSize = restMaxRequestSize;
    }

    public String getRestTelnet() {
        return restTelnet;
    }

    public void setRestTelnet(String restTelnet) {
        this.restTelnet = restTelnet;
    }

    public String getRestDaemon() {
        return restDaemon;
    }

    public void setRestDaemon(String restDaemon) {
        this.restDaemon = restDaemon;
    }

    public String getDubboPort() {
        return dubboPort;
    }

    public void setDubboPort(String dubboPort) {
        this.dubboPort = dubboPort;
    }

    public String getDubboIoThreadCount() {
        return dubboIoThreadCount;
    }

    public void setDubboIoThreadCount(String dubboIoThreadCount) {
        this.dubboIoThreadCount = dubboIoThreadCount;
    }

    public String getDubboExecutorThreadCount() {
        return dubboExecutorThreadCount;
    }

    public void setDubboExecutorThreadCount(String dubboExecutorThreadCount) {
        this.dubboExecutorThreadCount = dubboExecutorThreadCount;
    }

    public String getDubboAcceptsCount() {
        return dubboAcceptsCount;
    }

    public void setDubboAcceptsCount(String dubboAcceptsCount) {
        this.dubboAcceptsCount = dubboAcceptsCount;
    }

    public String getRegistryProtocol() {
        return registryProtocol;
    }

    public void setRegistryProtocol(String registryProtocol) {
        this.registryProtocol = registryProtocol;
    }
}
