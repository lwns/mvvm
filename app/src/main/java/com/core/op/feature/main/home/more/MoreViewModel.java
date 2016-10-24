package com.core.op.feature.main.home.more;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentManager;

import com.core.op.BR;
import com.core.op.R;
import com.core.op.feature.main.home.bangumi.BangumiSeasonItemViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.di.PerActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public final class MoreViewModel implements BViewModel {

    private final RxAppCompatActivity activity;
    RxFragment fragment;
    private FragmentManager fragmentManager;

    // viewModel for RecyclerView
    public final ObservableList<MoreItem> itemViewModel = new ObservableArrayList<>();
    // view layout for RecyclerView
    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.include_main_more_item);

    private String[] itemNames = new String[]{
            "直播",
            "番剧",
            "动画",
            "音乐",
            "舞蹈",
            "游戏",
            "科技",
            "生活",
            "鬼畜",
            "时尚",
            "广告",
            "娱乐",
            "电影",
            "电视剧",
            "游戏中心",
    };

    private int[] itemIcons = new int[]{
            R.drawable.ic_category_live,
            R.drawable.ic_category_t13,
            R.drawable.ic_category_t1,
            R.drawable.ic_category_t3,
            R.drawable.ic_category_t129,
            R.drawable.ic_category_t4,
            R.drawable.ic_category_t36,
            R.drawable.ic_category_t160,
            R.drawable.ic_category_t119,
            R.drawable.ic_category_t155,
            R.drawable.ic_category_t165,
            R.drawable.ic_category_t5,
            R.drawable.ic_category_t23,
            R.drawable.ic_category_t11,
            R.drawable.ic_category_game_center
    };

    @Inject
    public MoreViewModel(RxAppCompatActivity activity) {
        this.activity = activity;
        for (int i = 0; i < itemIcons.length; i++) {
            itemViewModel.add(new MoreItem(itemNames[i], activity.getDrawable(itemIcons[i])));
        }
    }

    public void setFragment(RxFragment fragment) {
        this.fragment = fragment;
        this.fragmentManager = fragment.getChildFragmentManager();
    }

    public static class MoreItem {
        public MoreItem(String title, Drawable resource) {
            this.title = title;
            this.resource = resource;
        }

        private Drawable resource;

        private String title;

        public Drawable getResource() {
            return resource;
        }

        public void setResource(Drawable resource) {
            this.resource = resource;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}