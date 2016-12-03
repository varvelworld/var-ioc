package io.github.varvelworld.var.ioc.core;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class SingletonBeanFactoryImpl implements BeanFactory {

    final private Object bean;

    public SingletonBeanFactoryImpl(BeanFactory beanFactory) {
        this.bean = beanFactory.bean();
    }

    @Override
    public Object bean() {
        return bean;
    }
}
