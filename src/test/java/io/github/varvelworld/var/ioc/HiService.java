package io.github.varvelworld.var.ioc;

import io.github.varvelworld.var.ioc.annotation.Resource;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class HiService {

    @Resource("fun2")
    private HelloPOJO fun;


    @Resource("fun3")
    private HelloPOJO fun3;

    public String hello() {
        return fun.getName();
    }

    public HelloPOJO getFun() {
        return fun;
    }

    public HelloPOJO getFun3() {
        return fun3;
    }
}
