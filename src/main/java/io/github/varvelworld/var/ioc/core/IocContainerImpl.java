package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.*;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    final private Map<String, BeanContext> beanMap = new HashMap<>();

    @Override
    public void addBeans(BeansMeta beansMeta) {
        beansMeta.getBeanMetaList().forEach(this::addBean);
    }

    @Override
    public void addBean(BeanMeta beanMeta) {
        if(beanMap.putIfAbsent(beanMeta.getId(), new BeanContext(beanMeta, beanMeta.createBean())) != null) {
            throw new RuntimeException("duplication id");
        }

    }

    @Override
    public void injectBeans() {
        for(Map.Entry<String, BeanContext> entry : beanMap.entrySet()) {
            BeanContext beanContext = entry.getValue();
            Object bean = beanContext.getBean();
            BeanMeta beanMeta = beanContext.getBeanMeta();
            BeanResourcesMeta beanResourcesMeta = beanMeta.createBeanResourcesMeta(beanContext);
            for (ResourceMeta resourceMeta : beanResourcesMeta.getResourceMetaList()) {
                resourceMeta.getBeanInjector().inject(bean, getBean(resourceMeta.getId()));
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id).getBean();
    }
}
