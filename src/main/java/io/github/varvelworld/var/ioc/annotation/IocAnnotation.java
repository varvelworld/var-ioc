package io.github.varvelworld.var.ioc.annotation;

import io.github.varvelworld.var.ioc.annotation.AnnotationBeansMetaFactoryImpl;
import io.github.varvelworld.var.ioc.meta.BeansMeta;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocAnnotation {

    public static BeansMeta beansMetaByAnnotation(Class<?> clazz) {
        return new AnnotationBeansMetaFactoryImpl(clazz).createBeansMeta();
    }
}
