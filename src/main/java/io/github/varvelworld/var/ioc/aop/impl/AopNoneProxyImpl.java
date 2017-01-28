package io.github.varvelworld.var.ioc.aop.impl;

import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.aop.AopProxy;

import java.util.Collections;
import java.util.List;

/**
 * Created by luzhonghao on 2017/1/28.
 */
public class AopNoneProxyImpl<T> implements AopProxy<T> {

    final private T targetBean;

    public AopNoneProxyImpl(T targetBean) {
        this.targetBean = targetBean;
    }

    @Override
    public T targetBean() {
        return targetBean;
    }

    @Override
    public List<Advisor> advisors() {
        return Collections.emptyList();
    }

    @Override
    public T proxy(ClassLoader classLoader) {
        return targetBean;
    }

    @Override
    public T proxy() {
        return targetBean;
    }
}
