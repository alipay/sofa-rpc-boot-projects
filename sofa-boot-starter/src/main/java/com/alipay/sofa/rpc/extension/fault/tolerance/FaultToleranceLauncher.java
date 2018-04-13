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
package com.alipay.sofa.rpc.extension.fault.tolerance;

import com.alipay.sofa.rpc.client.aft.FaultToleranceConfig;
import com.alipay.sofa.rpc.client.aft.FaultToleranceConfigManager;
import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.register.util.SofaBootRpcParserUtil;

/**
 *
 * Responsible for loading config of adaptive fault tolerance
 *
 * @author <a href="mailto:lw111072@antfin.com">liangen</a>
 */
public class FaultToleranceLauncher {

    public void startFaultTolerance() {

        String appName = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.APP_NAME);

        String regulationEffectiveStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String degradeEffectiveStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String timeWindowStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String leastWindowCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String leastWindowExceptionRateMultipleStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String weightDegradeRateStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String weightRecoverRateStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String degradeLeastWeightStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String degradeMaxIpCountStr = SofaBootRpcConfig
            .getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);

        Boolean regulationEffective = SofaBootRpcParserUtil.parseBoolean(regulationEffectiveStr);
        Boolean degradeEffective = SofaBootRpcParserUtil.parseBoolean(degradeEffectiveStr);
        Long timeWindow = SofaBootRpcParserUtil.parseLong(timeWindowStr);
        Long leastWindowCount = SofaBootRpcParserUtil.parseLong(leastWindowCountStr);
        Double leastWindowExceptionRateMultiple = SofaBootRpcParserUtil
            .parseDuoble(leastWindowExceptionRateMultipleStr);
        Double weightDegradeRate = SofaBootRpcParserUtil.parseDuoble(weightDegradeRateStr);
        Double weightRecoverRate = SofaBootRpcParserUtil.parseDuoble(weightRecoverRateStr);
        Integer degradeLeastWeight = SofaBootRpcParserUtil.parseInteger(degradeLeastWeightStr);
        Integer degradeMaxIpCount = SofaBootRpcParserUtil.parseInteger(degradeMaxIpCountStr);

        FaultToleranceConfig faultToleranceConfig = new FaultToleranceConfig();
        if (regulationEffective != null) {
            faultToleranceConfig.setRegulationEffective(regulationEffective);
        }
        if (degradeEffective != null) {
            faultToleranceConfig.setDegradeEffective(degradeEffective);
        }
        if (timeWindow != null) {
            faultToleranceConfig.setTimeWindow(timeWindow);
        }
        if (leastWindowCount != null) {
            faultToleranceConfig.setLeastWindowCount(leastWindowCount);
        }
        if (leastWindowExceptionRateMultiple != null) {
            faultToleranceConfig.setLeastWindowExceptionRateMultiple(leastWindowExceptionRateMultiple);
        }
        if (weightDegradeRate != null) {
            faultToleranceConfig.setWeightDegradeRate(weightDegradeRate);
        }
        if (weightRecoverRate != null) {
            faultToleranceConfig.setWeightRecoverRate(weightRecoverRate);
        }
        if (degradeLeastWeight != null) {
            faultToleranceConfig.setDegradeLeastWeight(degradeLeastWeight);
        }
        if (degradeMaxIpCount != null) {
            faultToleranceConfig.setDegradeMaxIpCount(degradeMaxIpCount);
        }

        FaultToleranceConfigManager.putAppConfig(appName, faultToleranceConfig);
    }

}