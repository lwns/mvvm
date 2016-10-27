package com.core.op.feature.main.home.bangumi;


import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;

import com.core.op.R;
import com.core.op.databinding.FrgBangumiBinding;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.bindingadapter.common.BaseItemViewSelector;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.domain.bean.BangumiRecommend;
import com.domain.bean.NewBangumiSerial;
import com.domain.bean.SeasonNewBangumi;
import com.domain.interactor.UseCase;
import com.domain.interactor.main.BangumiIndexUserCase;
import com.domain.interactor.main.BangumiRecommendUserCase;
import com.domain.interactor.main.BangumiSerialUserCase;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.core.op.feature.main.home.bangumi.BangumiItemViewModel.BANGUMI_TYPE_BANNER;
import static com.core.op.feature.main.home.bangumi.BangumiItemViewModel.BANGUMI_TYPE_RECOMMEND;
import static com.core.op.feature.main.home.bangumi.BangumiItemViewModel.BANGUMI_TYPE_SEASON;
import static com.core.op.feature.main.home.bangumi.BangumiItemViewModel.BANGUMI_TYPE_SERIAL;

@PerActivity
public final class BangumiViewModel extends BFViewModel<FrgBangumiBinding> {

    private final RxAppCompatActivity activity;

    private FragmentManager fragmentManager;

    private UseCase<BangumiRecommend> recommendUserCase;

    private UseCase<NewBangumiSerial> serialUserCase;

    private UseCase<SeasonNewBangumi> seasonUserCase;

    public final ObservableField<Boolean> refresh = new ObservableField<>(false);  // viewModel for binding.recycle
    public final List<BangumiItemViewModel> itemViewModel = new ArrayList<>();
    // view layout for binding.recycle
    public final ItemViewSelector<BangumiItemViewModel> itemView = new BaseItemViewSelector<BangumiItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, BangumiItemViewModel itemViewModel) {
            if (itemViewModel.getType() == BANGUMI_TYPE_BANNER) {
                itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_bangumi_banner);
            } else if (itemViewModel.getType() == BANGUMI_TYPE_SEASON) {
                itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_bangumi_index);
            } else if (itemViewModel.getType() == BANGUMI_TYPE_SERIAL) {
                itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_bangumi_recommend);
            } else {
                itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_bangumi_item);
            }
        }

        @Override
        public int viewTypeCount() {
            return 4;
        }
    };

    public final ReplyCommand onLoadCommand = new ReplyCommand(() -> {
        loadData();
    });

    @Inject
    public BangumiViewModel(RxAppCompatActivity activity,
                            @Named("bangumiindex") BangumiIndexUserCase bangumiIndexUserCase,
                            @Named("bangumiserial") BangumiSerialUserCase bangumiSerialUserCase,
                            @Named("bangumirecommend") BangumiRecommendUserCase bangumiRecommendUserCase) {
        this.activity = activity;
        this.recommendUserCase = bangumiRecommendUserCase;
        this.serialUserCase = bangumiSerialUserCase;
        this.seasonUserCase = bangumiIndexUserCase;
    }


    public void setFragment(RxFragment fragment) {
        this.fragment = fragment;
        this.fragmentManager = fragment.getChildFragmentManager();
    }

    @Override
    public void afterViews() {
        fragmentManager = fragment.getChildFragmentManager();
        loadData();
    }

    List<BangumiRecommend.RecommendsBean> recommends = new ArrayList<>();

    private void loadData() {
        recommends.clear();
        refresh.set(true);
        recommendUserCase.executeNoSchedule()
                .compose(fragment.bindToLifecycle())
                .doOnNext(data -> {
                    itemViewModel.clear();
                    itemViewModel.add(new BangumiItemViewModel(activity, BANGUMI_TYPE_BANNER, data.getBanners()));
                    recommends.addAll(data.getRecommends());
                })
                .flatMap(new Func1<BangumiRecommend, Observable<SeasonNewBangumi>>() {
                    @Override
                    public Observable<SeasonNewBangumi> call(BangumiRecommend bangumiRecommend) {
                        return seasonUserCase.executeNoSchedule();
                    }
                })
                .compose(fragment.bindToLifecycle())
                .doOnNext(data -> {
                    itemViewModel.add(new BangumiItemViewModel(activity, BANGUMI_TYPE_SEASON).setSeasonList(data.getList()));
                })
                .flatMap(new Func1<SeasonNewBangumi, Observable<NewBangumiSerial>>() {
                    @Override
                    public Observable<NewBangumiSerial> call(SeasonNewBangumi seasonNewBangumi) {
                        return serialUserCase.execute();
                    }
                })
                .compose(fragment.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewBangumiSerial>() {
                    @Override
                    public void call(NewBangumiSerial newBangumiSerial) {
                        itemViewModel.add(new BangumiItemViewModel(activity, BANGUMI_TYPE_SERIAL).setSerialList(newBangumiSerial.getList()));
                        Observable.from(recommends).subscribe(d -> {
                            itemViewModel.add(new BangumiItemViewModel(activity, BANGUMI_TYPE_RECOMMEND, d));
                        });
                        refresh.set(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        refresh.set(false);
                    }
                }, () -> {
                    binding.recycle.getAdapter().notifyDataSetChanged();
                });
    }
}