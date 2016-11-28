package io.github.varvelworld.var.ioc.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Resource;
import io.github.varvelworld.var.ioc.meta.BeanResourcesMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanResourcesMetaFactoryImpl implements BeanResourcesMetaFactory {
    @Override
    public BeanResourcesMeta createBeanResourcesMeta(Object bean) {
        List<ResourceMetaFactory> resourceMetaFactoryList = new ArrayList<>();
        for(Field field : bean.getClass().getDeclaredFields()) {
            Resource annotation = field.getAnnotation(Resource.class);
            if(annotation == null) {
                continue;
            }
            ResourceMetaFactory resourceMetaFactory = new AnnotationResourceMetaFactoryImpl(annotation, bean, field);
            resourceMetaFactoryList.add(resourceMetaFactory);
        }
        return new BeanResourcesMeta(resourceMetaFactoryList.stream()
                .map(ResourceMetaFactory::createResourceMeta)
                .collect(Collectors.toList()));
    }
}
