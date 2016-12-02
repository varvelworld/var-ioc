package io.github.varvelworld.var.ioc.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanContext;
import io.github.varvelworld.var.ioc.meta.BeanResourcesMeta;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public interface BeanResourcesMetaFactory {
    BeanResourcesMeta createBeanResourcesMeta(BeanContext beanContext);
}
