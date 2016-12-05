package io.github.varvelworld.var.ioc.core.meta;

import io.github.varvelworld.var.ioc.core.IocContainer;

import java.util.function.Function;

/**
 * Created by luzhonghao on 2016/12/4.
 */
public class ParamResourceMeta {
    final private String id;
    private final Function<IocContainer, Object> beanGetter;

    public ParamResourceMeta(String id) {
        this.id = id;
        this.beanGetter = (iocContainer -> iocContainer.getBean(id));
    }

    public String getId() {
        return id;
    }

    public Function<IocContainer, Object> beanGetter() {
        return beanGetter;
    }
}
