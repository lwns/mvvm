package com.core.op.feature.main.home.live;

import com.core.op.R;
import com.core.op.base.BaseFragment;
import com.core.op.databinding.FrgHomeliveBinding;
import com.core.op.di.components.MainComponent;
import com.core.op.feature.main.home.HomeFragment;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

@RootView(R.layout.frg_homelive)
public final class HomeLiveFragment extends BaseFragment<HomeLiveViewModel, FrgHomeliveBinding> {

    public static HomeLiveFragment instance() {
        return new HomeLiveFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
