package com.huihai.yunque.view.activity;

import android.app.Activity;
import android.content.Intent;
import com.huihai.yunque.R;
import com.huihai.yunque.R.drawable;
import com.huihai.yunque.R.id;
import com.huihai.yunque.R.layout;
import com.huihai.yunque.persist.common.PersistUtil;
import com.huihai.yunque.view.activity.common.quickaction.ActionItem;
import com.huihai.yunque.view.activity.common.quickaction.QuickAction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity{
 // Menu item Ids
    public static final int VERTICAL_ID = Menu.FIRST;
    public static final int HORIZONTAL_ID = Menu.FIRST + 1;

    public static final int TOP_ID = Menu.FIRST + 2;
    public static final int MIDDLE_ID = Menu.FIRST + 3;
    public static final int BOTTOM_ID = Menu.FIRST + 4;

    public static final int LEFT_ID = Menu.FIRST + 5;
    public static final int CENTER_ID = Menu.FIRST + 6;
    public static final int RIGHT_ID = Menu.FIRST + 7; 
    
    
    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_UPLOAD = 3;
    
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        
        // init the db for future use. 
        PersistUtil.initDB(this);
        
        // add listeners for each model.
        addCreateAction();
        addViewAction();
        
        initQuickActionForButtons();
    }
    
    private void initQuickActionForButtons() {
        addDeleteAction();
        //1 create action items
//        ActionItem addAction = new ActionItem();
    }
    
    private void addDeleteAction(){
        ImageButton deleteBtn = (ImageButton) this.findViewById(R.id.deleteModel);

        ActionItem addItem      = new ActionItem(ID_ADD, "Add", getResources().getDrawable(R.drawable.ic_add));
        ActionItem acceptItem   = new ActionItem(ID_ACCEPT, "Accept", getResources().getDrawable(R.drawable.ic_accept));
        ActionItem uploadItem   = new ActionItem(ID_UPLOAD, "Upload", getResources().getDrawable(R.drawable.ic_up));
       
        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        uploadItem.setSticky(true);
        
        final QuickAction mQuickAction  = new QuickAction(this);
        
        mQuickAction.addActionItem(addItem);
        mQuickAction.addActionItem(acceptItem);
        mQuickAction.addActionItem(uploadItem);
        
        //setup the action item click listener
        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
//          @Override
            public void onItemClick(QuickAction quickAction, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);
                
                if (actionId == ID_ADD) {
                    Toast.makeText(getApplicationContext(), "Add item selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), actionItem.getTitle() + " selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
//          @Override
            public void onDismiss() {
                Toast.makeText(getApplicationContext(), "Ups..dismissed", Toast.LENGTH_SHORT).show();
            }
        });
        
        deleteBtn.setOnClickListener(new View.OnClickListener() {
//          @Override
            public void onClick(View v) {
                mQuickAction.show(v);
            }
        });
    }

    private void addCreateAction(){
        ImageButton createBtn = (ImageButton) this.findViewById(R.id.addModel);
//        final Intent addIntent = new Intent(this, EditActivity.class);
        createBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToCreate();
//                MainActivity.this.startActivity(addIntent);
            }
        }); 
    }
    private void goToCreate(){
        Intent addIntent = new Intent(this, FunctionViewActivity.class);
        startActivity(addIntent);
    }
    
    private void gotoView(){
        Intent viewContent = new Intent(this, ListViewActivity.class);
        startActivity(viewContent);
    }
    
    private void addViewAction(){
        ImageButton viewBtn = (ImageButton) this.findViewById(R.id.viewModel);
//        final Intent viewIntent = new Intent(this, ExpandableDataGroupActivity.class);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                MainActivity.this.startActivity(viewIntent);
                gotoView();
            }
        });
    }
    
    // create the options menu.
    public boolean onCreateOptionsMenu(Menu menu){
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, VERTICAL_ID, 0, "Ìí¼Ó");
        menu.add(0, HORIZONTAL_ID, 0, "É¾³ý");
        menu.add(0, TOP_ID, 0, "²é¿´");
        return result;
        
    }
    
    // when option menu is selected.
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
        case VERTICAL_ID:
            goToCreate();
            return true;
        case HORIZONTAL_ID:
            return true;
        case TOP_ID:
            gotoView();
            return true;
        }
            
        return super.onOptionsItemSelected(item);
        
        // go to the edit for now.
//        Intent editIntent = new Intent(this, EditActivity.class);
//        this.startActivityForResult(editIntent, 0);
//        Intent anotherView = new Intent(this, ExpandableDataGroupActivity.class);
//        this.startActivity(anotherView);
//        return true;
    }

}
