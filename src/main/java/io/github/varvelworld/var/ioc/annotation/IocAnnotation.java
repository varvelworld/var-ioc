package io.github.varvelworld.var.ioc.annotation;

import io.github.varvelworld.var.ioc.annotation.meta.factory.AnnotationBeansMetaFactoryImpl;
import io.github.varvelworld.var.ioc.meta.factory.BeansMetaFactory;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class IocAnnotation {

    public static BeansMetaFactory beans(Class<?> clazz) {
        return new AnnotationBeansMetaFactoryImpl(clazz);
    }
}
