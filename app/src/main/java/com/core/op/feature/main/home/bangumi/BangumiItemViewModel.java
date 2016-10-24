package com.core.op.feature.main.home.bangumi;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.core.op.BR;
import com.core.op.R;
import com.core.op.feature.main.BannerItemViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.di.PerActivity;
import com.domain.bean.BangumiRecommend;
import com.domain.bean.NewBangumiSerial;
import com.domain.bean.SeasonNewBangumi;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/18
 */
@PerActivity
public class BangumiItemViewModel implements BViewModel {

    public static final int BANGUMI_TYPE_BANNER = 0;
    public static final int BANGUMI_TYPE_RECOMMEND = 1;
    public static final int BANGUMI_TYPE_SEASON = 2;
    public static final int BANGUMI_TYPE_SERIAL = 3;

    RxAppCompatActivity activity;

    List<BangumiRecommend.BannersBean> banner;
    BangumiRecommend.RecommendsBean recommendsBean;

    private int type = -1;

    public final boolean hasFixedSize = false;
    public final boolean scrollingEnabled = false;

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.include_main_banner_item);
    public final ObservableList<BannerItemViewModel> viewModels = new ObservableArrayList<>();


    // viewModel for RecyclerView
    public final ObservableList<BangumiSeasonItemViewModel> recommendModels = new ObservableArrayList<>();
    // view layout for RecyclerView
    public final ItemView recommendItemView = ItemView.of(BR.viewModel, R.layout.include_main_home_bangumi_index_item);

    // viewModel for RecyclerView
    public final ObservableList<BangumiSeasonItemViewModel> seasonModels = new ObservableArrayList<>();
    // view layout for RecyclerView
    public final ItemView seasonItemView = ItemView.of(BR.viewModel, R.layout.include_main_home_bangumi_index_item);

    public final ObservableField<String> recommedImg = new ObservableField<>();
    public final ObservableField<String> recommedTitle = new ObservableField<>();
    public final ObservableField<String> recommedDesc = new ObservableField<>();

    @Inject
    public BangumiItemViewModel(RxAppCompatActivity activity, int type) {
        this.activity = activity;
        this.type = type;
    }

    public BangumiItemViewModel(RxAppCompatActivity activity, int type, List<BangumiRecommend.BannersBean> banner) {
        this.activity = activity;
        this.type = type;
        this.banner = banner;
        for (BangumiRecommend.BannersBean bannerEntity : banner) {
            viewModels.add(new BannerItemViewModel(bannerEntity.getImg()));
        }
    }

    public BangumiItemViewModel(RxAppCompatActivity activity, int type, BangumiRecommend.RecommendsBean recommendsBean) {
        this.activity = activity;
        this.type = type;
        this.recommendsBean = recommendsBean;
        recommedImg.set(recommendsBean.getPic());
        recommedTitle.set(recommendsBean.getTitle());
        recommedTitle.set(recommendsBean.getDescription());
    }


    public BangumiItemViewModel setSerialList(List<NewBangumiSerial.ListBean> listBean) {
        Observable.from(listBean).take(6).flatMap(d -> {
            return Observable.just(d);
        }).subscribe(n -> {
            recommendModels.add(new BangumiSeasonItemViewModel(activity, n));
        });
        return this;
    }

    public BangumiItemViewModel setSeasonList(List<SeasonNewBangumi.ListBean> listBean) {
        Observable.from(listBean).take(6).flatMap(d -> {
            return Observable.just(d);
        }).subscribe(n -> {
            seasonModels.add(new BangumiSeasonItemViewModel(activity, n));
        });
        return this;
    }

    public int getType() {
        return type;
    }
}
