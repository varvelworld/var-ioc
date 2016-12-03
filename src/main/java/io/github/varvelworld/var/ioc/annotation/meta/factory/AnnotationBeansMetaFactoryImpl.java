package io.github.varvelworld.var.ioc.annotation.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.annotation.Beans;
import io.github.varvelworld.var.ioc.meta.BeansMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeansMetaFactory;
import io.github.varvelworld.var.ioc.util.Preconditions;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeansMetaFactoryImpl implements BeansMetaFactory {

    final private BeansMeta beansMeta;

    public AnnotationBeansMetaFactoryImpl(Class<?> clazz) {
        this(clazz, clazz.getAnnotation(Beans.class));
    }

    public AnnotationBeansMetaFactoryImpl(Class<?> clazz, Beans annotation) {
        try {
            this.beansMeta = new BeansMeta(generateBeanMetaFactoryList(clazz, clazz.newInstance())
                    .stream()
                    .map(BeanMetaFactory::beanMeta)
                    .collect(Collectors.toList()));
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<BeanMetaFactory> generateBeanMetaFactoryList(Class<?> clazz, Object beansInstance) {
        List<BeanMetaFactory> list = new ArrayList<>();
        for(Method method : clazz.getMethods()) {
            Bean beanAnnotation = method.getAnnotation(Bean.class);
            if(beanAnnotation != null) {
                list.add(new AnnotationBeanMetaFactoryImpl(beanAnnotation, beansInstance, method));
            }
        }
        return list;
    }

    public BeansMeta beansMeta() {
        return beansMeta;
    }
}
