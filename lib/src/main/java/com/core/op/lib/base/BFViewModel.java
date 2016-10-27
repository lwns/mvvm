package com.core.op.lib.base;

import android.support.v4.app.FragmentManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/21
 */
public abstract class BFViewModel<T> {

    protected T binding;

    protected RxFragment fragment;

    protected FragmentManager fragmentManager;

    protected void setBinding(T binding) {
        this.binding = binding;
    }

    protected void setFragment(RxFragment fragment) {
        this.fragment = fragment;
    }

    public abstract void afterViews();
}
