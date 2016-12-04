package io.github.varvelworld.var.ioc.meta;

import java.util.Collections;
import java.util.List;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeanResourcesMeta {

    public static final BeanResourcesMeta EMPTY = new BeanResourcesMeta(Collections.emptyList());

    final private List<ResourceMeta> resourceMetaList;

    public BeanResourcesMeta(List<ResourceMeta> resourceMetaList) {
        this.resourceMetaList = resourceMetaList;
    }

    public List<ResourceMeta> resourceMetaList() {
        return resourceMetaList;
    }
}
