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
}
