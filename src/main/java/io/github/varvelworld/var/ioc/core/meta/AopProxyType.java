package io.github.varvelworld.var.ioc.core.meta;

import io.github.varvelworld.var.ioc.aop.impl.AopProxyJdkDynamicFactoryImpl;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.impl.BeanFactoryWithAopImpl;
import io.github.varvelworld.var.ioc.exception.NotImplementedException;

/**
 * Created by luzhonghao on 2017/1/28.
 */
public enum AopProxyType {
    /**
     * jdk动态代理
     */
    JDK_DYNAMIC;

    public BeanFactory wrap(BeanFactory beanFactory) {
        switch (this) {
            case JDK_DYNAMIC:
                return new BeanFactoryWithAopImpl(beanFactory, new AopProxyJdkDynamicFactoryImpl());
            default:
                throw new NotImplementedException();
        }
    }
}
