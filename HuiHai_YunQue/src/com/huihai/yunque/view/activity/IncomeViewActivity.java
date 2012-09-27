package com.huihai.yunque.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.huihai.yunque.R;
import com.huihai.yunque.view.fragment.adapter.ViewPagerFragmentAdapter;
import com.viewpagerindicator.TitlePageIndicator;

public class IncomeViewActivity extends FragmentActivity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState );
        setContentView(R.layout.page_view_indicator);

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        final ViewPager pager = (ViewPager)findViewById( R.id.pager);
        TitlePageIndicator indicator = (TitlePageIndicator)findViewById( R.id.indicator );
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
//                Intent toListViewIntent = new Intent(IncomeViewActivity.this, IncomeListViewActivity.class);
//                pager.addView(IncomeViewActivity.this.startActivity("income", 
//                        toListViewIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
//                Toast.makeText(IncomeViewActivity.this, "Changed to page " + position, Toast.LENGTH_SHORT).show();
            }
            
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}