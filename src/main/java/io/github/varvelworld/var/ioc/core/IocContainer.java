package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.BeansMeta;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public interface IocContainer {
    void addBeans(BeansMeta beansMeta);
    void addBean(BeanMeta beanMeta);
    Object getBean(String id);
}
