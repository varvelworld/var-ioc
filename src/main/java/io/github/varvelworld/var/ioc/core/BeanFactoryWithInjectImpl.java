package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanFactoryWithInjectImpl implements BeanFactory {

    private final BeanFactory beanFactory;
    private final BeanResourcesMetaFactory beanResourcesMetaFactory;
    private final IocContainer iocContainer;

    public BeanFactoryWithInjectImpl(BeanFactory beanFactory, BeanResourcesMetaFactory beanResourcesMetaFactory
            , IocContainer iocContainer) {
        this.beanFactory = beanFactory;
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
        this.iocContainer = iocContainer;
    }

    @Override
    public Object bean() {
        Object bean = beanFactory.bean();
        for (ResourceMeta resourceMeta : beanResourcesMetaFactory.beanResourcesMeta(bean).getResourceMetaList()) {
            resourceMeta.getBeanInjector().inject(bean, iocContainer.getBean(resourceMeta.getId()));
        }
        return bean;
    }
}
