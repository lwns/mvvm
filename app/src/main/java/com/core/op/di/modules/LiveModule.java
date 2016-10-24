package com.core.op.di.modules;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import com.core.op.lib.di.PerActivity;
import com.domain.interactor.main.LiveIndexUserCase;
import com.domain.interactor.main.LiveUrlUserCase;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public final class LiveModule {

    @Provides
    @PerActivity
    @Named("liveurl")
    LiveUrlUserCase liveIndexUserCase(LiveUrlUserCase liveUrlUserCase) {
        return liveUrlUserCase;
    }
}
