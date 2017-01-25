package io.github.varvelworld.var.ioc.util;

/**
 * Created by luzhonghao on 2017/1/25.
 */
public class StringUtils {
    public static boolean isEmpty(String string) {
        if(string == null) return true;
        if(string.isEmpty()) return true;
        return false;
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
