package com.alipay.sofa.rpc.test.shutdown;

import com.alipay.sofa.rpc.boot.config.SofaBootRpcConfigConstants;
import com.alipay.sofa.rpc.test.util.TestUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootApplication
@SpringBootTest(classes = RpcShutdownTest.class)
@RunWith(SpringRunner.class)
@ImportResource("classpath:spring/readiness.xml")
public class RpcShutdownTest implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Test
    public void test() {
        Assert.assertFalse(TestUtils.available(SofaBootRpcConfigConstants.BOLT_PORT_DEFAULT));
    }

    @AfterClass
    public static void testRpcGracefulShutdown() {
        ((ConfigurableApplicationContext) applicationContext).close();
        Assert.assertTrue(TestUtils.available(SofaBootRpcConfigConstants.BOLT_PORT_DEFAULT));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RpcShutdownTest.applicationContext = applicationContext;
    }
}
