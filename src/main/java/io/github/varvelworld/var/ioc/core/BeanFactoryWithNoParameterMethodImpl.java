package io.github.varvelworld.var.ioc.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class BeanFactoryWithNoParameterMethodImpl implements BeanFactory {

    final private Object instance;
    final private Method method;

    public BeanFactoryWithNoParameterMethodImpl(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        try {
            method.setAccessible(true);
            Object bean = method.invoke(instance);
            method.setAccessible(false);
            return bean;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
