package io.github.varvelworld.var.ioc.meta;

import java.util.List;

/**
 * Created by luzhonghao on 2016/11/26.
 */
public class BeanResourcesMeta {
    final private List<ResourceMeta> resourceMetaList;

    public BeanResourcesMeta(List<ResourceMeta> resourceMetaList) {
        this.resourceMetaList = resourceMetaList;
    }

    public List<ResourceMeta> getResourceMetaList() {
        return resourceMetaList;
    }
}
