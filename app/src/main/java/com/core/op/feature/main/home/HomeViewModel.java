package com.core.op.feature.main.home;


import android.content.Intent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.core.op.databinding.FrgHomeBinding;
import com.core.op.feature.main.MainActivity;
import com.core.op.feature.main.home.bangumi.BangumiFragment;
import com.core.op.feature.main.home.live.HomeLiveFragment;
import com.core.op.feature.main.home.more.MoreFragment;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public final class HomeViewModel extends BFViewModel<FrgHomeBinding> {

    private RxAppCompatActivity activity;

    public FragmentManager fragmentManager;

    public final ObservableField<Integer> selectPosition = new ObservableField<>(1);

    public final List<Fragment> fragments = new ArrayList<>();

    public final List<String> titles = new ArrayList<>();

    public final ReplyCommand navigationClick = new ReplyCommand(() -> {
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).toggleDrawer();
        }
    });

    @Inject
    public HomeViewModel(RxAppCompatActivity activity) {
        this.activity = activity;
        titles.addAll(Arrays.asList(activity.getResources().getStringArray(com.core.op.R.array.home_sections)));
        fragments.add(HomeLiveFragment.instance());
        fragments.add(MoreFragment.instance());
        fragments.add(BangumiFragment.instance());
        fragments.add(MoreFragment.instance());
        fragments.add(MoreFragment.instance());
        fragments.add(MoreFragment.instance());
    }

    @Override
    public void afterViews() {
        this.fragmentManager = fragment.getChildFragmentManager();
    }
}