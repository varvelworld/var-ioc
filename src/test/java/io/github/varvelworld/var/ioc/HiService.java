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

    final private HelloPOJO fun4;

    public HiService() {
        this.fun4 = null;
    }

    public HiService(HelloPOJO fun4) {
        this.fun4 = fun4;
    }

    public String hello() {
        return fun.getName();
    }

    public HelloPOJO getFun() {
        return fun;
    }

    public HelloPOJO getFun3() {
        return fun3;
    }

    public HelloPOJO getFun4() {
        return fun4;
    }
}
