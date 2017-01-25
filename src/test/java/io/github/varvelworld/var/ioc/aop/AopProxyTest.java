package io.github.varvelworld.var.ioc.aop;

import io.github.varvelworld.var.ioc.aop.impl.AdvisorImpl;
import io.github.varvelworld.var.ioc.aop.impl.AopProxyJdkDynamicFactoryImpl;
import io.github.varvelworld.var.ioc.aop.impl.SimplePointcutImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public class AopProxyTest {

    @Test
    public void proxy() throws Exception {
        final AopProxyFactory aopProxyFactory
                = new AopProxyJdkDynamicFactoryImpl();

        AtomicInteger count = new AtomicInteger(0);
        Pointcut pointcut = new SimplePointcutImpl(null, "hello");
        AopProxy<HelloBean> aopProxy = aopProxyFactory.aopProxy(new HelloBeanImpl(), Arrays.asList(
                new AdvisorImpl((BeforeAdvice) (method, args, target) -> {
                    System.out.println("=========hello before===========");
                    assertEquals(1, count.incrementAndGet());
                }, pointcut)
                , new AdvisorImpl((AfterAdvice) (method, args, target, returnValue) -> {
                    System.out.println("=========hello after===========");
                    assertEquals(4, count.incrementAndGet());
                }, pointcut)
                , new AdvisorImpl((AroundAdvice) (method, args, target, invoker) -> {
                    System.out.println("=========hello around before===========");
                    assertEquals(2, count.incrementAndGet());
                    Object value = invoker.invoke(args);
                    assertEquals("hello world", value);
                    System.out.println("=========hello around after===========");
                    assertEquals(3, count.incrementAndGet());
                    return value;
                }, pointcut)
        ));

        HelloBean proxy = aopProxy.proxy();
        System.out.println(proxy.hello("world"));
        assertEquals(4, count.get());
    }


}