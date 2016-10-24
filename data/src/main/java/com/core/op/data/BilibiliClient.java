package com.core.op.data;

import com.core.op.data.api.BaseResponse;
import com.domain.bean.BangumiRecommend;
import com.domain.bean.LiveIndex;
import com.domain.bean.NewBangumiSerial;
import com.domain.bean.SeasonNewBangumi;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/8
 */

public interface BilibiliClient {


    @GET("bangumiindex")
    Observable<BangumiRecommend> getBangumiRecommended();

    @GET("bangumi")
    Observable<NewBangumiSerial> getNewBangumiSerialList();
}
