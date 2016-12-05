package io.github.varvelworld.var.ioc.core.annotation.meta.factory;

import io.github.varvelworld.var.ioc.core.annotation.Resource;
import io.github.varvelworld.var.ioc.core.meta.BeanResourcesMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.ResourceMetaFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanResourcesMetaFactoryImpl implements BeanResourcesMetaFactory {

    @Override
    public Function<Object, BeanResourcesMeta> beanResourcesMeta() {
        return (bean) -> {
            List<ResourceMetaFactory> resourceMetaFactoryList = new ArrayList<>();
            for (Field field : bean.getClass().getDeclaredFields()) {
                Resource annotation = field.getAnnotation(Resource.class);
                if (annotation == null) {
                    continue;
                }
                ResourceMetaFactory resourceMetaFactory = new AnnotationResourceMetaFactoryImpl(annotation, field);
                resourceMetaFactoryList.add(resourceMetaFactory);
            }
            return new BeanResourcesMeta(resourceMetaFactoryList.stream()
                    .map(ResourceMetaFactory::resourceMeta)
                    .collect(Collectors.toList()));
        };
    }
}
