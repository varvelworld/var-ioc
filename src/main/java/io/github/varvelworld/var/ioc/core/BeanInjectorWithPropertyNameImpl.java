package io.github.varvelworld.var.ioc.core;

import java.lang.reflect.Field;

/**
 * Created by luzhonghao on 2017/1/27.
 */
public class BeanInjectorWithPropertyNameImpl implements BeanInjector {

    final private String propertyName;

    public BeanInjectorWithPropertyNameImpl(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public void inject(Object bean, Object injectBean) {
        try {
            Field field = bean.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(bean, injectBean);
            field.setAccessible(false);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
