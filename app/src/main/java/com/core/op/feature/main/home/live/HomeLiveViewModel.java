package com.core.op.feature.main.home.live;


import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.core.op.R;
import com.core.op.databinding.FrgHomeliveBinding;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.bindingadapter.common.BaseItemViewSelector;
import com.core.op.lib.bindingadapter.common.ItemView;
import com.core.op.lib.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.bindingadapter.common.LayoutManagers;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.domain.bean.LiveIndex;
import com.domain.interactor.UseCase;
import com.domain.interactor.main.LiveIndexUserCase;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;


@PerActivity
public final class HomeLiveViewModel extends BFViewModel<FrgHomeliveBinding> {

    private RxAppCompatActivity activity;

    private final FragmentManager fragmentManager;

    private UseCase<LiveIndex> useCase;

    private int entranceSize = 4;

    public final ObservableField<Boolean> refresh = new ObservableField<>(false);  // viewModel for RecyclerView
    public final List<HomeLiveItemViewModel> itemViewModel = new ArrayList<>();
    // view layout for RecyclerView
    public final ItemViewSelector<HomeLiveItemViewModel> itemView = new BaseItemViewSelector<HomeLiveItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, HomeLiveItemViewModel itemViewModel) {
            if (position == 0) {
                itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_banner);
            } else {
                position -= 1;
                if (position < entranceSize) {
                    itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_entrance);
                } else if ((position - entranceSize) % 5 == 0) {
                    itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_partitiontitle);
                } else {
                    itemView.set(com.core.op.BR.viewModel, R.layout.include_main_home_live_partition);
                }
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

    public final LayoutManagers.LayoutManagerFactory factory = new LayoutManagers.LayoutManagerFactory() {
        @Override
        public RecyclerView.LayoutManager create(RecyclerView recyclerView) {
            GridLayoutManager layoutManager = new GridLayoutManager(activity, 12);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 12;

                    } else {
                        position -= 1;
                        if (position < entranceSize) {
                            return 3;
                        } else if ((position - entranceSize) % 5 == 0) {
                            return 12;
                        } else {
                            return 6;
                        }
                    }
                }
            });
            return layoutManager;
        }
    };

    public final GridLayoutManager layoutManager = new GridLayoutManager(activity, 12);

    @Inject
    public HomeLiveViewModel(RxAppCompatActivity activity, @Named("liveindex") LiveIndexUserCase liveIndexUserCase) {
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
        this.useCase = liveIndexUserCase;
    }

    @Override
    public void afterViews() {
        loadData();
    }

    private void loadData() {
        refresh.set(true);
        useCase.execute()
                .compose(fragment.bindToLifecycle())
                .doOnNext(data -> {
                    Observable.just(data.banner).subscribe(d -> {
                        itemViewModel.clear();
                        itemViewModel.add(0, new HomeLiveItemViewModel(activity, data.banner));
                    });
                }).doOnNext(data ->
                Observable.from(data.entranceIcons).take(4).flatMap(m ->
                        Observable.just(m)
                ).subscribe(d ->
                        itemViewModel.add(new HomeLiveItemViewModel(activity, d)))
        ).flatMap(n -> Observable.from(n.partitions))
                .doOnNext(s -> itemViewModel.add(new HomeLiveItemViewModel(activity, s.partition)))
                .flatMap(d -> Observable.from(d.lives).take(4))
                .subscribe(s -> {
                    itemViewModel.add(new HomeLiveItemViewModel(activity, s));
                    refresh.set(false);
                }, e -> {
                    refresh.set(false);
                }, () -> {
                    binding.recycle.getAdapter().notifyDataSetChanged();
                    refresh.set(false);
                });
    }
}