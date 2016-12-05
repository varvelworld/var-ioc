package io.github.varvelworld.var.ioc.core.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.meta.BeanResourcesMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.ResourceMetaFactory;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanResourcesMetaFactoryImpl implements BeanResourcesMetaFactory {
    final private Supplier<BeanResourcesMeta> beanResourcesMeta;

    public DSLBeanResourcesMetaFactoryImpl(List<ResourceMetaFactory> resourceMetaFactoryList) {
        this.beanResourcesMeta = () -> new BeanResourcesMeta(resourceMetaFactoryList.stream()
                .map(ResourceMetaFactory::resourceMeta)
                .collect(Collectors.toList()));
    }

    @Override
    public Function<Object, BeanResourcesMeta> beanResourcesMeta() {
        return (bean) -> beanResourcesMeta.get();
    }
}
