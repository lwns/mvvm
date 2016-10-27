package com.core.op.lib.base;

import android.support.v4.app.FragmentManager;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/21
 */
public abstract class BAViewModel<T> {

    protected T binding;

    protected FragmentManager fragmentManager;

    protected void setBinding(T binding) {
        this.binding = binding;
    }

    public abstract void afterViews();
}
