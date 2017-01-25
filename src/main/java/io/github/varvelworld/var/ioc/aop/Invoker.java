package io.github.varvelworld.var.ioc.aop;

/**
 * 调用器
 * Created by luzhonghao on 2017/1/25.
 */
public interface Invoker {
    Object invoke(Object[] args);
}
