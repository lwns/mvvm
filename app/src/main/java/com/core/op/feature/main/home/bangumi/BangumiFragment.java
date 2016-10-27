package com.core.op.feature.main.home.bangumi;

import com.android.databinding.library.baseAdapters.BR;
import com.core.op.R;
import com.core.op.base.BaseFragment;
import com.core.op.databinding.FrgBangumiBinding;
import com.core.op.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_bangumi)
public final class BangumiFragment extends BaseFragment<BangumiViewModel, FrgBangumiBinding> {

    public static BangumiFragment instance() {
        return new BangumiFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
