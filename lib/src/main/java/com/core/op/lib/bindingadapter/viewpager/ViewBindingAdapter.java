package com.core.op.lib.bindingadapter.viewpager;

import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.core.op.lib.command.ReplyCommand;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kelin on 16-6-1.
 */
public class ViewBindingAdapter {

    @BindingAdapter(value = {"frgManager", "fragemnts"}, requireAll = false)
    public static void setAdapter(final ViewPager viewPager, final FragmentManager fragmentManager, List<Fragment> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        ViewPagerFragmentAdatper adatper = new ViewPagerFragmentAdatper(fragmentManager, items);
        viewPager.setAdapter(adatper);
    }

    @BindingAdapter(value = {"selectPosition"}, requireAll = false)
    public static void setAdapter(final ViewPager viewPager, final Integer position) {
        viewPager.setCurrentItem(position);
    }

    @BindingAdapter(value = {"onPageScrolledCommand", "onPageSelectedCommand", "onPageScrollStateChangedCommand"}, requireAll = false)
    public static void onScrollChangeCommand(final ViewPager viewPager,
                                             final ReplyCommand<ViewPagerDataWrapper> onPageScrolledCommand,
                                             final ReplyCommand<Integer> onPageSelectedCommand,
                                             final ReplyCommand<Integer> onPageScrollStateChangedCommand) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int state;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (onPageScrolledCommand != null) {
                    onPageScrolledCommand.execute(new ViewPagerDataWrapper(position, positionOffset, positionOffsetPixels, state));
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (onPageSelectedCommand != null) {
                    onPageSelectedCommand.execute(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                this.state = state;
                if (onPageScrollStateChangedCommand != null) {
                    onPageScrollStateChangedCommand.execute(state);
                }
            }
        });

    }

    public static class ViewPagerDataWrapper {
        public float positionOffset;
        public float position;
        public int positionOffsetPixels;
        public int state;

        public ViewPagerDataWrapper(float position, float positionOffset, int positionOffsetPixels, int state) {
            this.positionOffset = positionOffset;
            this.position = position;
            this.positionOffsetPixels = positionOffsetPixels;
            this.state = state;
        }
    }
}
