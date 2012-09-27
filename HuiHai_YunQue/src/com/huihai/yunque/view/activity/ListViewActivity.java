package com.huihai.yunque.view.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import com.huihai.yunque.R;
import com.huihai.yunque.R.drawable;
import com.huihai.yunque.R.layout;
import android.os.Bundle;
import android.widget.TabHost;

public class ListViewActivity extends TabActivity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_view);

        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, ExpandableDataGroupActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        Intent allItems = new Intent(this, IncomeListViewActivity.class);
        spec = tabHost.newTabSpec("all").setIndicator("所有",
                          res.getDrawable(R.drawable.ic_tab_all))
                      .setContent(allItems);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, ExpandableDataGroupActivity.class);
        spec = tabHost.newTabSpec("albums").setIndicator("按类别",
                          res.getDrawable(R.drawable.ic_tab_type))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ExpandableDataGroupActivity.class);
        spec = tabHost.newTabSpec("songs").setIndicator("按时间",
                          res.getDrawable(R.drawable.ic_tab_clock))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
    
}
