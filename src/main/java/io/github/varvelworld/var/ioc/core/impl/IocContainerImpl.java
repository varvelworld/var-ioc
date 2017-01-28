package io.github.varvelworld.var.ioc.core.impl;

import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.IocContainer;
import io.github.varvelworld.var.ioc.core.meta.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Ioc容器接口实现
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    final private AtomicReference<Map<String, BeanFactory>> beanMap = new AtomicReference<>(new HashMap<>());
    private AtomicReference<Map<String, BeanFactory>> oldBeanMap = new AtomicReference<>(null);
    final private List<BeanFactory> notInstanceSingletonBeanList = new ArrayList<>();
    final private List<Advisor> advisors = new ArrayList<>();
    private boolean dirtyOfAdvisors = false;

    @Override
    public IocContainer loadMeta(BeansMeta beansMeta) {
        beansMeta.getBeanMetaList().forEach(this::loadMeta);
        return this;
    }

    @Override
    public IocContainer loadMeta(BeanMeta beanMeta) {
        BeanFactory beanFactory = beanMeta.beanFactory();
        if(beanMap.get().putIfAbsent(beanMeta.getId(), beanFactory)
                != null) {
            throw new RuntimeException("duplication id");
        }
        notInstanceSingletonBeanList.add(beanFactory);
        return this;
    }

    @Override
    public IocContainer loadAdvisor(Advisor advisor) {
        advisors.add(advisor);
        dirtyOfAdvisors = true;
        return this;
    }

    @Override
    public IocContainer refreshMeta() {
        if(dirtyOfAdvisors) {
            Map<String, BeanFactory> old = beanMap.get();
            oldBeanMap.set(old);
            beanMap.set(new HashMap<>(old));
            dirtyOfAdvisors = true;
        }
        /** 实例化单例bean **/
        notInstanceSingletonBeanList.stream().forEach(beanFactory -> beanFactory.bean(this));
        oldBeanMap.set(null);
        return this;
    }

    @Override
    public Object getBean(String id) {
        Map<String, BeanFactory> beanMap = oldBeanMap.get() != null
                ? oldBeanMap.get()
                : this.beanMap.get();
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
