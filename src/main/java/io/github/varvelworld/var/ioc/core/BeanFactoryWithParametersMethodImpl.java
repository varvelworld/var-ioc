package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.BeanResourcesMeta;
import io.github.varvelworld.var.ioc.meta.ParamResourcesMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class BeanFactoryWithParametersMethodImpl implements BeanFactory {

    final private Object instance;
    final private Method method;
    final private List<Function<IocContainer, Object>> beanGetters;

    public BeanFactoryWithParametersMethodImpl(Object instance, Method method
            , List<Function<IocContainer, Object>> beanGetters) {
        this.instance = instance;
        this.method = method;
        this.beanGetters = beanGetters;
    }

    @Override
    public Object bean(IocContainer iocContainer) {
        try {
            method.setAccessible(true);
            Object bean = method.invoke(instance, beanGetters.stream()
                    .map(beanGetter -> beanGetter.apply(iocContainer))
                    .collect(Collectors.toList()).toArray());
            method.setAccessible(false);
            return bean;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
