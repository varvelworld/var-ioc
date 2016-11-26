package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.BeansMeta;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    private Map<String, Object> beanMap = new HashMap<>();

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
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
