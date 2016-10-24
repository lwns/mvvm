package com.core.op.base;

import android.databinding.ViewDataBinding;

import com.core.op.lib.base.BFragment;
import com.core.op.lib.di.HasComponent;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/11
 */
public class BaseFragment<T extends ViewDataBinding> extends BFragment<T> {

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
