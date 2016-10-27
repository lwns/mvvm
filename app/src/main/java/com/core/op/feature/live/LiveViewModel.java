package com.core.op.feature.live;


import android.content.Intent;
import android.databinding.ObservableField;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.View;

import com.core.op.R;
import com.core.op.databinding.ActLiveBinding;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.AppToast;
import com.domain.interactor.UseCase;
import com.domain.interactor.main.LiveUrlUserCase;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static com.core.op.feature.live.LiveActivity.*;

@PerActivity
public class LiveViewModel extends BAViewModel<ActLiveBinding> {

    private final RxAppCompatActivity activity;

    private UseCase<ResponseBody> useCase;

    private SurfaceHolder holder;

    private IjkMediaPlayer ijkMediaPlayer;

    private int cid;

    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> online = new ObservableField<>();

    public final ObservableField<String> face = new ObservableField<>();

    public final ObservableField<String> name = new ObservableField<>();

    private int mid;

    public ObservableField<AnimationDrawable> mAnimViewBackground = new ObservableField<>();

    public final ObservableField<Integer> mBiliAnimVisible = new ObservableField<>(View.VISIBLE);
    public final ObservableField<Integer> mLoadTextVisible = new ObservableField<>(View.VISIBLE);
    public final ObservableField<Integer> mVideoViewVisible = new ObservableField<>(View.GONE);
    public final ObservableField<Integer> mPlayResId = new ObservableField<>(R.drawable.ic_tv_play);
    public final ObservableField<Integer> mBottomPlayResId = new ObservableField<>(R.drawable.ic_portrait_play);

    private boolean isPlay = false;

    @Inject
    public LiveViewModel(RxAppCompatActivity activity, @Named("liveurl") LiveUrlUserCase liveUrlUserCase) {
        this.activity = activity;
        this.useCase = liveUrlUserCase;
    }

    @Override
    public void afterViews() {
        this.holder = binding.videoView.getHolder();
        mAnimViewBackground.set((AnimationDrawable) activity.getResources().getDrawable(R.drawable.anim_video_loading));
        Intent intent = activity.getIntent();
        if (intent != null) {
            cid = intent.getIntExtra(EXTRA_CID, 0);
            title.set(intent.getStringExtra(EXTRA_TITLE));
            online.set(intent.getIntExtra(EXTRA_ONLINE, 0) + "");
            face.set(intent.getStringExtra(EXTRA_FACE));
            name.set(intent.getStringExtra(EXTRA_NAME));
            mid = intent.getIntExtra(EXTRA_MID, 0);
        }

        initVideo();
    }

    private void initVideo() {
        ijkMediaPlayer = new IjkMediaPlayer();
        getLiveUrl();
    }

    private void getLiveUrl() {
        useCase.setParams(cid + "");
        useCase.execute()
                .compose(activity.bindToLifecycle())
                .map(new Func1<ResponseBody, String>() {

                    @Override
                    public String call(ResponseBody responseBody) {
                        try {
                            String str = responseBody.string();
                            String result = str.substring(str.lastIndexOf("[") + 1,
                                    str.lastIndexOf("]") - 1);
                            return result;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        playVideo(s);
                        return Observable.timer(2000, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        stopAnim();
                        isPlay = true;
                        mVideoViewVisible.set(View.VISIBLE);
                        mPlayResId.set(R.drawable.ic_tv_stop);
                        mBottomPlayResId.set(R.drawable.ic_portrait_stop);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        AppToast.show(activity, "直播地址url获取失败" + throwable.getMessage());
                    }
                });
    }

    private void playVideo(String uri) {
        try {
            ijkMediaPlayer.setDataSource(activity, Uri.parse(uri));
            ijkMediaPlayer.setDisplay(holder);
            holder.addCallback(new SurfaceHolder.Callback() {

                @Override
                public void surfaceCreated(SurfaceHolder holder) {

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                    ijkMediaPlayer.setDisplay(holder);
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.setKeepInBackground(false);
    }

    private void startAnim() {
        mAnimViewBackground.get().start();
    }

    private void stopAnim() {
        mAnimViewBackground.get().stop();
        mBiliAnimVisible.set(View.GONE);
        mLoadTextVisible.set(View.GONE);
    }

    public void onDestroy() {
        ijkMediaPlayer.release();
    }
}