package io.github.varvelworld.var.ioc;

import io.github.varvelworld.var.ioc.core.annotation.Bean;
import io.github.varvelworld.var.ioc.core.annotation.Beans;
import io.github.varvelworld.var.ioc.core.annotation.Resource;
import io.github.varvelworld.var.ioc.core.meta.BeanScope;

/**
 * Created by luzhonghao on 2016/11/26.
 */
@Beans
public class TestBeans {

    @Bean(value = "fun")
    static private HelloPOJO fun() {
        return new HelloPOJO("hello world");
    }

    @Bean
    static public HelloPOJO fun2() {
        return new HelloPOJO("hello world2");
    }

    @Bean(socpe = BeanScope.PROTOTYPE)
    private HelloPOJO fun3() {
        return new HelloPOJO("hello world3");
    }

    @Bean
    public HelloPOJO fun4() {
        return new HelloPOJO("hello world4");
    }

    @Bean
    public HiService hi() {
        return new HiService();
    }

    @Bean(socpe = BeanScope.PROTOTYPE)
    public HiService hi2() {
        return new HiService();
    }

    @Bean
    public HiService hi3(@Resource("fun4") HelloPOJO fun4) {
        return new HiService(fun4);
    }
}
