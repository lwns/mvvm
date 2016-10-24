package com.core.op.feature.main;

import android.support.v4.view.GravityCompat;
import android.view.KeyEvent;

import com.core.op.R;
import com.core.op.base.BaseActivity;
import com.core.op.databinding.ActMainBinding;
import com.core.op.di.components.DaggerMainComponent;
import com.core.op.di.components.MainComponent;
import com.core.op.di.modules.MainModule;
import com.core.op.feature.main.home.HomeFragment;
import com.core.op.lib.di.HasComponent;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/9/21
 */
@RootView(R.layout.act_main)
public class MainActivity extends BaseActivity<ActMainBinding> implements HasComponent<MainComponent> {

    @Inject
    MainViewModel viewModel;

    MainComponent component;

    long exitTime;

    @BeforeViews
    void beforViews() {
        component = DaggerMainComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
        binding.setViewModel(viewModel);
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (binding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            binding.drawerlayout.openDrawer(GravityCompat.START);
        }
    }

    /**
     * 监听back键处理DrawerLayout和SearchView
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();
        }
        return true;
    }

    /**
     * 双击退出App
     */
    private void exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            AppToast.show(this, "再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public MainComponent getComponent() {
        return component;
    }
}
