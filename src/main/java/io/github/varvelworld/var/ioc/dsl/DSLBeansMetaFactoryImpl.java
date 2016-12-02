package io.github.varvelworld.var.ioc.dsl;

import io.github.varvelworld.var.ioc.meta.BeansMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeansMetaFactory;

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
    public BeansMeta createBeansMeta() {
        return new BeansMeta(beanMetaFactoryList
                .stream()
                .map(BeanMetaFactory::createBeanMeta)
                .collect(Collectors.toList()));
    }
}
