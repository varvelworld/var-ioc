package io.github.varvelworld.var.ioc.core.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryByConstructorImpl;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithInjectImpl;
import io.github.varvelworld.var.ioc.core.meta.BeanMeta;
import io.github.varvelworld.var.ioc.core.meta.BeanScope;
import io.github.varvelworld.var.ioc.core.meta.ParamResourcesMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.ParamResourcesMetaFactory;
import io.github.varvelworld.var.ioc.util.ClassUtils;

import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanMetaFactoryImpl implements BeanMetaFactory {

    final private Supplier<BeanMeta> beanMeta;

    public DSLBeanMetaFactoryImpl(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope scope) {
        this.beanMeta = () -> new BeanMeta(id, new BeanFactoryWithInjectImpl(
                scope.wrap(beanFactory)
                , beanResourcesMetaFactory));
    }

    public DSLBeanMetaFactoryImpl(String id, Class<?> clazz, ParamResourcesMetaFactory paramResourcesMetaFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope scope) {
        this(id, generateBeanFactoryByConstructor(clazz, paramResourcesMetaFactory), beanResourcesMetaFactory, scope);
    }

    private static BeanFactory generateBeanFactoryByConstructor(Class<?> clazz
            , ParamResourcesMetaFactory paramResourcesMetaFactory){
        ParamResourcesMeta paramResourcesMeta = paramResourcesMetaFactory.paramResourcesMeta();
        return new BeanFactoryByConstructorImpl(ClassUtils.getConstructorByArgCount(clazz
                , paramResourcesMeta.paramResourceMetaList().size())
                , paramResourcesMeta);
    }

    @Override
    public BeanMeta beanMeta() {
        return beanMeta.get();
    }
}
