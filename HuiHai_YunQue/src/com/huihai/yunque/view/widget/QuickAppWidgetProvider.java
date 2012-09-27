package com.huihai.yunque.view.widget;

import com.huihai.yunque.view.activity.FunctionViewActivity;
import com.huihai.yunque.view.activity.ListViewActivity;
import com.huihai.yunque.view.activity.MainActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import com.huihai.yunque.R;
import android.util.Log;
import android.widget.RemoteViews;

public class QuickAppWidgetProvider extends AppWidgetProvider {
    /** Called when the activity is first created. */  
      
    final String mPerfName = "com.silenceburn.MyColorNoteConf";  
  
    @Override  
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];  
            Log.i("myLog", "this is [" + appWidgetId + "] onUpdate!");  
        }
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        createEventOnAdd(context, rv, appWidgetManager, appWidgetIds);
        createEventOnView(context, rv, appWidgetManager, appWidgetIds);
    }
    

    private void createEventOnAdd(Context context, RemoteViews remoteView, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        Intent addIntent = new Intent(context, FunctionViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, addIntent, 0);
        remoteView.setOnClickPendingIntent(R.id.widget_btn_add, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteView);
    }
    
    private void createEventOnView(Context context, RemoteViews remoteView, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        Intent addIntent = new Intent(context, ListViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, addIntent, 0);
        remoteView.setOnClickPendingIntent(R.id.widget_btn_chart, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteView);
        
    }
    
    @Override  
    public void onDeleted(Context context, int[] appWidgetIds) {  
        // TODO Auto-generated method stub  
        final int N = appWidgetIds.length;  
        for (int i = 0; i < N; i++) {  
            int appWidgetId = appWidgetIds[i];  
            Log.i("myLog", "this is [" + appWidgetId + "] onDelete!");  
        }  
    }  
  
}  