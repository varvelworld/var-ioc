package io.github.varvelworld.var.ioc.core;

import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanSupplierByClassImpl<T> implements Supplier<T> {

    final Class<T> clazz;

    public BeanSupplierByClassImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T get() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
