package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.core.meta.BeanResourcesMeta;
import io.github.varvelworld.var.ioc.core.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanResourcesMetaFactory;

import java.util.function.Function;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanFactoryWithInjectImpl implements BeanFactory {

    private final BeanFactory beanFactory;
    private final Function<Object, BeanResourcesMeta> beanResourcesMeta;

    public BeanFactoryWithInjectImpl(BeanFactory beanFactory, Function<Object, BeanResourcesMeta> beanResourcesMeta) {
        this.beanFactory = beanFactory;
        this.beanResourcesMeta = beanResourcesMeta;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        Object bean = beanFactory.bean(iocContainer);
        for (ResourceMeta resourceMeta : beanResourcesMeta.apply(bean).resourceMetaList()) {
            resourceMeta.getBeanInjector().inject(bean, iocContainer.getBean(resourceMeta.getId()));
        }
        return bean;
    }
}
