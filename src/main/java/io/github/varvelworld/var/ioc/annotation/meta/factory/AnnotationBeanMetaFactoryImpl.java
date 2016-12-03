package io.github.varvelworld.var.ioc.annotation.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanMetaFactoryImpl implements BeanMetaFactory {

    final private BeanMeta beanMeta;

    public AnnotationBeanMetaFactoryImpl(Bean annotation, final Object target, final Method method) {
        String beanId = annotation.value().isEmpty() ? method.getName() : annotation.value();
        BeanFactory beanFactory = annotation.socpe().wrap(() -> {
            try {
                return method.invoke(target);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        BeanResourcesMetaFactory beanResourcesMetaFactory = new AnnotationBeanResourcesMetaFactoryImpl();
        this.beanMeta = new BeanMeta(beanId, beanFactory, beanResourcesMetaFactory, annotation.socpe());
    }

    public BeanMeta beanMeta() {
        return beanMeta;
    }
}
