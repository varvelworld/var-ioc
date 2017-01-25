package io.github.varvelworld.var.ioc.aop.impl;

import io.github.varvelworld.var.ioc.aop.Advice;
import io.github.varvelworld.var.ioc.aop.Advisor;
import io.github.varvelworld.var.ioc.aop.Pointcut;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public class AdvisorImpl implements Advisor {

    private final Advice advice;
    private final Pointcut pointcut;

    public AdvisorImpl(Advice advice, Pointcut pointcut) {
        this.advice = advice;
        this.pointcut = pointcut;
    }

    @Override
    public Advice advice() {
        return advice;
    }

    @Override
    public Pointcut pointcut() {
        return pointcut;
    }
}
