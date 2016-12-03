package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanInjector;
import io.github.varvelworld.var.ioc.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.meta.factory.ResourceMetaFactory;

import java.lang.reflect.Field;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLResourceMetaFactory implements ResourceMetaFactory {

    final private ResourceMeta resourceMeta;

    public DSLResourceMetaFactory(String id, String propertyName) {
        BeanInjector beanInjector = (bean, injectBean) -> {
            try {
                Field field = bean.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(bean, injectBean);
                field.setAccessible(false);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        };
        this.resourceMeta = new ResourceMeta(id, beanInjector);
    }

    @Override
    public ResourceMeta resourceMeta() {
        return resourceMeta;
    }
}
