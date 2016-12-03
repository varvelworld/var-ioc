package io.github.varvelworld.var.ioc.annotation.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanMetaFactoryImpl implements BeanMetaFactory {

    final private Bean annotation;
    final private Object target;
    final private Method method;
    final private BeanFactory beanFactory;
    final private String beanId;

    public AnnotationBeanMetaFactoryImpl(Bean annotation, final Object target, final Method method) {
        this.annotation = annotation;
        this.target = target;
        this.method = method;
        this.beanFactory = () -> {
            try {
                return method.invoke(target);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
        this.beanId = annotation.value().isEmpty() ? method.getName() : annotation.value();
    }

    public BeanMeta beanMeta() {
        return new BeanMeta(beanId, beanFactory, new AnnotationBeanResourcesMetaFactoryImpl());
    }
}
