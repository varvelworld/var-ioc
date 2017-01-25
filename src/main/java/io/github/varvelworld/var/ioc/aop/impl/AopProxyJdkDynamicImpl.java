package io.github.varvelworld.var.ioc.aop.impl;

import io.github.varvelworld.var.ioc.aop.*;
import io.github.varvelworld.var.ioc.exception.NotImplementedException;
import io.github.varvelworld.var.ioc.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * jdk动态aop代理
 * Created by luzhonghao on 2016/12/30.
 */
public class AopProxyJdkDynamicImpl<T> implements AopProxy<T>,InvocationHandler {

    final private T targetBean;
    final private List<Advisor> advisors;

    public AopProxyJdkDynamicImpl(T targetBean, List<Advisor> advisors) {
        this.targetBean = targetBean;
        this.advisors = advisors;
    }

    @Override
    public T targetBean() {
        return targetBean;
    }

    @Override
    public List<Advisor> advisors() {
        return advisors;
    }

    @Override
    public T proxy(ClassLoader classLoader) {
        Class<?>[] interfaces = targetBean.getClass().getInterfaces();
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public T proxy() {
        return proxy(ClassUtils.getDefaultClassLoader());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        final List<BeforeAdvice> beforeAdvices = new ArrayList<>();
        final List<AfterAdvice> afterAdvices = new ArrayList<>();
        final List<AroundAdvice> aroundAdvices = new ArrayList<>();
        advisors.stream()
                .filter(advisor -> advisor.pointcut().matches(method, targetBean.getClass()))
                .forEach(advisor -> {
                    Advice advice = advisor.advice();
                    if (advice instanceof BeforeAdvice) {
                        beforeAdvices.add((BeforeAdvice) advice);
                    } else if (advice instanceof AfterAdvice) {
                        afterAdvices.add((AfterAdvice) advice);
                    } else if(advice instanceof AroundAdvice) {
                        aroundAdvices.add((AroundAdvice) advice);
                    }
                    else {
                        /** 未实现的通知类型 **/
                        throw new NotImplementedException(String.format("not implement Advice, class:%s"
                                , advice.getClass()));
                    }
        });
        for(BeforeAdvice beforeAdvice : beforeAdvices) {
            beforeAdvice.before(method, args, targetBean);
        }
        Object returnValue = wrapInvoker(aroundAdvices, method).invoke(args);
        for(AfterAdvice afterAdvice : afterAdvices) {
            afterAdvice.after(method, args, targetBean, returnValue);
        }
        return returnValue;
    }

    /**
     * 围绕通知包装成调用器
     * @param aroundAdvices
     * @param method
     * @return
     */
    private Invoker wrapInvoker(List<AroundAdvice> aroundAdvices, Method method) {
        Invoker invoker = args -> {
            try {
                return method.invoke(targetBean, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
        for(AroundAdvice aroundAdvice : aroundAdvices) {
            final Invoker finalInvoker = invoker;
            invoker = args -> aroundAdvice.around(method, args, targetBean, finalInvoker);
        }
        return invoker;
    }
}
