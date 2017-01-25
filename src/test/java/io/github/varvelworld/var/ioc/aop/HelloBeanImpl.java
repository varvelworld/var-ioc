package io.github.varvelworld.var.ioc.aop;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public class HelloBeanImpl implements HelloBean {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
