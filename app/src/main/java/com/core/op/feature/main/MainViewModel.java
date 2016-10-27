package com.core.op.feature.main;

import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.core.op.R;
import com.core.op.databinding.ActMainBinding;
import com.core.op.feature.main.home.HomeFragment;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.lib.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/9/21
 */
@PerActivity
public class MainViewModel extends BAViewModel<ActMainBinding> {

    private RxAppCompatActivity activity;

    public final FragmentManager fragmentManager;

    public final List<Fragment> fragments = new ArrayList<>();

    public final ObservableField<Integer> index = new ObservableField<>(0);
    public final ObservableField<Integer> currentIndex = new ObservableField<>(0);

    @Inject
    public MainViewModel(RxAppCompatActivity activity) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
        fragments.add(HomeFragment.instance());
    }

    @Override
    public void afterViews() {
    }
}
