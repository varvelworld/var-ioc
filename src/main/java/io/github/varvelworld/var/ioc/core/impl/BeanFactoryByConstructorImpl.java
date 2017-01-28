package io.github.varvelworld.var.ioc.core.impl;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.meta.ParamResourcesMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanFactoryByConstructorImpl implements BeanFactory {

    final private Constructor<?> constructor;
    final private ParamResourcesMeta paramResourcesMeta;

    public BeanFactoryByConstructorImpl(Class<?> clazz) {
        this(getNoParamConstructor(clazz), ParamResourcesMeta.EMPTY);
    }

    private static Constructor<?> getNoParamConstructor(Class<?> clazz) {
        try {
            return clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public BeanFactoryByConstructorImpl(Constructor<?> constructor, ParamResourcesMeta paramResourcesMeta) {
        this.constructor = constructor;
        this.paramResourcesMeta = paramResourcesMeta;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        try {
            return constructor.newInstance(
                    paramResourcesMeta.paramResourceMetaList()
                            .stream()
                            .map(paramResourceMeta -> paramResourceMeta.beanGetter().apply(iocContainer))
                            .collect(Collectors.toList())
                            .toArray()
            );
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
