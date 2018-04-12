/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.sofa.rpc.extension.fault.tolerance;

import com.alipay.sofa.rpc.client.aft.FaultToleranceConfig;
import com.alipay.sofa.rpc.client.aft.FaultToleranceConfigManager;
import com.alipay.sofa.rpc.config.SofaBootRpcConfig;
import com.alipay.sofa.rpc.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.register.util.SofaBootRpcParserUtil;

/**
 *
 * @author liangen
 * @version $Id: FaultToleranceLauncher.java, v 0.1 2018年04月13日 上午1:25 liangen Exp $
 */
public class FaultToleranceLauncher {

    public void startFaultTolerance(){

        String appName = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.APP_NAME);

        String regulationEffectiveStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String degradeEffectiveStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String timeWindowStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String leastWindowCountStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String leastWindowExceptionRateMultipleStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String weightDegradeRateStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String weightRecoverRateStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String degradeLeastWeightStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);
        String degradeMaxIpCountStr = SofaBootRpcConfig.getPropertyAllCircumstances(SofaBootRpcConfigConstants.RPC_AFT_REGULATION_EFFECTIVE);

        Boolean regulationEffective = SofaBootRpcParserUtil.parseBoolean(regulationEffectiveStr);
        Boolean degradeEffective = SofaBootRpcParserUtil.parseBoolean(degradeEffectiveStr);
        Long timeWindow = SofaBootRpcParserUtil.parseLong(timeWindowStr);
        Long leastWindowCount = SofaBootRpcParserUtil.parseLong(leastWindowCountStr);
        Double leastWindowExceptionRateMultiple = SofaBootRpcParserUtil.parseDuoble(leastWindowExceptionRateMultipleStr);
        Double weightDegradeRate = SofaBootRpcParserUtil.parseDuoble(weightDegradeRateStr);
        Double weightRecoverRate = SofaBootRpcParserUtil.parseDuoble(weightRecoverRateStr);
        Integer degradeLeastWeight = SofaBootRpcParserUtil.parseInteger(degradeLeastWeightStr);
        Integer degradeMaxIpCount = SofaBootRpcParserUtil.parseInteger(degradeMaxIpCountStr);

        FaultToleranceConfig faultToleranceConfig = new FaultToleranceConfig();
        if(regulationEffective != null){
            faultToleranceConfig.setRegulationEffective(regulationEffective);
        }
        if(degradeEffective != null){
            faultToleranceConfig.setDegradeEffective(degradeEffective);
        }
        if(timeWindow != null){
            faultToleranceConfig.setTimeWindow(timeWindow);
        }
        if(leastWindowCount != null){
            faultToleranceConfig.setLeastWindowCount(leastWindowCount);
        }
        if(leastWindowExceptionRateMultiple != null){
            faultToleranceConfig.setLeastWindowExceptionRateMultiple(leastWindowExceptionRateMultiple);
        }
        if(weightDegradeRate != null){
            faultToleranceConfig.setWeightDegradeRate(weightDegradeRate);
        }
        if(weightRecoverRate != null){
            faultToleranceConfig.setWeightRecoverRate(weightRecoverRate);
        }
        if(degradeLeastWeight != null){
            faultToleranceConfig.setDegradeLeastWeight(degradeLeastWeight);
        }
        if(degradeMaxIpCount != null){
            faultToleranceConfig.setDegradeMaxIpCount(degradeMaxIpCount);
        }

        FaultToleranceConfigManager.putAppConfig(appName, faultToleranceConfig);
    }

}