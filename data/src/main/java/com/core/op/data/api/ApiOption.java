package com.core.op.data.api;


import android.app.Application;

import com.core.op.data.util.NetUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.common.base.Strings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/3/4
 */
public class ApiOption {

    private Map<Class, Object> apis;

    protected Retrofit retrofit;

    public ApiOption(Retrofit retrofit) {
        this.retrofit = retrofit;
        apis = new HashMap<>();
    }

    public <T> T create(final Class<T> service) {
//        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object... args)
//                            throws Throwable {
//                        Object object = apis.get(service);
//                        if (object == null) {
//                            object = retrofit.create(service);
//                            apis.put(service, object);
//                        }
//                        return method.invoke(object, args);
//                    }
//                });
        return retrofit.create(service);
    }

    /**
     * ApiOption 构建器，主要是构建Retrofit对象出来
     */
    public static final class Builder {

        protected Retrofit retrofit;

        protected OkHttpClient client;

        protected String url;


        public Builder(Application application) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient()
                    .newBuilder()
                    .addInterceptor(logging)
                    .addInterceptor(new HeadInterceptor())
                    .addInterceptor(new CacheInterceptor(application))
                    .addNetworkInterceptor(new StethoInterceptor())
                    .cache(provideCache(application))
                    .build();
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public ApiOption build() {
            if (Strings.isNullOrEmpty(url)) {
                throw new RuntimeException("url is null!");
            }
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            return new ApiOption(retrofit);
        }


        public Cache provideCache(Application application) {
            return new Cache(application.getCacheDir(), 10240 * 1024);
        }
    }

    /**
     * 添加请求头信息
     */
    static class HeadInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
//                    .addHeader("devicetype", "ANDROID")
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * 有网请求，没网不读缓存
     */
    static class CacheInterceptor implements Interceptor {
        Application application;

        public CacheInterceptor(Application application) {
            this.application = application;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(application)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (NetUtil.isNetworkAvailable(application)) {
                int maxAge = 0;
                response.newBuilder().header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    }

}
