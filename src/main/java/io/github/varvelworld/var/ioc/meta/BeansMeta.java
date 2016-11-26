package io.github.varvelworld.var.ioc.meta;


import java.util.List;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeansMeta {
    final List<BeanMeta> beanMetaList;

    public BeansMeta(List<BeanMeta> beanMetaList) {
        this.beanMetaList = beanMetaList;
    }

    public List<BeanMeta> getBeanMetaList() {
        return beanMetaList;
    }


}
