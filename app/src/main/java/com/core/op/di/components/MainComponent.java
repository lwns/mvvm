package com.core.op.di.components;


import com.core.op.di.modules.ActivityModule;
import com.core.op.di.modules.MainModule;
import com.core.op.feature.main.MainActivity;
import com.core.op.feature.main.home.HomeFragment;
import com.core.op.feature.main.home.bangumi.BangumiFragment;
import com.core.op.feature.main.home.live.HomeLiveFragment;
import com.core.op.feature.main.home.more.MoreFragment;
import com.core.op.feature.main.home.recommend.RecommendFragment;
import com.core.op.lib.di.PerActivity;
import com.domain.interactor.main.LiveIndexUserCase;

import dagger.Component;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/9
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, MainModule.class})
public interface MainComponent extends ActivityComponent {

    void inject(MainActivity activity);

    void inject(HomeFragment fragment);

    void inject(HomeLiveFragment fragment);

    void inject(MoreFragment fragment);

    void inject(RecommendFragment fragment);

    void inject(BangumiFragment fragment);

}
