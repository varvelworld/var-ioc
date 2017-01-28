package io.github.varvelworld.var.ioc.core.impl;

import io.github.varvelworld.var.ioc.aop.AopProxyFactory;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.IocContainer;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public class BeanFactoryWithAopImpl implements BeanFactory {

    final private BeanFactory beanFactory;
    final private AopProxyFactory aopProxyFactory;

    public BeanFactoryWithAopImpl(BeanFactory beanFactory, AopProxyFactory aopProxyFactory) {
        this.beanFactory = beanFactory;
        this.aopProxyFactory = aopProxyFactory;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        return aopProxyFactory
                .aopProxy(beanFactory.bean(iocContainer)
                        , iocContainer.advisors())
                .proxy();
    }
}
