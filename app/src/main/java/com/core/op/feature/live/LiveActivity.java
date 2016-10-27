package com.core.op.feature.live;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;

import com.core.op.R;
import com.core.op.base.BaseActivity;
import com.core.op.databinding.ActLiveBinding;
import com.core.op.di.components.LiveComponent;
import com.core.op.di.modules.LiveModule;
import com.core.op.di.components.DaggerLiveComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_live)
public final class LiveActivity extends BaseActivity<LiveViewModel, ActLiveBinding> {

    public static final String EXTRA_CID = "cid";

    public static final String EXTRA_TITLE = "title";

    public static final String EXTRA_ONLINE = "online";

    public static final String EXTRA_FACE = "face";

    public static final String EXTRA_NAME = "name";

    public static final String EXTRA_MID = "mid";

    LiveComponent component;

    public static void launch(Activity activity, int cid, String title, int online, String face, String name, int mid) {
        Intent mIntent = new Intent(activity, LiveActivity.class);
        mIntent.putExtra(EXTRA_CID, cid);
        mIntent.putExtra(EXTRA_TITLE, title);
        mIntent.putExtra(EXTRA_ONLINE, online);
        mIntent.putExtra(EXTRA_FACE, face);
        mIntent.putExtra(EXTRA_NAME, name);
        mIntent.putExtra(EXTRA_MID, mid);
        activity.startActivity(mIntent);
    }


    @BeforeViews
    void beforViews() {
        component = DaggerLiveComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .liveModule(new LiveModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
        initToolBar();
    }

    public void initToolBar() {
        setSupportActionBar(binding.toolbar.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
