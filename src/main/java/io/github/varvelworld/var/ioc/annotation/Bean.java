package io.github.varvelworld.var.ioc.annotation;

import io.github.varvelworld.var.ioc.meta.BeanScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by luzhonghao on 2016/11/25.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
    /**
     * bean id
     * @return
     */
    String value() default "";

    /**
     * bean 作用范围
     * @return
     */
    BeanScope socpe() default BeanScope.SINGLETON;
}
