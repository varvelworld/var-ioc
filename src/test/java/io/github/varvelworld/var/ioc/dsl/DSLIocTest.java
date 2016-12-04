package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.core.AbstractIocContainerTest;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.IocContainerImpl;
import io.github.varvelworld.var.ioc.meta.BeanScope;

import static io.github.varvelworld.var.ioc.dsl.IocDSL.*;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class DSLIocTest extends AbstractIocContainerTest {

    protected IocContainer createIocContainer() {
        return new IocContainerImpl();
    }

    protected void loadAndRefreshMeta(IocContainer iocContainer) {
        iocContainer.loadMeta(beans(
                bean("fun", () -> new HelloPOJO("hello world"))
                , bean("fun2", () -> new HelloPOJO("hello world2"))
                , bean("hi", HiService.class, resources(resource("fun2", "fun")))
                , bean("fun3", () -> new HelloPOJO("hello world3"), BeanScope.PROTOTYPE)
                , bean("fun4", () -> new HelloPOJO("hello world4"))
                , bean("hi2", HiService.class, resources(resource("fun2", "fun"), resource("fun3", "fun3"))
                        , BeanScope.PROTOTYPE)
                , bean("hi3", HiService.class
                        , constructor(arg("fun4"))
                        , resources(resource("fun2", "fun"), resource("fun3", "fun3")))
        ).beansMeta());
        iocContainer.refreshMeta();
    }

}