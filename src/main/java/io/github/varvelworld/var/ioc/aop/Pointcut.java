package io.github.varvelworld.var.ioc.aop;

import java.lang.reflect.Method;

/**
 * 切点
 * Created by luzhonghao on 2016/12/5.
 */
public interface Pointcut {
    boolean matches(Method method, Class<?> clazz);
}
