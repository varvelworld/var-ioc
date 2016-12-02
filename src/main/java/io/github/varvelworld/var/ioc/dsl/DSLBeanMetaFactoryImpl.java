package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryByClassImpl;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanMetaFactoryImpl implements BeanMetaFactory {

    private final String id;
    private final BeanFactory beanFactory;
    private final BeanResourcesMetaFactory beanResourcesMetaFactory;

    public DSLBeanMetaFactoryImpl(String id, BeanFactory beanFactory, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        this.id = id;
        this.beanFactory = beanFactory;
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
    }

    public DSLBeanMetaFactoryImpl(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        this.id = id;
        this.beanFactory = new BeanFactoryByClassImpl(clazz);
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
    }

    @Override
    public BeanMeta createBeanMeta() {
        return new BeanMeta(id, beanFactory, beanResourcesMetaFactory);
    }
}
