package io.github.varvelworld.var.ioc.meta.factory;

import io.github.varvelworld.var.ioc.meta.ParamResourcesMeta;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public interface ParamResourcesMetaFactory {

    ParamResourcesMetaFactory EMPTY = () -> ParamResourcesMeta.EMPTY;

    ParamResourcesMeta paramResourcesMeta();
}
