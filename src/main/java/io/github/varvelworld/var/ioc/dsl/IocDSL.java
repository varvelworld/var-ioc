package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryByClassImpl;
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

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class IocDSL {

    public static BeansMetaFactory beans(BeanMetaFactory... beanMetaFactories) {
        return new DSLBeansMetaFactoryImpl(Arrays.asList(beanMetaFactories));
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope beanScope) {
        return new DSLBeanMetaFactoryImpl(id, beanFactory, beanResourcesMetaFactory, beanScope);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return bean(id, beanFactory, beanResourcesMetaFactory, BeanScope.SINGLETON);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanScope beanScope) {
        return bean(id, beanFactory, BeanResourcesMetaFactory.EMPTY, beanScope);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory) {
        return bean(id, beanFactory, BeanResourcesMetaFactory.EMPTY);
    }

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory
            , BeanScope beanScope) {
        return bean(id, new BeanFactoryByClassImpl(clazz), beanResourcesMetaFactory, beanScope);
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
