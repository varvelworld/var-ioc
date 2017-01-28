package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.core.meta.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ioc容器接口实现
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    final private Map<String, BeanFactory> beanMap = new HashMap<>();
    final private List<BeanFactory> notInstanceSingletonBeanList = new ArrayList<>();
    final private List<Advisor> advisors = new ArrayList<>();

    @Override
    public IocContainer loadMeta(BeansMeta beansMeta) {
        beansMeta.getBeanMetaList().forEach(this::loadMeta);
        return this;
    }

    @Override
    public IocContainer loadMeta(BeanMeta beanMeta) {
        BeanFactory beanFactory = beanMeta.beanFactory();
        if(beanMap.putIfAbsent(beanMeta.getId(), beanFactory)
                != null) {
            throw new RuntimeException("duplication id");
        }
        notInstanceSingletonBeanList.add(beanFactory);
        return this;
    }

    @Override
    public IocContainer refreshMeta() {
        /** 实例化单例bean **/
        notInstanceSingletonBeanList.stream().forEach(beanFactory -> beanFactory.bean(this));
        return this;
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id).bean(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String id, Class<T> clazz) {
        return (T) getBean(id);
    }

    @Override
    public List<Advisor> advisors() {
        return advisors;
    }
}
