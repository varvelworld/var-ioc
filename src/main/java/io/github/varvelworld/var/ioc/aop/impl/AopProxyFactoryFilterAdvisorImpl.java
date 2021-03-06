package io.github.varvelworld.var.ioc.aop.impl;

import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.aop.AopProxy;
import io.github.varvelworld.var.ioc.aop.AopProxyFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * aop代理工厂,过滤与当前bean无关的通知
 * Created by luzhonghao on 2017/1/25.
 */
public class AopProxyFactoryFilterAdvisorImpl implements AopProxyFactory {

    private final AopProxyFactory aopProxyFactory;

    public AopProxyFactoryFilterAdvisorImpl(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

    @Override
    public <T> AopProxy<T> aopProxy(T bean, List<Advisor> advisors) {
        advisors = advisors
                .stream()
                .filter(advisor -> accept(bean, advisor))
                .collect(Collectors.toList());
        return !advisors.isEmpty()
                ? aopProxyFactory.aopProxy(bean, advisors)
                : new AopNoneProxyImpl<>(bean);
    }

    private boolean accept(Object bean, Advisor advisor) {
        Class<?> clazz = bean.getClass();
        for(Method method : clazz.getMethods()) {
            if(advisor.pointcut().matches(method, clazz)) {
                return true;
            }
        }
        return false;
    }
}
