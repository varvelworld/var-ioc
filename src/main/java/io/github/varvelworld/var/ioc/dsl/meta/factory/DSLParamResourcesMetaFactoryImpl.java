package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.meta.ParamResourcesMeta;
import io.github.varvelworld.var.ioc.meta.factory.ParamResourceMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.ParamResourcesMetaFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class DSLParamResourcesMetaFactoryImpl implements ParamResourcesMetaFactory {

    final private ParamResourcesMeta paramResourcesMeta;

    public DSLParamResourcesMetaFactoryImpl(List<ParamResourceMetaFactory> paramResourceMetaFactoryList) {
        this.paramResourcesMeta = new ParamResourcesMeta(paramResourceMetaFactoryList
                .stream().map(ParamResourceMetaFactory::paramResourceMeta)
                .collect(Collectors.toList())
        );
    }

    @Override
    public ParamResourcesMeta paramResourcesMeta() {
        return paramResourcesMeta;
    }
}
