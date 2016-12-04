package io.github.varvelworld.var.ioc.annotation.meta.factory;

import io.github.varvelworld.var.ioc.meta.ParamResourcesMeta;
import io.github.varvelworld.var.ioc.meta.factory.ParamResourceMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.ParamResourcesMetaFactory;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class AnnotationParamResourcesMetaFactoryImpl implements ParamResourcesMetaFactory {

    private final ParamResourcesMeta paramResourcesMeta;

    public AnnotationParamResourcesMetaFactoryImpl(Parameter[] parameters) {
        this.paramResourcesMeta = new ParamResourcesMeta(
                Arrays.asList(parameters).stream()
                        .map(AnnotationParamResourceMetaFactoryImpl::new)
                        .map(ParamResourceMetaFactory::paramResourceMeta)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ParamResourcesMeta paramResourcesMeta() {
        return paramResourcesMeta;
    }
}
