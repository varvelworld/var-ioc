package io.github.varvelworld.var.ioc.aop;

import java.util.List;

/**
 * aop代理
 * Created by luzhonghao on 2016/12/5.
 */
public interface AopProxy<T> {
    T targetBean();
    List<Advisor> advisors();
    T proxy(ClassLoader classLoader);
    T proxy();
}
