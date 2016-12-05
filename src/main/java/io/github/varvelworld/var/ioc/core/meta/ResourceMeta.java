package io.github.varvelworld.var.ioc.core.meta;

import io.github.varvelworld.var.ioc.core.BeanInjector;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class ResourceMeta {
    final private String id;
    final private BeanInjector beanInjector;

    public ResourceMeta(String id, BeanInjector beanInjector) {
        this.id = id;
        this.beanInjector = beanInjector;
    }

    public String getId() {
        return id;
    }

    public BeanInjector getBeanInjector() {
        return beanInjector;
    }
}
