package com.core.op.feature.main.home.recommend;

import com.core.op.R;
import com.core.op.base.BaseFragment;
import com.core.op.databinding.FrgRecommendBinding;
import com.core.op.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_recommend)
public final class RecommendFragment extends BaseFragment<FrgRecommendBinding> {

    @Inject
    RecommendViewModel viewModel;

    public static RecommendFragment instance() {
        return new RecommendFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
        viewModel.setFragment(this);
    }

    @AfterViews
    void afterViews() {
        binding.setViewModel(viewModel);
    }
}
