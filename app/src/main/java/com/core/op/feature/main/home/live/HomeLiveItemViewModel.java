package com.core.op.feature.main.home.live;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.core.op.BR;
import com.core.op.R;
import com.core.op.feature.live.LiveActivity;
import com.core.op.feature.main.BannerItemViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.domain.bean.BannerEntity;
import com.domain.bean.Entrance;
import com.domain.bean.Live;
import com.domain.bean.LiveIndex;
import com.domain.bean.PartitionSub;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/10
 */
public class HomeLiveItemViewModel implements BViewModel {

    RxAppCompatActivity activity;

    PartitionSub partitionSub;

    List<BannerEntity> banner;

    Entrance entrance;
    Live live;

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.include_main_banner_item);
    public final ObservableList<BannerItemViewModel> viewModels = new ObservableArrayList<>();

    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> typeUri = new ObservableField<>();
    public final ObservableField<String> typeName = new ObservableField<>();
    public final ObservableField<SpannableStringBuilder> typePerson = new ObservableField<>();

    public final ObservableField<String> liveUri = new ObservableField<>();
    public final ObservableField<String> liveHeadUri = new ObservableField<>();
    public final ObservableField<String> liveTitle = new ObservableField<>();
    public final ObservableField<String> liveName = new ObservableField<>();
    public final ObservableField<String> livePerson = new ObservableField<>();

    public final ReplyCommand liveClick = new ReplyCommand(() -> {
        LiveActivity.launch(
                activity,
                live.room_id,
                live.title,
                live.online,
                live.owner.face,
                live.owner.name,
                live.owner.mid);
    });

    public HomeLiveItemViewModel(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    public HomeLiveItemViewModel(RxAppCompatActivity activity, List<BannerEntity> banner) {
        this.activity = activity;
        this.banner = banner;
        for (BannerEntity bannerEntity : banner) {
            viewModels.add(new BannerItemViewModel(bannerEntity.img));
        }
    }

    public HomeLiveItemViewModel(RxAppCompatActivity activity, Entrance entrance) {
        this.activity = activity;
        this.entrance = entrance;
        imageUrl.set(entrance.entrance_icon.src);
        title.set(entrance.name);
    }

    public HomeLiveItemViewModel(RxAppCompatActivity activity, PartitionSub partitionSub) {
        this.activity = activity;
        this.partitionSub = partitionSub;
        typeUri.set(partitionSub.sub_icon.src);
        typeName.set(partitionSub.name);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("当前" + partitionSub.count + "个直播");
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
                activity.getResources().getColor(R.color.app_text_pink));
        stringBuilder.setSpan(foregroundColorSpan, 2,
                stringBuilder.length() - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        typePerson.set(stringBuilder);
    }

    public HomeLiveItemViewModel(RxAppCompatActivity activity, Live live) {
        this.activity = activity;
        this.live = live;
        liveUri.set(live.cover.src);
        liveHeadUri.set(live.owner.face);
        liveTitle.set(live.title);
        liveName.set(live.owner.name);
        livePerson.set(live.online + "");
    }
}
