package com.core.op;

import android.app.Application;

import com.core.op.di.components.AppComponent;
import com.core.op.di.components.DaggerAppComponent;
import com.core.op.di.modules.AppModule;
import com.core.op.lib.AppException;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/10
 */
public class MainApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(AppException
                .getAppExceptionHandler(this));

        LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.tag("YTP");
        }

        Logger.init("YTP")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
