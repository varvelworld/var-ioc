package io.github.varvelworld.var.ioc.core.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.impl.BeanFactoryWithInjectImpl;
import io.github.varvelworld.var.ioc.core.meta.AopProxyType;
import io.github.varvelworld.var.ioc.core.meta.BeanMeta;
import io.github.varvelworld.var.ioc.core.meta.BeanScope;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanResourcesMetaFactory;

import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanMetaFactoryImpl implements BeanMetaFactory {

    final private Supplier<BeanMeta> beanMeta;

    public DSLBeanMetaFactoryImpl(String id, BeanFactory beanFactory
            , BeanResourcesMetaFactory beanResourcesMetaFactory, BeanScope scope
            , AopProxyType aopProxyType) {
        /** 从内至外,先注入bean,再aop代理,最后scope包装 **/
        this.beanMeta = () -> new BeanMeta(id,
                scope.wrap(
                        aopProxyType.wrap(
                                new BeanFactoryWithInjectImpl(beanFactory, beanResourcesMetaFactory.beanResourcesMeta())))
                );
    }

    @Override
    public BeanMeta beanMeta() {
        return beanMeta.get();
    }
}
