package io.github.varvelworld.var.ioc.core.impl;

import io.github.varvelworld.var.ioc.core.BeanInjector;

import java.lang.reflect.Field;

/**
 * Created by luzhonghao on 2017/1/27.
 */
public class BeanInjectorWithFieldImpl implements BeanInjector {

    private final Field field;

    public BeanInjectorWithFieldImpl(Field field) {
        this.field = field;
    }

    @Override
    public void inject(Object bean, Object injectBean) {
        try {
            field.setAccessible(true);
            field.set(bean, injectBean);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
