package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.*;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    final private Map<String, Object> beanMap = new HashMap<>();
    final private BeanResourcesMetaFactory beanResourcesMetaFactory;

    public IocContainerImpl(BeanResourcesMetaFactory beanResourcesMetaFactory) {
        this.beanResourcesMetaFactory = beanResourcesMetaFactory;
    }


    @Override
    public void addBeans(BeansMeta beansMeta) {
        beansMeta.getBeanMetaList().forEach(this::addBean);
    }

    @Override
    public void addBean(BeanMeta beanMeta) {
        if(beanMap.putIfAbsent(beanMeta.getId(), beanMeta.createBean()) != null) {
            throw new RuntimeException("duplication id");
        }

    }

    @Override
    public void injectBeans() {
        for(Map.Entry<String, Object> entry : beanMap.entrySet()) {
            BeanResourcesMeta beanResourcesMeta = beanResourcesMetaFactory.createBeanResourcesMeta(entry.getValue());
            for (ResourceMeta resourceMeta : beanResourcesMeta.getResourceMetaList()) {
                resourceMeta.getBeanInjector().inject(beanMap.get(resourceMeta.getId()));
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
