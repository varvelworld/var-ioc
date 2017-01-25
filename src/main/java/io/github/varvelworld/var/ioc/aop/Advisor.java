package io.github.varvelworld.var.ioc.aop;

/**
 * 通知器
 * Created by luzhonghao on 2016/12/5.
 */
public interface Advisor {
    Advice advice();
    Pointcut pointcut();
}
