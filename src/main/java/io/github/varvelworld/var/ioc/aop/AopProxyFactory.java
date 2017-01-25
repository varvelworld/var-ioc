package io.github.varvelworld.var.ioc.aop;

import java.util.List;

/**
 * aop代理工厂
 * Created by luzhonghao on 2016/12/5.
 */
public interface AopProxyFactory {
    <T> AopProxy<T> aopProxy(T bean, List<Advisor> advisors);
}
