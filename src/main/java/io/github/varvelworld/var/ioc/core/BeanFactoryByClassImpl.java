package io.github.varvelworld.var.ioc.core;

/**
 * Created by luzhonghao on 2016/12/3.
 */
public class BeanFactoryByClassImpl implements BeanFactory {

    final Class<?> clazz;

    public BeanFactoryByClassImpl(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object createBean() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
