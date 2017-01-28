package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.core.meta.BeanMeta;
import io.github.varvelworld.var.ioc.core.meta.BeansMeta;

import java.util.List;

/**
 * Ioc容器接口
 * Created by luzhonghao on 2016/11/26.
 */
public interface IocContainer {
    IocContainer loadMeta(BeansMeta beansMeta);
    IocContainer loadMeta(BeanMeta beanMeta);
    IocContainer refreshMeta();
    Object getBean(String id);
    <T> T getBean(String id, Class<T> clazz);
    List<Advisor> advisors();
}
