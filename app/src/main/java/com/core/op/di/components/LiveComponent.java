package com.core.op.di.components;

import dagger.Component;


import com.core.op.di.modules.ActivityModule;
import com.core.op.di.modules.LiveModule;
import com.core.op.feature.live.LiveActivity;
import com.core.op.lib.di.PerActivity;


@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, LiveModule.class})
public interface LiveComponent extends ActivityComponent {
    void inject(LiveActivity activity);
}