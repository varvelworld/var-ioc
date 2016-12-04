package io.github.varvelworld.var.ioc.core;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class SingletonBeanFactoryImpl implements BeanFactory {

    final private BeanFactory beanFactory;
    final private AtomicReference<Object> beanRef;

    public SingletonBeanFactoryImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.beanRef = new AtomicReference<>();
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        return beanRef.updateAndGet((bean) -> bean == null ? beanFactory.bean(iocContainer) : bean);
    }
}
