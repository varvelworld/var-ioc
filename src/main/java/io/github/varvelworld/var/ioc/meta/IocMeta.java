package io.github.varvelworld.var.ioc.meta;

import io.github.varvelworld.var.ioc.annotation.AnnotationBeansMetaFactoryImpl;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocMeta {

    public static BeansMeta beansMetaByAnnotation(Class<?> clazz) {
        return new AnnotationBeansMetaFactoryImpl(clazz).createBeansMeta();
    }
}
