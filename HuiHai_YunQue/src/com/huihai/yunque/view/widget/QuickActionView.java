package com.huihai.yunque.view.widget;

import android.app.Activity;
import com.huihai.yunque.R;
import com.huihai.yunque.view.activity.common.quickaction.ActionItem;
import com.huihai.yunque.view.activity.common.quickaction.QuickAction;

import android.view.View;
import android.widget.Toast;

public class QuickActionView {

    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_UPLOAD = 3;
    
    public static QuickAction createQuickAction(final Activity context, View v){

        ActionItem addItem      = new ActionItem(ID_ADD, "Add", context.getResources().getDrawable(R.drawable.ic_add));
        ActionItem acceptItem   = new ActionItem(ID_ACCEPT, "Accept", context.getResources().getDrawable(R.drawable.ic_accept));
        ActionItem uploadItem   = new ActionItem(ID_UPLOAD, "Upload", context.getResources().getDrawable(R.drawable.ic_up));
       
        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        uploadItem.setSticky(true);
        
        final QuickAction mQuickAction  = new QuickAction(context);
        
        mQuickAction.addActionItem(addItem);
        mQuickAction.addActionItem(acceptItem);
        mQuickAction.addActionItem(uploadItem);
        
        //setup the action item click listener
        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            public void onItemClick(QuickAction quickAction, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                if (actionId == ID_ADD) {
                    Toast.makeText(context.getApplicationContext(), "Add item selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context.getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
            public void onDismiss() {
                Toast.makeText(context.getApplicationContext(), "Ups..dismissed", Toast.LENGTH_SHORT).show();
            }
        });

        return mQuickAction;
    }
}
