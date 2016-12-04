package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.dsl.meta.factory.*;
import io.github.varvelworld.var.ioc.meta.BeanScope;
import io.github.varvelworld.var.ioc.meta.factory.*;

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
        return new DSLBeanMetaFactoryImpl(id, iocContainer -> beanSupplier.get(), beanResourcesMetaFactory, beanScope);
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

    public static BeanMetaFactory bean(String id, Class<?> clazz
            , ParamResourcesMetaFactory paramResourcesMetaFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory
            , BeanScope beanScope) {

        return new DSLBeanMetaFactoryImpl(id, clazz, paramResourcesMetaFactory, beanResourcesMetaFactory, beanScope);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory
            , BeanScope beanScope) {
        return bean(id, clazz, ParamResourcesMetaFactory.EMPTY, beanResourcesMetaFactory, beanScope);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, ParamResourcesMetaFactory paramResourcesMetaFactory
            , BeanScope beanScope) {
        return bean(id, clazz, paramResourcesMetaFactory, BeanResourcesMetaFactory.EMPTY, beanScope);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, ParamResourcesMetaFactory paramResourcesMetaFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return bean(id, clazz, paramResourcesMetaFactory, beanResourcesMetaFactory, BeanScope.SINGLETON);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return bean(id, clazz, beanResourcesMetaFactory, BeanScope.SINGLETON);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, ParamResourcesMetaFactory paramResourcesMetaFactory) {
        return bean(id, clazz, paramResourcesMetaFactory, BeanResourcesMetaFactory.EMPTY, BeanScope.SINGLETON);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanScope scope) {
        return bean(id, clazz, ParamResourcesMetaFactory.EMPTY, BeanResourcesMetaFactory.EMPTY, scope);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz) {
        return bean(id, clazz, BeanResourcesMetaFactory.EMPTY);
    }

    public static ParamResourcesMetaFactory constructor(ParamResourceMetaFactory... paramResourceMetaFactories) {
        return new DSLParamResourcesMetaFactoryImpl(Arrays.asList(paramResourceMetaFactories));
    }

    public static ParamResourceMetaFactory arg(String id) {
        return new DSLParamResourceMetaFactoryImpl(id);
    }


    public static BeanResourcesMetaFactory resources(ResourceMetaFactory... resourceMetaFactories) {
        return new DSLBeanResourcesMetaFactoryImpl(Arrays.asList(resourceMetaFactories));
    }

    public static ResourceMetaFactory resource(String id, String propertyName) {
        return new DSLResourceMetaFactory(id, propertyName);
    }
}
