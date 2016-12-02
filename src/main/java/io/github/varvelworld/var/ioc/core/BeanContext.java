package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.BeanMeta;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanContext {
    final private BeanMeta beanMeta;
    final private Object bean;

    public BeanContext(BeanMeta beanMeta, Object bean) {
        this.beanMeta = beanMeta;
        this.bean = bean;
    }

    public BeanMeta getBeanMeta() {
        return beanMeta;
    }

    public Object getBean() {
        return bean;
    }
}
