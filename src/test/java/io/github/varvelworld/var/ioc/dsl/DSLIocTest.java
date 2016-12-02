package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.TestBeans;
import io.github.varvelworld.var.ioc.annotation.IocAnnotation;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.IocContainerImpl;
import org.junit.Assert;
import org.junit.Test;

import static io.github.varvelworld.var.ioc.dsl.IocDSL.*;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class DSLIocTest {

    @Test
    public void addBeans() throws Exception {
        final IocContainer iocContainer = new IocContainerImpl();
        iocContainer.addBeans(beans(
                bean("fun", () -> new HelloPOJO("hello world"))
                , bean("fun2", () -> new HelloPOJO("hello world2"))
                , bean("hi", HiService.class, resources(
                        resource("fun2", "fun")
                ))
        ).createBeansMeta());
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun2");
        Assert.assertEquals("hello world", helloPOJO.getName());
        Assert.assertEquals("hello world2", helloPOJO2.getName());
    }

    @Test
    public void injectBean() throws Exception {
        final IocContainer iocContainer = new IocContainerImpl();
        iocContainer.addBeans(beans(
                bean("fun", () -> new HelloPOJO("hello world2"))
                , bean("fun2", () -> new HelloPOJO("hello world2"))
                , bean("hi", HiService.class, resources(
                        resource("fun2", "fun")
                ))
        ).createBeansMeta());
        iocContainer.injectBeans();
        HiService hiService = (HiService) iocContainer.getBean("hi");
        Assert.assertEquals("hello world2", hiService.hello());
    }
}