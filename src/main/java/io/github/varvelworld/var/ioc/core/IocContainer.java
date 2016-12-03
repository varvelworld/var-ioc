package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.BeansMeta;

/**
 * Ioc容器接口
 * Created by luzhonghao on 2016/11/26.
 */
public interface IocContainer {
    void loadMeta(BeansMeta beansMeta);
    void loadMeta(BeanMeta beanMeta);
    void refreshMeta();
    Object getBean(String id);
    <T> T getBean(String id, Class<T> clazz);
}
