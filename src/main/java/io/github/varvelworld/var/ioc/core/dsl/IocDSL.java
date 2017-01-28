package io.github.varvelworld.var.ioc.core.dsl;

import io.github.varvelworld.var.ioc.aop.Advice;
import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.aop.BeforeAdvice;
import io.github.varvelworld.var.ioc.aop.Pointcut;
import io.github.varvelworld.var.ioc.aop.impl.AdvisorImpl;
import io.github.varvelworld.var.ioc.aop.impl.SimplePointcutImpl;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryByConstructorImpl;
import io.github.varvelworld.var.ioc.core.dsl.meta.factory.*;
import io.github.varvelworld.var.ioc.core.meta.AopProxyType;
import io.github.varvelworld.var.ioc.core.meta.BeanScope;
import io.github.varvelworld.var.ioc.core.meta.ParamResourcesMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.*;
import io.github.varvelworld.var.ioc.util.ClassUtils;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class IocDSL {

    public static BeansMetaFactory beans(BeanMetaFactory... beanMetaFactories) {
        return new DSLBeansMetaFactoryImpl(Arrays.asList(beanMetaFactories));
    }

    private final static ParamResourcesMetaFactory DEFAULT_PARAM_RESOURCES_META_FACTORY
            = ParamResourcesMetaFactory.EMPTY;
    private final static BeanResourcesMetaFactory DEFAULT_BEAN_RESOURCES_META_FACTORY
            = BeanResourcesMetaFactory.EMPTY;
    private final static BeanScope DEFAULT_BEAN_SCOPE
            = BeanScope.SINGLETON;
    private final static AopProxyType DEFAULT_AOP_PROXY_TYPE
            = AopProxyType.JDK_DYNAMIC;

    public static BeanFactory factory(Class<?> clazz, ParamResourcesMetaFactory paramResourcesMetaFactory) {
        ParamResourcesMeta paramResourcesMeta = paramResourcesMetaFactory.paramResourcesMeta();
        return new BeanFactoryByConstructorImpl(ClassUtils.getConstructorByArgCount(clazz
                , paramResourcesMeta.paramResourceMetaList().size())
                , paramResourcesMeta);
    }

    public static BeanFactory factory(Class<?> clazz) {
        return factory(clazz, DEFAULT_PARAM_RESOURCES_META_FACTORY);
    }

    public static BeanFactory factory(Supplier<?> beanSupplier) {
        return iocContainer -> beanSupplier.get();
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory
            , BeanScope beanScope
            , AopProxyType aopProxyType) {
        return new DSLBeanMetaFactoryImpl(id, beanFactory
                , beanResourcesMetaFactory, beanScope, aopProxyType);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanScope beanScope
            , AopProxyType aopProxyType) {
        return bean(id, beanFactory, DEFAULT_BEAN_RESOURCES_META_FACTORY, beanScope, aopProxyType);
    }


    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory
            , AopProxyType aopProxyType) {
        return bean(id, beanFactory, beanResourcesMetaFactory, DEFAULT_BEAN_SCOPE, aopProxyType);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory
            , BeanScope beanScope) {
        return bean(id, beanFactory, beanResourcesMetaFactory, beanScope, DEFAULT_AOP_PROXY_TYPE);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , AopProxyType aopProxyType) {
        return bean(id, beanFactory, DEFAULT_BEAN_RESOURCES_META_FACTORY, DEFAULT_BEAN_SCOPE, aopProxyType);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanScope beanScope) {
        return bean(id, beanFactory, DEFAULT_BEAN_RESOURCES_META_FACTORY, beanScope, DEFAULT_AOP_PROXY_TYPE);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory) {
        return bean(id, beanFactory, beanResourcesMetaFactory, DEFAULT_BEAN_SCOPE, DEFAULT_AOP_PROXY_TYPE);
    }

    public static BeanMetaFactory bean(String id, BeanFactory beanFactory) {
        return bean(id, beanFactory, DEFAULT_BEAN_RESOURCES_META_FACTORY, DEFAULT_BEAN_SCOPE, DEFAULT_AOP_PROXY_TYPE);
    }

    public static ParamResourcesMetaFactory constructor(ParamResourceMetaFactory... paramResourceMetaFactories) {
        return new DSLParamResourcesMetaFactoryImpl(Arrays.asList(paramResourceMetaFactories));
    }

    public static ParamResourceMetaFactory arg(String id) {
        return new DSLParamResourceMetaFactoryImpl(id);
    }


    public static BeanResourcesMetaFactory properties(ResourceMetaFactory... resourceMetaFactories) {
        return new DSLBeanResourcesMetaFactoryImpl(Arrays.asList(resourceMetaFactories));
    }

    public static ResourceMetaFactory property(String id, String propertyName) {
        return new DSLResourceMetaFactory(id, propertyName);
    }

    public static Advisor advisor(Advice advice, Pointcut pointcut) {
        return new AdvisorImpl(advice, pointcut);
    }

    public static BeforeAdvice before(BeforeAdvice beforeAdvice) {
        return beforeAdvice;
    }

    public static Pointcut simplePointcut(String classSimpleName, String methodName) {
        return new SimplePointcutImpl(classSimpleName, methodName);
    }
}
