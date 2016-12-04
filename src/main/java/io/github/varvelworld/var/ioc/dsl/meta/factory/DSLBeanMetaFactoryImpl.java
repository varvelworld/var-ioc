package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithInjectImpl;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.BeanScope;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanMetaFactoryImpl implements BeanMetaFactory {

    final private BeanMeta beanMeta;

    public DSLBeanMetaFactoryImpl(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope scope) {
        this.beanMeta = new BeanMeta(id, new BeanFactoryWithInjectImpl(
                scope.wrap(beanFactory)
                , beanResourcesMetaFactory));
    }

    @Override
    public BeanMeta beanMeta() {
        return beanMeta;
    }
}
