package io.github.varvelworld.var.ioc.aop;

/**
 * 通知器
 * 关联通知与切点
 * Created by luzhonghao on 2016/12/5.
 */
public interface Advisor {
    Advice advice();
    Pointcut pointcut();
}
