package io.github.varvelworld.var.ioc.core.meta;

import java.util.Collections;
import java.util.List;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class ParamResourcesMeta {

    public static final ParamResourcesMeta EMPTY = new ParamResourcesMeta(Collections.emptyList());

    private final List<ParamResourceMeta> paramResourceMetaList;

    public ParamResourcesMeta(List<ParamResourceMeta> paramResourceMetaList) {
        this.paramResourceMetaList = paramResourceMetaList;
    }

    public List<ParamResourceMeta> paramResourceMetaList() {
        return paramResourceMetaList;
    }
}
