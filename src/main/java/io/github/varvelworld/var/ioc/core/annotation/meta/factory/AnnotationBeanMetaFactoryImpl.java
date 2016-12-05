package io.github.varvelworld.var.ioc.core.annotation.meta.factory;

import io.github.varvelworld.var.ioc.core.annotation.Bean;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithInjectImpl;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithParametersMethodImpl;
import io.github.varvelworld.var.ioc.core.meta.BeanMeta;
import io.github.varvelworld.var.ioc.core.meta.ParamResourceMeta;
import io.github.varvelworld.var.ioc.core.meta.factory.BeanMetaFactory;

import java.lang.reflect.Method;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanMetaFactoryImpl implements BeanMetaFactory {

    final private Supplier<BeanMeta> beanMeta;

    public AnnotationBeanMetaFactoryImpl(final Object beansInstance, final Method method) {
        this(beansInstance, method, method.getAnnotation(Bean.class));
    }

    public AnnotationBeanMetaFactoryImpl(final Object beansInstance, final Method method, Bean annotation) {
        this.beanMeta = () -> new BeanMeta(annotation.value().isEmpty() ? method.getName() : annotation.value()
                , new BeanFactoryWithInjectImpl(
                    annotation.socpe().wrap(
                            new BeanFactoryWithParametersMethodImpl(beansInstance
                                    , method
                                    , new AnnotationParamResourcesMetaFactoryImpl(method.getParameters())
                                    .paramResourcesMeta()
                    .paramResourceMetaList().stream().map(ParamResourceMeta::beanGetter)
                    .collect(Collectors.toList())))
                , new AnnotationBeanResourcesMetaFactoryImpl().beanResourcesMeta()));
    }

    public BeanMeta beanMeta() {
        return beanMeta.get();
    }
}
