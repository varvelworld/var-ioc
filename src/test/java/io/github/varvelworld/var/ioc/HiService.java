package io.github.varvelworld.var.ioc;

import io.github.varvelworld.var.ioc.annotation.Resource;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class HiService {

    @Resource("fun2")
    private HelloPOJO fun;

    public String hello() {
        return fun.getName();
    }
}
