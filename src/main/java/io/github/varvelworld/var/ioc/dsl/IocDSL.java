package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.core.BeanSupplierByClassImpl;
import io.github.varvelworld.var.ioc.dsl.meta.factory.DSLBeanMetaFactoryImpl;
import io.github.varvelworld.var.ioc.dsl.meta.factory.DSLBeanResourcesMetaFactoryImpl;
import io.github.varvelworld.var.ioc.dsl.meta.factory.DSLBeansMetaFactoryImpl;
import io.github.varvelworld.var.ioc.dsl.meta.factory.DSLResourceMetaFactory;
import io.github.varvelworld.var.ioc.meta.BeanScope;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeansMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.ResourceMetaFactory;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class IocDSL {

    public static BeansMetaFactory beans(BeanMetaFactory... beanMetaFactories) {
        return new DSLBeansMetaFactoryImpl(Arrays.asList(beanMetaFactories));
    }

    public static BeanMetaFactory bean(String id, Supplier<?> beanSupplier
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope beanScope) {
        return new DSLBeanMetaFactoryImpl(id, beanSupplier, beanResourcesMetaFactory, beanScope);
    }

    public static BeanMetaFactory bean(String id, Supplier<?> beanSupplier
            , BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return bean(id, beanSupplier, beanResourcesMetaFactory, BeanScope.SINGLETON);
    }

    public static BeanMetaFactory bean(String id, Supplier<?> beanSupplier
            , BeanScope beanScope) {
        return bean(id, beanSupplier, BeanResourcesMetaFactory.EMPTY, beanScope);
    }

    public static BeanMetaFactory bean(String id, Supplier<?> beanSupplier) {
        return bean(id, beanSupplier, BeanResourcesMetaFactory.EMPTY);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory
            , BeanScope beanScope) {
        return bean(id, new BeanSupplierByClassImpl<>(clazz), beanResourcesMetaFactory, beanScope);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return bean(id, clazz, beanResourcesMetaFactory, BeanScope.SINGLETON);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz) {
        return bean(id, clazz, BeanResourcesMetaFactory.EMPTY);
    }



    public static BeanResourcesMetaFactory resources(ResourceMetaFactory... resourceMetaFactories) {
        return new DSLBeanResourcesMetaFactoryImpl(Arrays.asList(resourceMetaFactories));
    }

    public static ResourceMetaFactory resource(String id, String propertyName) {
        return new DSLResourceMetaFactory(id, propertyName);
    }
}
