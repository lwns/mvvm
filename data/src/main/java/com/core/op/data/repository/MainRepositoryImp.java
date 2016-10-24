package com.core.op.data.repository;

import com.core.op.data.ApiClient;
import com.core.op.data.AppClient;
import com.core.op.data.BilibiliClient;
import com.core.op.data.api.transformer.ErrorTransformer;
import com.domain.bean.BangumiRecommend;
import com.domain.bean.LiveIndex;
import com.domain.bean.NewBangumiSerial;
import com.domain.bean.SeasonNewBangumi;
import com.domain.repository.MainRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/11
 */
@Singleton
public class MainRepositoryImp implements MainRepository {

    ApiClient apiClient;

    AppClient appClient;

    BilibiliClient bilibiliClient;

    @Inject
    public MainRepositoryImp(ApiClient apiClient, AppClient appClient, BilibiliClient bilibiliClient) {
        this.apiClient = apiClient;
        this.appClient = appClient;
        this.bilibiliClient = bilibiliClient;
    }

    @Inject
    public Observable<LiveIndex> liveIndex() {
        return apiClient.getLiveIndex().compose(new ErrorTransformer());
    }

    @Override
    public Observable<ResponseBody> getLiveUrl(int id) {
        return apiClient.getLiveUrl(id);
    }

    @Override
    public Observable<BangumiRecommend> getBangumiRecommended() {
        return bilibiliClient.getBangumiRecommended();
    }

    @Override
    public Observable<SeasonNewBangumi> getSeasonNewBangumiList() {
        return appClient.getSeasonNewBangumiList();
    }

    @Override
    public Observable<NewBangumiSerial> getBangumiSerialList() {
        return bilibiliClient.getNewBangumiSerialList();
    }
}
