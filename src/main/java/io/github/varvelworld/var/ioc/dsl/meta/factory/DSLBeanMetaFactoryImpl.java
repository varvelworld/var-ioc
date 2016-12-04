package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.BeanScope;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanMetaFactoryImpl implements BeanMetaFactory {

    final private BeanMeta beanMeta;

    public DSLBeanMetaFactoryImpl(String id, Supplier<?> beanSupplier
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope scope) {
        this.beanMeta = new BeanMeta(id, scope.wrap(iocContainer -> beanSupplier.get()), beanResourcesMetaFactory);
    }

    @Override
    public BeanMeta beanMeta() {
        return beanMeta;
    }
}
