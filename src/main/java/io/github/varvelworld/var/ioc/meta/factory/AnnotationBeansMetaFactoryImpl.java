package io.github.varvelworld.var.ioc.meta.factory;

import io.github.varvelworld.var.ioc.meta.BeansMeta;
import io.github.varvelworld.var.ioc.util.Preconditions;
import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.annotation.Beans;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeansMetaFactoryImpl implements BeansMetaFactory {

    final private Beans annotation;
    final private Class<?> clazz;
    final private Object beansInstance;
    final private List<BeanMetaFactory> beanMetaFactoryList;

    public AnnotationBeansMetaFactoryImpl(Class<?> clazz) {
        this(clazz, clazz.getAnnotation(Beans.class));
    }

    public AnnotationBeansMetaFactoryImpl(Class<?> clazz, Beans annotation) {
        try {
            Preconditions.check(annotation != null, "annotation != null");
            this.annotation = annotation;
            this.clazz = clazz;
            this.beansInstance = clazz.newInstance();
            this.beanMetaFactoryList = Collections.unmodifiableList(generateBeanMetaFactoryList(clazz, beansInstance));
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<BeanMetaFactory> generateBeanMetaFactoryList(Class<?> clazz, Object beansInstance) {
        List<BeanMetaFactory> list = new ArrayList<BeanMetaFactory>();
        for(Method method : clazz.getMethods()) {
            Bean beanAnnotation = method.getAnnotation(Bean.class);
            if(beanAnnotation != null) {
                list.add(new AnnotationBeanMetaFactoryImpl(beanAnnotation, beansInstance, method));
            }
        }
        return list;
    }

    public BeansMeta createBeansMeta() {
        return new BeansMeta(beanMetaFactoryList
                .stream()
                .map(BeanMetaFactory::createBeanMeta)
                .collect(Collectors.toList()));
    }
}
