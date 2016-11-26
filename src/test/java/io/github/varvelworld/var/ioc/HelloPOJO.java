package io.github.varvelworld.var.ioc;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class HelloPOJO {
    private String name;

    public HelloPOJO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
