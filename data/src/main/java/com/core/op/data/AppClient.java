package com.core.op.data;

import com.core.op.data.api.BaseResponse;
import com.domain.bean.LiveIndex;
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

public interface AppClient {

    @GET("bangumi/operation_module?_device=android&_hwid=ac538400c68784bb&_ulv=10000&module=bangumi&platform=android&screen=xxhdpi")
    Observable<SeasonNewBangumi> getSeasonNewBangumiList();
}
