package com.core.op.di.modules;

import android.app.Application;
import android.content.Context;

import com.core.op.UIThread;
import com.core.op.data.ApiClient;
import com.core.op.data.AppClient;
import com.core.op.data.BilibiliClient;
import com.core.op.data.UrlRoot;
import com.core.op.data.api.ApiOption;
import com.core.op.data.executor.JobExecutor;
import com.core.op.data.repository.MainRepositoryImp;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 16/3/22.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return new ApiOption.Builder(application).url(UrlRoot.LIVE_BASE_URL).build().create(ApiClient.class);
    }

    @Provides
    @Singleton
    AppClient provideAppClient() {
        return new ApiOption.Builder(application).url(UrlRoot.APP_BASE_URL).build().create(AppClient.class);
    }

    @Provides
    @Singleton
    BilibiliClient provideBilibiliClient() {
        return new ApiOption.Builder(application).url(UrlRoot.BILIBILI_BASE_URL).build().create(BilibiliClient.class);
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    MainRepository mainRepository(MainRepositoryImp mainRepository) {
        return mainRepository;
    }
}
