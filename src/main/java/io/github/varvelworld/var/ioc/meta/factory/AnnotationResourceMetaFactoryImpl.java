package io.github.varvelworld.var.ioc.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Resource;
import io.github.varvelworld.var.ioc.core.BeanInjector;
import io.github.varvelworld.var.ioc.meta.ResourceMeta;

import java.lang.reflect.Field;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationResourceMetaFactoryImpl implements ResourceMetaFactory {

    final Resource annotation;
    final Object bean;
    final BeanInjector beanInjector ;
    final String id;

    public AnnotationResourceMetaFactoryImpl(Resource annotation, Object bean, Field field) {
        this.annotation = annotation;
        this.bean = bean;
        this.beanInjector = injectBean -> {
            try {
                field.setAccessible(true);
                field.set(bean, injectBean);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        this.id = annotation.value().isEmpty() ? field.getName() : annotation.value();
    }

    @Override
    public ResourceMeta createResourceMeta() {
        return new ResourceMeta(this.id, beanInjector);
    }

    public Resource getAnnotation() {
        return annotation;
    }

    public Object getBean() {
        return bean;
    }

    public BeanInjector getBeanInjector() {
        return beanInjector;
    }

    public String getId() {
        return id;
    }
}
