package io.github.varvelworld.var.ioc.core.dsl;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.HiServiceImpl;
import io.github.varvelworld.var.ioc.core.AbstractIocContainerTest;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.impl.IocContainerImpl;
import io.github.varvelworld.var.ioc.core.meta.BeanScope;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static io.github.varvelworld.var.ioc.core.dsl.IocDSL.*;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class DSLIocTest extends AbstractIocContainerTest {

    protected IocContainer createIocContainer() {
        return new IocContainerImpl();
    }

    protected void loadAndRefreshMeta(IocContainer iocContainer) {
        iocContainer.loadMeta(beans(
                bean("fun", factory(() -> new HelloPOJO("hello world")))
                , bean("fun2", factory(() -> new HelloPOJO("hello world2")))
                , bean("hi", factory(HiServiceImpl.class), properties(property("fun2", "fun")))
                , bean("fun3", factory(() -> new HelloPOJO("hello world3")), BeanScope.PROTOTYPE)
                , bean("fun4", factory(() -> new HelloPOJO("hello world4")))
                , bean("hi2", factory(HiServiceImpl.class), properties(property("fun2", "fun"), property("fun3", "fun3"))
                        , BeanScope.PROTOTYPE)
                , bean("hi3", factory(HiServiceImpl.class, constructor(arg("fun4")))
                        , properties(property("fun2", "fun"), property("fun3", "fun3")))
                ).beansMeta())
                .refreshMeta();
    }

    @Test
    public void aop() {
        AtomicInteger count = new AtomicInteger();
        iocContainer
                .loadAdvisor(advisor(IocDSL.before((method, args, target)
                        -> Assert.assertEquals(1, count.incrementAndGet())), simplePointcut("HiServiceImpl", "hello")))
                .refreshMeta();
        HiService hiService = iocContainer.getBean("hi2", HiService.class);
        hiService.hello();
        Assert.assertEquals(1, count.get());
    }

}