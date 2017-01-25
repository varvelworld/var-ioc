package io.github.varvelworld.var.ioc.aop;

import java.lang.reflect.Method;

/**
 * Created by luzhonghao on 2016/12/30.
 */
public interface AfterAdvice extends Advice {
    void after(Method method, Object[] args, Object target, Object returnValue) throws Throwable;
}
