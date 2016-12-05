package io.github.varvelworld.var.ioc.core.dsl.meta.factory;

import io.github.varvelworld.var.ioc.core.meta.BeansMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.core.meta.factory.BeansMetaFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class DSLBeansMetaFactoryImpl implements BeansMetaFactory {

    final private List<BeanMetaFactory> beanMetaFactoryList;

    public DSLBeansMetaFactoryImpl(List<BeanMetaFactory> beanMetaFactoryList) {
        this.beanMetaFactoryList = beanMetaFactoryList;
    }

    @Override
    public BeansMeta beansMeta() {
        return new BeansMeta(beanMetaFactoryList
                .stream()
                .map(BeanMetaFactory::beanMeta)
                .collect(Collectors.toList()));
    }
}
