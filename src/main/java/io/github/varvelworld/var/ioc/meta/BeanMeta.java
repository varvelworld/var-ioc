package io.github.varvelworld.var.ioc.meta;

import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithInjectImpl;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeanMeta {
    final private String id;
    final private BeanFactory beanFactory;

    public BeanMeta(String id, BeanFactory beanFactory, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        this.id = id;
        this.beanFactory = new BeanFactoryWithInjectImpl(beanFactory, beanResourcesMetaFactory);
    }

    public String getId() {
        return id;
    }

    public BeanFactory beanFactory() {
        return beanFactory;
    }
}
