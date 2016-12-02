package io.github.varvelworld.var.ioc.meta;

import io.github.varvelworld.var.ioc.core.BeanContext;
import io.github.varvelworld.var.ioc.meta.factory.AnnotationBeanResourcesMetaFactoryImpl;
import io.github.varvelworld.var.ioc.meta.factory.AnnotationBeansMetaFactoryImpl;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeansMetaFactory;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocMeta {
    public static BeansMetaFactory createBeansMetaFactoryByAnnotation(Class<?> clazz) {
        return new AnnotationBeansMetaFactoryImpl(clazz);
    }

    public static BeansMeta createBeansMetaByAnnotation(Class<?> clazz) {
        return createBeansMetaFactoryByAnnotation(clazz).createBeansMeta();
    }

    public static BeanResourcesMetaFactory createBeanResourcesMetaFactoryByAnnotation() {
        return new AnnotationBeanResourcesMetaFactoryImpl();
    }

    public static BeanResourcesMeta createBeanResourcesMetaByAnnotation(BeanContext beanContext) {
        return createBeanResourcesMetaFactoryByAnnotation().createBeanResourcesMeta(beanContext);
    }
}
