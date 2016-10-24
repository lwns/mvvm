package com.core.op.feature.main;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.di.PerActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/10
 */
@PerActivity
public class BannerItemViewModel implements BViewModel {

    RxAppCompatActivity activity;

    public final ObservableField<String> imageUrl = new ObservableField<>();

    // viewModel for recycler header viewPager

    @Inject
    public BannerItemViewModel(String url) {
        imageUrl.set(url);
    }

}
