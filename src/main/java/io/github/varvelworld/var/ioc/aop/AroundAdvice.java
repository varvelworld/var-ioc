package io.github.varvelworld.var.ioc.aop;

import java.lang.reflect.Method;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public interface AroundAdvice extends Advice {
    Object around(Method method, Object[] args, Object target, Invoker invoker);
}
