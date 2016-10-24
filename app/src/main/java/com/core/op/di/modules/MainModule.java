package com.core.op.di.modules;

import com.core.op.lib.di.PerActivity;
import com.domain.interactor.main.BangumiIndexUserCase;
import com.domain.interactor.main.BangumiRecommendUserCase;
import com.domain.interactor.main.BangumiSerialUserCase;
import com.domain.interactor.main.LiveIndexUserCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/9/21
 */
@Module
public class MainModule {

    @Provides
    @PerActivity
    @Named("liveindex")
    LiveIndexUserCase liveIndexUserCase(LiveIndexUserCase liveIndexUserCase) {
        return liveIndexUserCase;
    }

    @Provides
    @PerActivity
    @Named("bangumiindex")
    BangumiIndexUserCase bangumiIndexUserCase(BangumiIndexUserCase bangumiIndexUserCase) {
        return bangumiIndexUserCase;
    }

    @Provides
    @PerActivity
    @Named("bangumiserial")
    BangumiSerialUserCase bangumiSerialUserCase(BangumiSerialUserCase bangumiSerialUserCase) {
        return bangumiSerialUserCase;
    }

    @Provides
    @PerActivity
    @Named("bangumirecommend")
    BangumiRecommendUserCase bangumiRecommendUserCase(BangumiRecommendUserCase bangumiRecommendUserCase) {
        return bangumiRecommendUserCase;
    }

}
