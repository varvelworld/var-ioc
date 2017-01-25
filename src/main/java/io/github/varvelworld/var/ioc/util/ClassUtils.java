package io.github.varvelworld.var.ioc.util;

import java.lang.reflect.Constructor;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class ClassUtils {
    public static Constructor<?> getConstructorByArgCount(Class<?> clazz, int argCount) {
        Constructor<?> targetConstructor = null;
        for(Constructor<?> constructor : clazz.getConstructors()) {
            if(constructor.getParameters().length == argCount) {
                if(targetConstructor != null) {
                    throw new RuntimeException("ambiguous constructor by arg count");
                }
                targetConstructor = constructor;
            }
        }
        return targetConstructor;
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // 不能获取当前线程的类加载器
        }
        if (classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
        }
        if (classLoader == null) {
            try {
                classLoader = ClassLoader.getSystemClassLoader();
            }
            catch (Throwable ex) {
                // 不能获取系统类加载器
            }
        }
        return classLoader;
    }
}
