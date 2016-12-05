package io.github.varvelworld.var.ioc.core.meta.factory;

import io.github.varvelworld.var.ioc.core.meta.BeanResourcesMeta;

import java.util.function.Function;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public interface BeanResourcesMetaFactory {

    BeanResourcesMetaFactory EMPTY = () -> (bean) -> BeanResourcesMeta.EMPTY;

    Function<Object, BeanResourcesMeta> beanResourcesMeta();
}
