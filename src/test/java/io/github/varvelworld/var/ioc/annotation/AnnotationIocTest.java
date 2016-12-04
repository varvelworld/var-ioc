package io.github.varvelworld.var.ioc.annotation;

import io.github.varvelworld.var.ioc.HiService;
import io.github.varvelworld.var.ioc.TestBeans;
import io.github.varvelworld.var.ioc.core.AbstractIocContainerTest;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.IocContainerImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationIocTest extends AbstractIocContainerTest {

    @Override
    protected IocContainer createIocContainer() {
        return new IocContainerImpl();
    }

    @Override
    protected void loadAndRefreshMeta(IocContainer iocContainer) {
        iocContainer.loadMeta(IocAnnotation.beans(TestBeans.class).beansMeta());
        iocContainer.refreshMeta();
    }
}