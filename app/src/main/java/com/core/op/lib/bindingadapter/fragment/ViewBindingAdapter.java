package com.core.op.lib.bindingadapter.fragment;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.core.op.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/9
 */
public final class ViewBindingAdapter {

    /**
     * Fragment切换
     */
    @BindingAdapter(value = {"frgManager", "fragemnts", "index", "currentIndex"}, requireAll = false)
    public static void switchFragment(final FrameLayout frameLayout, final FragmentManager fragmentManager, List<Fragment> items, final int index, final int currentTabIndex) {
        if (items == null) {
            items = new ArrayList<>();
            return;
        }
        FragmentTransaction trx = fragmentManager.beginTransaction();
        trx.hide(items.get(currentTabIndex));
        if (!items.get(index).isAdded()) {
            trx.add(R.id.container, items.get(index));
        }
        trx.show(items.get(index)).commit();
    }
}
