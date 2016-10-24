package com.core.op.feature.main.home.recommend;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v4.app.FragmentManager;

import com.core.op.R;
import com.core.op.feature.main.home.live.HomeLiveItemViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.bindingadapter.common.BaseItemViewSelector;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.di.PerActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public final class RecommendViewModel implements BViewModel {

    private final RxAppCompatActivity activity;
    RxFragment fragment;
    private FragmentManager fragmentManager;

//    // viewModel for RecyclerView
//    public final ObservableList<HomeLiveItemViewModel> itemViewModel = new ObservableArrayList<>();
//    // view layout for RecyclerView
//    public final ItemViewSelector<HomeLiveItemViewModel> itemView = new BaseItemViewSelector<HomeLiveItemViewModel>() {
//        @Override
//        public void select(ItemView itemView, int position, HomeLiveItemViewModel itemViewModel) {
//            if (position == 0) {
//                itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_recommend_banner);
//            } else {
//                position -= 1;
//                if (position < entranceSize) {
//                    itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_entrance);
//                } else if ((position - entranceSize) % 5 == 0) {
//                    itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_partitiontitle);
//                } else {
//                    itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_partition);
//                }
//            }
//        }
//
//        @Override
//        public int viewTypeCount() {
//            return 4;
//        }
//    };

    @Inject
    public RecommendViewModel(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    public void setFragment(RxFragment fragment) {
        this.fragment = fragment;
        this.fragmentManager = fragment.getChildFragmentManager();
    }
}