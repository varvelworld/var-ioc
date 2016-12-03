package io.github.varvelworld.var.ioc.meta;

import io.github.varvelworld.var.ioc.core.BeanContext;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeanMeta implements BeanFactory, BeanResourcesMetaFactory {
    final private String id;
    final private BeanFactory beanFactory;
    final private BeanResourcesMetaFactory beanResourcesMetaFactory;

    public BeanMeta(String id, BeanFactory beanFactory, BeanResourcesMetaFactory beanResourcesMetaFactory) {
        this.id = id;
        this.beanFactory = beanFactory;
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
    }

    @Override
    public Object createBean() {
        return beanFactory.createBean();
    }

    public String getId() {
        return id;
    }

    @Override
    public BeanResourcesMeta beanResourcesMeta(BeanContext beanContext) {
        return beanResourcesMetaFactory.beanResourcesMeta(beanContext);
    }
}
