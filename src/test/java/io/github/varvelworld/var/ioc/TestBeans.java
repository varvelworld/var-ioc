package io.github.varvelworld.var.ioc;

import io.github.varvelworld.var.ioc.annotation.Bean;
import io.github.varvelworld.var.ioc.annotation.Beans;
import io.github.varvelworld.var.ioc.meta.BeanScope;

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

    @Bean(socpe = BeanScope.PROTOTYPE)
    public HelloPOJO fun3() {
        return new HelloPOJO("hello world3");
    }

    @Bean
    public HiService hi() {
        return new HiService();
    }

    @Bean(socpe = BeanScope.PROTOTYPE)
    public HiService hi2() {
        return new HiService();
    }
}
