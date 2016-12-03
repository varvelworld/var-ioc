package io.github.varvelworld.var.ioc.annotation.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Resource;
import io.github.varvelworld.var.ioc.core.BeanInjector;
import io.github.varvelworld.var.ioc.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.meta.factory.ResourceMetaFactory;

import java.lang.reflect.Field;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationResourceMetaFactoryImpl implements ResourceMetaFactory {

    final Resource annotation;
    final BeanInjector beanInjector ;
    final String id;

    public AnnotationResourceMetaFactoryImpl(Resource annotation, Field field) {
        this.annotation = annotation;
        this.beanInjector = (bean, injectBean) -> {
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
    public ResourceMeta resourceMeta() {
        return new ResourceMeta(this.id, beanInjector);
    }

    public Resource getAnnotation() {
        return annotation;
    }

    public BeanInjector getBeanInjector() {
        return beanInjector;
    }

    public String getId() {
        return id;
    }
}
