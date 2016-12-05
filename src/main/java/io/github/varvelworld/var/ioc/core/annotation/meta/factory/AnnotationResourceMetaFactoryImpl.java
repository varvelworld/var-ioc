package io.github.varvelworld.var.ioc.core.annotation.meta.factory;

import io.github.varvelworld.var.ioc.core.annotation.Resource;
import io.github.varvelworld.var.ioc.core.BeanInjector;
import io.github.varvelworld.var.ioc.core.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.ResourceMetaFactory;

import java.lang.reflect.Field;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationResourceMetaFactoryImpl implements ResourceMetaFactory {

    private final ResourceMeta resourceMeta;

    public AnnotationResourceMetaFactoryImpl(Resource annotation, Field field) {
        BeanInjector beanInjector = (bean, injectBean) -> {
            try {
                field.setAccessible(true);
                field.set(bean, injectBean);
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
        String id = annotation.value().isEmpty() ? field.getName() : annotation.value();
        this.resourceMeta = new ResourceMeta(id, beanInjector);
    }

    @Override
    public ResourceMeta resourceMeta() {
        return resourceMeta;
    }
}
