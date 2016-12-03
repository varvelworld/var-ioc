package io.github.varvelworld.var.ioc.core;

import io.github.varvelworld.var.ioc.meta.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Ioc容器接口实现
 * Created by luzhonghao on 2016/11/26.
 */
public class IocContainerImpl implements IocContainer {

    final private Map<String, BeanFactory> beanMap = new HashMap<>();
    final private List<BeanFactory> notInstanceSingletonBeanList = new ArrayList<>();

    @Override
    public void loadMeta(BeansMeta beansMeta) {
        beansMeta.getBeanMetaList().forEach(this::loadMeta);
    }

    @Override
    public void loadMeta(BeanMeta beanMeta) {
        BeanFactory beanFactory = new BeanFactoryWithInjectImpl(beanMeta.beanFactory()
                , beanMeta.beanResourcesMetaFactory(), this);
        if(beanMap.putIfAbsent(beanMeta.getId(), beanFactory)
                != null) {
            throw new RuntimeException("duplication id");
        }
        notInstanceSingletonBeanList.add(beanFactory);
    }

    @Override
    public void refreshMeta() {
        /** 实例化单例bean **/
        notInstanceSingletonBeanList.forEach(BeanFactory::bean);
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id).bean();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getBean(String id, Class<T> clazz) {
        return (T) getBean(id);
    }
}
