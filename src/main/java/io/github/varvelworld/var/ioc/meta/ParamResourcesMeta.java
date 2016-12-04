package io.github.varvelworld.var.ioc.meta;

import java.util.List;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class ParamResourcesMeta {
    private final List<ParamResourceMeta> paramResourceMetaList;

    public ParamResourcesMeta(List<ParamResourceMeta> paramResourceMetaList) {
        this.paramResourceMetaList = paramResourceMetaList;
    }

    public List<ParamResourceMeta> paramResourceMetaList() {
        return paramResourceMetaList;
    }
}
