package io.github.varvelworld.var.ioc.aop.impl;

import io.github.varvelworld.var.ioc.aop.AopProxyFactory;

/**
 * jdk动态aop代理工厂
 * Created by luzhonghao on 2017/1/25.
 */
public class AopProxyJdkDynamicFactoryImpl extends AopProxyFactoryFilterAdvisorImpl implements AopProxyFactory {
    public AopProxyJdkDynamicFactoryImpl() {
        super(AopProxyJdkDynamicImpl::new);
    }
}
