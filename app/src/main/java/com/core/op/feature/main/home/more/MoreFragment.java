package com.core.op.feature.main.home.more;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.DrawableRes;

import com.core.op.BR;
import com.core.op.R;
import com.core.op.base.BaseFragment;
import com.core.op.databinding.FrgMoreBinding;
import com.core.op.di.components.MainComponent;
import com.core.op.feature.main.home.bangumi.BangumiSeasonItemViewModel;
import com.core.op.feature.main.home.live.HomeLiveFragment;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_more)
public final class MoreFragment extends BaseFragment<MoreViewModel, FrgMoreBinding> {


    public static MoreFragment instance() {
        return new MoreFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }

}
