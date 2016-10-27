package com.core.op.lib.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;

import com.android.databinding.library.baseAdapters.BR;
import com.core.op.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import com.core.op.lib.utils.inject.InjectUtil;

import javax.inject.Inject;


/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/2/1
 */
public class BActivity<V extends BAViewModel, T extends ViewDataBinding> extends RxAppCompatActivity {

    protected LayoutInflater inflater;

    protected T binding;

    @Inject
    protected V viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        initBeforeView();
        initRootView();
        viewModel.setBinding(binding);
        binding.setVariable(BR.viewModel, viewModel);
        initAfterView();
        viewModel.afterViews();
    }

    protected void initBeforeView() {
        InjectUtil.injectBeforeView(this);
    }

    protected void initRootView() {
        binding = InjectUtil.injectBindRootView(this);
    }

    protected void initAfterView() {
        InjectUtil.injectAfterView(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

}
