package com.core.op.data;

import com.core.op.data.api.BaseResponse;
import com.domain.bean.LiveIndex;

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

public interface ApiClient {

    //    @FormUrlEncoded
//    @POST(UriMethod.LOGIN)
//    Observable<BaseResponse<User>> login(@Field("telephone") String name, @Field("password") String password);

    @GET("AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Observable<BaseResponse<LiveIndex>> getLiveIndex();

    @GET("api/playurl?player=1&quality=0")
    Observable<ResponseBody> getLiveUrl(@Query("cid") int cid);
}
