package com.core.op.lib.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.core.op.lib.utils.inject.InjectUtil;
import com.trello.rxlifecycle.components.support.RxFragment;


/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/2/2
 */
public class BFragment<T extends ViewDataBinding> extends RxFragment {

    protected LayoutInflater inflater;

    protected T binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        binding = DataBindingUtil.inflate(inflater, InjectUtil.injectFrgRootView(this), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InjectUtil.injectAfterView(this);
    }


    protected void initBeforeView() {
        InjectUtil.injectBeforeView(this);
    }
}
