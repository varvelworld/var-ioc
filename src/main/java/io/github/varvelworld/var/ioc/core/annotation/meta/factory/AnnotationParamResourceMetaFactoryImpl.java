package io.github.varvelworld.var.ioc.core.annotation.meta.factory;

import io.github.varvelworld.var.ioc.core.annotation.Resource;
import io.github.varvelworld.var.ioc.core.meta.ParamResourceMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.ParamResourceMetaFactory;

import java.lang.reflect.Parameter;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class AnnotationParamResourceMetaFactoryImpl implements ParamResourceMetaFactory {

    private final ParamResourceMeta paramResourceMeta;

    public AnnotationParamResourceMetaFactoryImpl(Parameter parameter) {
        this(parameter, parameter.getAnnotation(Resource.class));
    }

    public AnnotationParamResourceMetaFactoryImpl(Parameter parameter, Resource annotation) {
        String id = annotation.value();
        if(id.isEmpty()) {
            if(parameter.isNamePresent()){
                id = parameter.getName();
            }
            else {
                throw new RuntimeException("id is empty");
            }
        }
        this.paramResourceMeta = new ParamResourceMeta(id);
    }

    @Override
    public ParamResourceMeta paramResourceMeta() {
        return paramResourceMeta;
    }
}
