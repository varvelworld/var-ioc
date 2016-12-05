package io.github.varvelworld.var.ioc.core.annotation.meta.factory;

import io.github.varvelworld.var.ioc.core.annotation.Resource;
import io.github.varvelworld.var.ioc.core.meta.BeanResourcesMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.ResourceMetaFactory;
import io.github.varvelworld.var.ioc.util.ImmutablePair;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanResourcesMetaFactoryImpl implements BeanResourcesMetaFactory {

    @Override
    public Function<Object, BeanResourcesMeta> beanResourcesMeta() {
        return (bean) -> new BeanResourcesMeta(
                Arrays.asList(bean.getClass().getDeclaredFields())
                .stream()
                .map(field -> new ImmutablePair<>(field, field.getAnnotation(Resource.class)))
                .filter(pair -> pair.right() != null)
                .map(pair -> new AnnotationResourceMetaFactoryImpl(pair.right(), pair.left()))
                .map(ResourceMetaFactory::resourceMeta)
                .collect(Collectors.toList()));
    }
}
