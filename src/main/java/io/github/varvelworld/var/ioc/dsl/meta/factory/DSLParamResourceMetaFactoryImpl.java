package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.meta.ParamResourceMeta;
import io.github.varvelworld.var.ioc.meta.factory.ParamResourceMetaFactory;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class DSLParamResourceMetaFactoryImpl implements ParamResourceMetaFactory {

    final private ParamResourceMeta paramResourceMeta;

    public DSLParamResourceMetaFactoryImpl(String id) {
        this.paramResourceMeta = new ParamResourceMeta(id);
    }

    @Override
    public ParamResourceMeta paramResourceMeta() {
        return paramResourceMeta;
    }
}
