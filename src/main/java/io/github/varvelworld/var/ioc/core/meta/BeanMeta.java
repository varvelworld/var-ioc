package io.github.varvelworld.var.ioc.core.meta;

import io.github.varvelworld.var.ioc.core.BeanFactory;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeanMeta {
    final private String id;
    final private BeanFactory beanFactory;

    public BeanMeta(String id, BeanFactory beanFactory) {
        this.id = id;
        this.beanFactory = beanFactory;
    }

    public String getId() {
        return id;
    }

    public BeanFactory beanFactory() {
        return beanFactory;
    }
}
