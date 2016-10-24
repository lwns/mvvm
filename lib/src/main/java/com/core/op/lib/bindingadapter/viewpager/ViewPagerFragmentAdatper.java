package com.core.op.lib.bindingadapter.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/9/12
 */
public class ViewPagerFragmentAdatper extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public ViewPagerFragmentAdatper(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerFragmentAdatper(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }
}
