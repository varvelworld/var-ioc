package io.github.varvelworld.var.ioc.annotation;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.TestBeans;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.IocContainerImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationIocTest {

    @Test
    public void addBeans() throws Exception {
        final IocContainer iocContainer = new IocContainerImpl();
        iocContainer.loadMeta(IocAnnotation.beansMetaByAnnotation(TestBeans.class));
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun2");
        Assert.assertEquals("hello world", helloPOJO.getName());
        Assert.assertEquals("hello world2", helloPOJO2.getName());
    }

    @Test
    public void injectBean() throws Exception {
        final IocContainer iocContainer = new IocContainerImpl();
        iocContainer.loadMeta(IocAnnotation.beansMetaByAnnotation(TestBeans.class));
        iocContainer.refreshMeta();
        HiService hiService = (HiService) iocContainer.getBean("hi");
        Assert.assertEquals("hello world2", hiService.hello());
    }
}