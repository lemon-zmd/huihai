package com.huihai.yunque.view.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import com.huihai.yunque.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

public class QuickWidgetActivity extends Activity {

    int mAppWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.widget_main);
        Log.i("myLog", " on WidgetConf ... ");

        setResult(RESULT_CANCELED);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If they gave us an intent without the widget id, just bail.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);

        setResult(RESULT_OK, resultValue);
        finish();
        // ImageButton addBtn = (ImageButton) findViewById(R.id.widget_btn_add);
        // initClickEvent();

    }

    private void initClickEvent() {
        showQuickAction();
    }

    private void showQuickAction() {

        ImageButton deleteBtn = (ImageButton) this.findViewById(R.id.widget_btn_backend);
        // final QuickAction quickAction =
        // QuickActionView.createQuickAction(this, deleteBtn);
        // deleteBtn.setOnClickListener(new View.OnClickListener() {
        // // @Override
        // public void onClick(View v) {
        // quickAction.show(v);
        // System.out.println("got it here ....");
        //
        // // return OK
        // Intent resultValue = new Intent();
        // resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
        // mAppWidgetId);
        //
        // setResult(RESULT_OK, resultValue);
        // finish();
        // }
        // });

    }
}
