package com.core.op.lib.bindingadapter.common.factories;

import android.widget.AdapterView;

import com.core.op.lib.bindingadapter.common.BindingListViewAdapter;
import com.core.op.lib.bindingadapter.common.ItemViewArg;


public interface BindingAdapterViewFactory {
    <T> BindingListViewAdapter<T> create(AdapterView adapterView, ItemViewArg<T> arg);

    BindingAdapterViewFactory DEFAULT = new BindingAdapterViewFactory() {
        @Override
        public <T> BindingListViewAdapter<T> create(AdapterView adapterView, ItemViewArg<T> arg) {
            return new BindingListViewAdapter<>(arg);
        }
    };
}
