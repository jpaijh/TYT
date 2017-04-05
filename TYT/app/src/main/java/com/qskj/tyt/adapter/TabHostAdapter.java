package com.qskj.tyt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 主界面: TableHost 的数据适配器
 * Created by 赵 鑫 on 2015/8/24.
 */
public class TabHostAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listFragment;

    public TabHostAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int arg0) {
        return listFragment.get(arg0);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

}
