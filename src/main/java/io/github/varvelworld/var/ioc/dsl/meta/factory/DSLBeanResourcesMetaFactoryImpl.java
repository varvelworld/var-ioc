package io.github.varvelworld.var.ioc.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.BeanContext;
import io.github.varvelworld.var.ioc.meta.BeanResourcesMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.ResourceMetaFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeanResourcesMetaFactoryImpl implements BeanResourcesMetaFactory {
    final private List<ResourceMetaFactory> resourceMetaFactoryList;

    public DSLBeanResourcesMetaFactoryImpl(List<ResourceMetaFactory> resourceMetaFactoryList) {
        this.resourceMetaFactoryList = resourceMetaFactoryList;
    }

    @Override
    public BeanResourcesMeta createBeanResourcesMeta(BeanContext beanContext) {
        return new BeanResourcesMeta(resourceMetaFactoryList.stream()
                .map(ResourceMetaFactory::createResourceMeta)
                .collect(Collectors.toList()));
    }
}
