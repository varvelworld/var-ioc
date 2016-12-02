package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.core.BeanFactory;
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

    public static BeanMetaFactory bean(String id, Class<?> clazz, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return new DSLBeanMetaFactoryImpl(id, clazz, beanResourcesMetaFactory);
    }
    public static BeanMetaFactory bean(String id, Class<?> clazz) {
        return bean(id, clazz, BeanResourcesMetaFactory.EMPTY);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return new DSLBeanMetaFactoryImpl(id, beanFactory, beanResourcesMetaFactory);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory) {
        return bean(id, beanFactory, BeanResourcesMetaFactory.EMPTY);
    }

    public static BeanResourcesMetaFactory resources(ResourceMetaFactory... resourceMetaFactories) {
        return new DSLBeanResourcesMetaFactoryImpl(Arrays.asList(resourceMetaFactories));
    }

    public static ResourceMetaFactory resource(String id, String propertyName) {
        return new DSLResourceMetaFactory(id, propertyName);
    }
}
