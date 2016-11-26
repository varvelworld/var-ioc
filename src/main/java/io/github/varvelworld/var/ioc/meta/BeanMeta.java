package io.github.varvelworld.var.ioc.meta;

import io.github.varvelworld.var.ioc.core.BeanFactory;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeanMeta implements BeanFactory {
    final private String id;
    final private BeanFactory beanFactory;

    public BeanMeta(String id, BeanFactory beanFactory) {
        this.id = id;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object createBean() {
        return beanFactory.createBean();
    }

    public String getId() {
        return id;
    }
}
