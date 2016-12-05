package io.github.varvelworld.var.ioc.util;

/**
 * Created by luzhonghao on 2016/12/5.
 */
public class ImmutablePair<L, R> implements Pair<L, R> {

    final private L left;
    final private R right;

    public ImmutablePair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L left() {
        return left;
    }

    @Override
    public R right() {
        return right;
    }

    @Override
    public L key() {
        return left;
    }

    @Override
    public R value() {
        return right;
    }
}
