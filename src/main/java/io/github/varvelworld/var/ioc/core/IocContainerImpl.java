package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Ioc容器接口实现
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    final private Map<String, BeanContext> beanMap = new HashMap<>();

    @Override
    public void loadMeta(BeansMeta beansMeta) {
        beansMeta.getBeanMetaList().forEach(this::loadMeta);
    }

    @Override
    public void loadMeta(BeanMeta beanMeta) {
        if(beanMap.putIfAbsent(beanMeta.getId(), new BeanContext(beanMeta, beanMeta.createBean())) != null) {
            throw new RuntimeException("duplication id");
        }

    }

    @Override
    public void refreshMeta() {
        for(Map.Entry<String, BeanContext> entry : beanMap.entrySet()) {
            BeanContext beanContext = entry.getValue();
            Object bean = beanContext.getBean();
            BeanMeta beanMeta = beanContext.getBeanMeta();
            BeanResourcesMeta beanResourcesMeta = beanMeta.beanResourcesMeta(beanContext);
            for (ResourceMeta resourceMeta : beanResourcesMeta.getResourceMetaList()) {
                resourceMeta.getBeanInjector().inject(bean, getBean(resourceMeta.getId()));
            }
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id).getBean();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String id, Class<T> clazz) {
        return (T) getBean(id);
    }
}
