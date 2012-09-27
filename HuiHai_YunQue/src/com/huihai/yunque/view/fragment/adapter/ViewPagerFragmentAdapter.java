package com.huihai.yunque.view.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.huihai.yunque.view.fragment.IncomeListFragment;
import com.viewpagerindicator.TitleProvider;

/**
 * adaptor for the view pager fragment.
 * it takes care of how to create the fragment inside the view pager.
 * @author lemon
 *
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter implements TitleProvider{
    
    /**
     * the list of title list.
     */
    private static String[] titles = new String[] { "全部", "按类型", "按日期" };
    
    /**
     * default constructor.
     * @param fm the {@link FragmentManager}
     */
    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * get the title on the specified position.
     */
    @Override
    public String getTitle(int position) {
        return titles[position];
    }

    /**
     * the count of the title list.
     */
    @Override
    public int getCount() {
        return titles.length;
    }

    /**
     * get item on each position. 
     * should create each item according its position.
     * @return a created {@link Fragment}. it has to be a fragment.
     */
    @Override
    public Fragment getItem(int position) {
        // create the fragment item. it can both use the fragment class and the layout xml files.
        return IncomeListFragment.newInstance(Types.values()[position]);
    }
    
    public static enum Types{
        All, ByType, ByDate
    }

}
