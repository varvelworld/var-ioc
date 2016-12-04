package io.github.varvelworld.var.ioc.core;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class SingletonBeanFactoryImpl implements BeanFactory {

    final private BeanFactory beanFactory;
    private Object bean;

    public SingletonBeanFactoryImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        if(bean == null) {
            synchronized (this) {
                if(bean == null) { // 双重检查加锁
                    bean = beanFactory.bean(iocContainer);
                }
            }
        }
        return bean;
    }
}
