package io.github.varvelworld.var.ioc.aop.impl;

import io.github.varvelworld.var.ioc.aop.Pointcut;
import io.github.varvelworld.var.ioc.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public class SimplePointcutImpl implements Pointcut {

    private final String classSimpleName;
    private final String methodName;

    public SimplePointcutImpl(String classSimpleName, String methodName) {
        this.classSimpleName = classSimpleName;
        this.methodName = methodName;
    }

    @Override
    public boolean matches(Method method, Class<?> clazz) {
        return matchMethod(method) && matchClass(clazz);
    }

    private boolean matchMethod(Method method) {
        return StringUtils.isEmpty(methodName) || methodName.equals(method.getName());
    }

    private boolean matchClass(Class<?> clazz) {
        return StringUtils.isEmpty(classSimpleName) || classSimpleName.equals(clazz.getSimpleName());
    }
}
