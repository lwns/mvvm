package com.core.op.feature.main.home;

import com.core.op.R;
import com.core.op.base.BaseFragment;
import com.core.op.databinding.FrgHomeBinding;
import com.core.op.di.components.MainComponent;
import com.core.op.feature.main.MainActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import static com.core.op.lib.bindingadapter.viewpager.ViewBindingAdapter.VIEWPAGE_INIT_COMPLATE;

@RootView(R.layout.frg_home)
public final class HomeFragment extends BaseFragment<HomeViewModel, FrgHomeBinding> {

    public static HomeFragment instance() {
        return new HomeFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
        setHasOptionsMenu(true);
        initToolBar();
        initViewPage();
    }


    private void initToolBar() {
        binding.toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(binding.toolbar);
    }

    private void initViewPage() {
        binding.viewPager.setOffscreenPageLimit(5);
        Messenger.getDefault().register(this, VIEWPAGE_INIT_COMPLATE, () ->
                binding.slidingTabs.setViewPager(binding.viewPager));
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
