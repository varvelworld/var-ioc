package io.github.varvelworld.var.ioc.core.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanInjector;
import io.github.varvelworld.var.ioc.core.impl.BeanInjectorWithPropertyNameImpl;
import io.github.varvelworld.var.ioc.core.meta.ResourceMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.ResourceMetaFactory;

import java.util.function.Supplier;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLResourceMetaFactory implements ResourceMetaFactory {

    final private Supplier<ResourceMeta> resourceMeta;

    public DSLResourceMetaFactory(String id, String propertyName) {
        BeanInjector beanInjector
                = new BeanInjectorWithPropertyNameImpl(propertyName);
        this.resourceMeta = () -> new ResourceMeta(id, beanInjector);
    }

    @Override
    public ResourceMeta resourceMeta() {
        return resourceMeta.get();
    }
}
