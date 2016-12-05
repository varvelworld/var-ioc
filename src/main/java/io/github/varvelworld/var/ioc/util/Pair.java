package io.github.varvelworld.var.ioc.util;

/**
 * Created by luzhonghao on 2016/12/5.
 */
public interface Pair<L, R> {
    L left();
    R right();
    L key();
    R value();
}
