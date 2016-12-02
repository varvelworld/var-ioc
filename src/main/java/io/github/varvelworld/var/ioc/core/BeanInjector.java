package io.github.varvelworld.var.ioc.core;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public interface BeanInjector {
    void inject(Object bean, Object injectBean);
}
