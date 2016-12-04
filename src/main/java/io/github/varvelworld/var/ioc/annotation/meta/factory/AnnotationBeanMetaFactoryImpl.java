package io.github.varvelworld.var.ioc.annotation.meta.factory;

import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.core.BeanFactory;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithInjectImpl;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithNoParameterMethodImpl;
import io.github.varvelworld.var.ioc.core.BeanFactoryWithParametersMethodImpl;
import io.github.varvelworld.var.ioc.meta.BeanMeta;
import io.github.varvelworld.var.ioc.meta.ParamResourceMeta;
import io.github.varvelworld.var.ioc.meta.factory.BeanMetaFactory;
import io.github.varvelworld.var.ioc.meta.factory.BeanResourcesMetaFactory;

import java.lang.reflect.Method;
import java.util.stream.Collectors;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class AnnotationBeanMetaFactoryImpl implements BeanMetaFactory {

    final private BeanMeta beanMeta;

    public AnnotationBeanMetaFactoryImpl(final Object beansInstance, final Method method) {
        this(beansInstance, method, method.getAnnotation(Bean.class));
    }

    public AnnotationBeanMetaFactoryImpl(final Object beansInstance, final Method method, Bean annotation) {
        this.beanMeta = new BeanMeta(annotation.value().isEmpty() ? method.getName() : annotation.value()
                , new BeanFactoryWithInjectImpl(
                    annotation.socpe().wrap(method.getParameterCount() == 0
                    ? new BeanFactoryWithNoParameterMethodImpl(beansInstance, method)
                    : new BeanFactoryWithParametersMethodImpl(beansInstance, method
                    , new AnnotationParamResourcesMetaFactoryImpl(method.getParameters()).paramResourcesMeta()
                    .paramResourceMetaList().stream().map(ParamResourceMeta::beanGetter)
                    .collect(Collectors.toList())))
                , new AnnotationBeanResourcesMetaFactoryImpl()));
    }

    public BeanMeta beanMeta() {
        return beanMeta;
    }
}
