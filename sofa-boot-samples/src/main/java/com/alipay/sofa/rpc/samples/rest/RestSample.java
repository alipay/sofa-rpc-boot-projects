/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.sofa.rpc.samples.rest;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author liangen
 * @version $Id: RestSample.java, v 0.1 2018年04月15日 下午5:40 liangen Exp $
 */
public class RestSample {

    public void start(ApplicationContext applicationContext){
        RestService restService = (RestService) applicationContext.getBean("restServiceReference");

        System.out.println(restService.sayRest("rest"));
    }
}