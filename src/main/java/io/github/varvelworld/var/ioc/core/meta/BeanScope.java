package io.github.varvelworld.var.ioc.core.meta;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.SingletonBeanFactoryImpl;
import io.github.varvelworld.var.ioc.exception.NotImplementedException;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public enum BeanScope {
    /**
     * 单例
     */
    SINGLETON,
    /**
     * 原型
     */
    PROTOTYPE,
    /**
     * 线程
     */
    THREAD,
    ;

    public BeanFactory wrap(BeanFactory beanFactory) {
        switch (this) {
            case SINGLETON:
                return new SingletonBeanFactoryImpl(beanFactory);
            case PROTOTYPE:
                return beanFactory;
            default:
                throw new NotImplementedException();
        }
    }
}
