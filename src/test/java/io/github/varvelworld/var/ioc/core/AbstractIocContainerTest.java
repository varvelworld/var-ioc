package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by luzhonghao on 2016/12/3.
 */
@RunWith(JUnit4.class)
abstract public class AbstractIocContainerTest {

    abstract protected IocContainer createIocContainer();

    abstract protected void loadAndRefreshMeta(IocContainer iocContainer);

    protected IocContainer iocContainer;

    @Before
    public void before() {
        iocContainer = createIocContainer();
        loadAndRefreshMeta(iocContainer);
    }

    @Test
    public void getBean() throws Exception {
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun2");
        Assert.assertEquals("hello world", helloPOJO.getName());
        Assert.assertEquals("hello world2", helloPOJO2.getName());
    }

    @Test
    public void injectBean() throws Exception {
        HiService hiService = (HiService) iocContainer.getBean("hi");
        Assert.assertEquals("hello world2", hiService.hello());
    }

    @Test
    public void prototypeBean() throws Exception {
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun3");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun3");
        Assert.assertNotNull(helloPOJO);
        Assert.assertNotNull(helloPOJO2);
        Assert.assertNotSame(helloPOJO, helloPOJO2);
    }

    @Test
    public void singletonBean() throws Exception {
        HelloPOJO helloPOJO = (HelloPOJO) iocContainer.getBean("fun");
        HelloPOJO helloPOJO2 = (HelloPOJO) iocContainer.getBean("fun");
        Assert.assertNotNull(helloPOJO);
        Assert.assertNotNull(helloPOJO2);
        Assert.assertSame(helloPOJO, helloPOJO2);
    }

    @Test
    public void prototypeThenSingletonBean() throws Exception {
        HiService hi1 = (HiService) iocContainer.getBean("hi2");
        HiService hi2 = (HiService) iocContainer.getBean("hi2");
        Assert.assertNotNull(hi1);
        Assert.assertNotNull(hi2);
        Assert.assertNotSame(hi1, hi2);
        Assert.assertNotNull(hi1.getFun());
        Assert.assertNotNull(hi2.getFun());
        Assert.assertSame(hi1.getFun(), hi2.getFun());
    }

    @Test
    public void prototypeThenPrototypeBeanBean() throws Exception {
        HiService hi1 = (HiService) iocContainer.getBean("hi2");
        HiService hi2 = (HiService) iocContainer.getBean("hi2");
        Assert.assertNotNull(hi1);
        Assert.assertNotNull(hi2);
        Assert.assertNotSame(hi1, hi2);
        Assert.assertNotNull(hi1.getFun3());
        Assert.assertNotNull(hi2.getFun3());
        Assert.assertNotSame(hi1.getFun3(), hi2.getFun3());
    }

    @Test
    public void resourceByConstructor() {
        HiService hi3 = iocContainer.getBean("hi3", HiService.class);
        Assert.assertNotNull(hi3);
        Assert.assertNotNull(hi3.getFun4());
        Assert.assertEquals("hello world4", hi3.getFun4().getName());
    }
}
