package io.github.varvelworld.var.ioc.core.dsl;

import io.github.varvelworld.var.ioc.HelloPOJO;
import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.core.AbstractIocContainerTest;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.IocContainerImpl;
import io.github.varvelworld.var.ioc.core.meta.BeanScope;

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
                , bean("hi", factory(HiService.class), properties(property("fun2", "fun")))
                , bean("fun3", factory(() -> new HelloPOJO("hello world3")), BeanScope.PROTOTYPE)
                , bean("fun4", factory(() -> new HelloPOJO("hello world4")))
                , bean("hi2", factory(HiService.class), properties(property("fun2", "fun"), property("fun3", "fun3"))
                        , BeanScope.PROTOTYPE)
                , bean("hi3", factory(HiService.class, constructor(arg("fun4")))
                        , properties(property("fun2", "fun"), property("fun3", "fun3")))
        ).beansMeta());
        iocContainer.refreshMeta();
    }

}