package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.TestBeans;
import io.github.varvelworld.var.ioc.meta.IocMeta;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImplTest {

    @Test
    public void addBeans() throws Exception {
        final IocContainer iocContainer = new IocContainerImpl();
        iocContainer.addBeans(IocMeta.beansMetaByAnnotation(TestBeans.class));
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun2");
        Assert.assertEquals("hello world", helloPOJO.getName());
        Assert.assertEquals("hello world2", helloPOJO2.getName());
    }

    @Test
    public void injectBean() throws Exception {
        final IocContainer iocContainer = new IocContainerImpl();
        iocContainer.addBeans(IocMeta.beansMetaByAnnotation(TestBeans.class));
        iocContainer.injectBeans();
        HiService hiService = (HiService) iocContainer.getBean("hi");
        Assert.assertEquals("hello world2", hiService.hello());
    }
}