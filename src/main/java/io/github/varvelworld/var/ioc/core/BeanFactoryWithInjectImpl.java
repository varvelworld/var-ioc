package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanFactoryWithInjectImpl implements BeanFactory {

    private final BeanFactory beanFactory;
    private final BeanResourcesMetaFactory beanResourcesMetaFactory;

    public BeanFactoryWithInjectImpl(BeanFactory beanFactory, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        this.beanFactory = beanFactory;
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        Object bean = beanFactory.bean(iocContainer);
        for (ResourceMeta resourceMeta : beanResourcesMetaFactory.beanResourcesMeta(bean).resourceMetaList()) {
            resourceMeta.getBeanInjector().inject(bean, iocContainer.getBean(resourceMeta.getId()));
        }
        return bean;
    }
}
