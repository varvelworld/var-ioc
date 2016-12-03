package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.BeanScope;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanMetaFactoryImpl implements BeanMetaFactory {

    private final String id;
    private final BeanFactory beanFactory;
    private final BeanResourcesMetaFactory beanResourcesMetaFactory;
    final private BeanMeta beanMeta;

    public DSLBeanMetaFactoryImpl(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope scope) {
        this.id = id;
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
        this.beanFactory = scope.wrap(beanFactory);
        this.beanMeta = new BeanMeta(id, this.beanFactory, this.beanResourcesMetaFactory, scope);
    }

    @Override
    public BeanMeta beanMeta() {
        return beanMeta;
    }
}
