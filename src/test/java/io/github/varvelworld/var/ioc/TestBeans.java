package io.github.varvelworld.var.ioc;

import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.annotation.Beans;

/**
 * Created by luzhonghao on 2016/11/26.
 */
@Beans
public class TestBeans {

    @Bean(value = "fun")
    public HelloPOJO fun() {
        return new HelloPOJO("hello world");
    }

    @Bean
    public HelloPOJO fun2() {
        return new HelloPOJO("hello world2");
    }

}
