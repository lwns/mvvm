package com.core.op.feature.main.home.bangumi;

        import android.databinding.ObservableArrayList;
        import android.databinding.ObservableField;
        import android.databinding.ObservableList;

        import com.core.op.BR;
        import com.core.op.R;
        import com.core.op.feature.main.BannerItemViewModel;
        import com.core.op.lib.base.BViewModel;
        import com.core.op.lib.bindingadapter.common.BaseItemViewSelector;
        import com.core.op.lib.bindingadapter.common.ItemView;
        import com.core.op.lib.bindingadapter.common.ItemViewSelector;
        import com.core.op.lib.di.PerActivity;
        import com.domain.bean.BangumiRecommend;
        import com.domain.bean.NewBangumiSerial;
        import com.domain.bean.SeasonNewBangumi;
        import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

        import java.util.List;

        import javax.inject.Inject;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/18
 */
@PerActivity
public class BangumiSeasonItemViewModel implements BViewModel {

    RxAppCompatActivity activity;

    public final ObservableField<String> uri = new ObservableField<>();

    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> play = new ObservableField<>();
    public final ObservableField<String> update = new ObservableField<>();

    public BangumiSeasonItemViewModel(RxAppCompatActivity activity, SeasonNewBangumi.ListBean listBean) {
        this.activity = activity;
        uri.set(listBean.getImageurl());
        title.set(listBean.getTitle());
        play.set("12万人在看");
        update.set("");
    }

    public BangumiSeasonItemViewModel(RxAppCompatActivity activity, NewBangumiSerial.ListBean listBean) {
        this.activity = activity;
        uri.set(listBean.getCover());
        title.set(listBean.getTitle());
        play.set(listBean.getPlay_count() + "人在看");
        update.set("更新至第" + listBean.getBgmcount() + "话");
    }

}
