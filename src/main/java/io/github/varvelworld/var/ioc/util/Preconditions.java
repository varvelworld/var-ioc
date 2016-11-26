package io.github.varvelworld.var.ioc.util;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class Preconditions {
     public static void check(boolean expression, String message) {
        if(!expression) {
            throw new RuntimeException(message);
        }
    }
}
