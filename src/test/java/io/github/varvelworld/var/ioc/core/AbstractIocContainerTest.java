package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by luzhonghao on 2016/12/3.
 */
@RunWith(JUnit4.class)
abstract public class AbstractIocContainerTest {

    abstract protected IocContainer createIocContainer();

    abstract protected void loadMeta(IocContainer iocContainer);

    @Test
    public void getBean() throws Exception {
        final IocContainer iocContainer = createIocContainer();
        loadMeta(iocContainer);
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun2");
        Assert.assertEquals("hello world", helloPOJO.getName());
        Assert.assertEquals("hello world2", helloPOJO2.getName());
    }

    @Test
    public void injectBean() throws Exception {
        final IocContainer iocContainer = createIocContainer();
        loadMeta(iocContainer);
        HiService hiService = (HiService) iocContainer.getBean("hi");
        Assert.assertEquals("hello world2", hiService.hello());
    }
}
