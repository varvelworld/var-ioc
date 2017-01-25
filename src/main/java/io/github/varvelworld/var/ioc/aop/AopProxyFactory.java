package io.github.varvelworld.var.ioc.aop;

import java.util.List;

/**
 * 代理工厂
 * Created by luzhonghao on 2016/12/5.
 */
public interface AopProxyFactory {
    <T> AopProxy<T> aopProxy(T bean, List<Advisor> advisors);
}
