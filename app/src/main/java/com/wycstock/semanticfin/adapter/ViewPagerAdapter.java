package com.wycstock.semanticfin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wycstock.semanticfin.view.fragment.NewsFragment;
import com.wycstock.semanticfin.view.fragment.ToolFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
    private int mNumOfTabs;
    private NewsFragment newsFragment;
    private ToolFragment toolFragment;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        newsFragment = new NewsFragment();
        toolFragment = new ToolFragment();
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return newsFragment;
            case 1:
                return toolFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}