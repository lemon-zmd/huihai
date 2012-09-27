package com.huihai.yunque.view.activity;


import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.huihai.yunque.R;
import com.huihai.yunque.persist.common.PersistUtil;

public class FunctionViewActivity extends ActivityGroup{
    
    private FrameLayout container = null;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // init the db for future use. 
        boolean ready = PersistUtil.initDB(this);

        if (!ready) { // if not ready.
            Toast.makeText(this, "请插入SD卡或等待SD卡加载完成", Toast.LENGTH_SHORT).show();
            return;
        }
        // hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.function_view);

        container = (FrameLayout) findViewById(R.id.content_container);

        /**
         * add dashboard view.
         */
        final Button dashboard = (Button) findViewById(R.id.dashboard);
        dashboard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllState();
                dashboard.setBackgroundResource(R.drawable.dashboard_enable);
                container.removeAllViews();
                Intent dashboard = new Intent(FunctionViewActivity.this, MainActivity.class);
                container.addView(getLocalActivityManager().startActivity("dashboard", 
                        dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
                
            }
        });

        /**
         * add payment view.
         */
        final Button payment = (Button) findViewById(R.id.payment);
        payment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllState();
                payment.setBackgroundResource(R.drawable.outbox_enable);
                container.removeAllViews();
                Intent toListViewIntent = new Intent(FunctionViewActivity.this, IncomeViewActivity.class);
                container.addView(getLocalActivityManager().startActivity("payment", 
                        toListViewIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
            }
        });

        /**
         * add income view.
         */
        final Button income = (Button) findViewById(R.id.income);
        income.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllState();
                income.setBackgroundResource(R.drawable.inbox_enable);
                container.removeAllViews();
                Intent toListViewIntent = new Intent(FunctionViewActivity.this, IncomeViewActivity.class);
                container.addView(getLocalActivityManager().startActivity("income", 
                        toListViewIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
            }
        });

        /**
         * add both view.
         */
        final Button both = (Button) findViewById(R.id.both);
        both.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllState();
                both.setBackgroundResource(R.drawable.shuffle_enable);
                container.removeAllViews();
                Intent toListViewIntent = new Intent(FunctionViewActivity.this, EditActivity.class);
                container.addView(getLocalActivityManager().startActivity("edit", 
                        toListViewIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
            }
        });

        /**
         * add setting view.
         */
        final Button settings = (Button) findViewById(R.id.setting);
        settings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllState();
                settings.setBackgroundResource(R.drawable.settings_enable);
                container.removeAllViews();
                Intent toListViewIntent = new Intent(FunctionViewActivity.this, IncomeViewActivity.class);
                container.addView(getLocalActivityManager().startActivity("settings", 
                        toListViewIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView());
            }
        });
        
    }

    /**
     * clear all selected state.
     */
    private void clearAllState(){
        Button income = (Button) findViewById(R.id.income);
        Button output = (Button) findViewById(R.id.payment);
        Button dashboard = (Button) findViewById(R.id.dashboard);
        Button settings = (Button) findViewById(R.id.setting);
        Button both = (Button) findViewById(R.id.both);
        
        income.setBackgroundResource(R.drawable.inbox);
        output.setBackgroundResource(R.drawable.outbox);
        dashboard.setBackgroundResource(R.drawable.dashboard);
        settings.setBackgroundResource(R.drawable.settings);
        both.setBackgroundResource(R.drawable.shuffle);
    }
    
}
