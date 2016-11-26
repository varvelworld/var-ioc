package io.github.varvelworld.var.ioc.meta;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeansMetaFactories {
    public static BeansMetaFactory createBeansMetaFactoryByAnnotation(Class<?> clazz) {
        return new AnnotationBeansMetaFactoryImpl(clazz);
    }

    public static BeansMeta createBeansMetaByAnnotation(Class<?> clazz) {
        return createBeansMetaFactoryByAnnotation(clazz).createBeansMeta();
    }
}
