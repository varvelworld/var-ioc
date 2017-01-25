package io.github.varvelworld.var.ioc.aop;

import java.lang.reflect.Method;

/**
 * 前置通知
 * Created by luzhonghao on 2016/12/30.
 */
public interface BeforeAdvice extends Advice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
