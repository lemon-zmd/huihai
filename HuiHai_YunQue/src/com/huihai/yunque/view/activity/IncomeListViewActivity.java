package com.huihai.yunque.view.activity;

/**
 * 采购主页面
 */
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.huihai.yunque.R;
import com.huihai.yunque.persist.dao.ItemsDAO;
import com.huihai.yunque.view.activity.common.quickaction.ActionItem;
import com.huihai.yunque.view.activity.common.quickaction.QuickAction;

public class IncomeListViewActivity extends ListActivity {

    private static final int DELETE_ID = Menu.FIRST;
    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_UPLOAD = 3;
    
    public void onCreate(Bundle bd) {
        super.onCreate(bd);
        setContentView(R.layout.income_view);
        fillData();
        this.registerForContextMenu(getListView());
        onAddItem();
    }

    /**
     * add long touch menu here. let user delete it.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("选择");
        menu.add(0, DELETE_ID, 0, "删除");
        // menu.add(0, 1, 1, "其它");
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case DELETE_ID:
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            Log.i("[lemon]", info.id + "");
            ItemsDAO.getInstance().deleteById(info.id);
            fillData();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void fillData() {
        Cursor listCursor = ItemsDAO.getInstance().findAllItems();
        SimpleCursorAdapter items = new SimpleCursorAdapter(this, R.layout.common_list_item, listCursor, new String[] {
                "item_name", "person_name", "happen_time", "_id" }, new int[] { R.id.common_item_1, R.id.common_item_2,
                R.id.happen_time, R.id.item_id_hidden });
        setListAdapter(items);
    }
    
    public void onListItemClick(ListView l, View v,int position, long id){

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
        
        mQuickAction.show(v);
    
    }
    
    private void onAddItem(){
        
        final Button addbtn = (Button) findViewById(R.id.addBtn);
        final LinearLayout typeSelector = (LinearLayout) findViewById(R.id.type_selector);
        if (null != addbtn) {
            addbtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    typeSelector.setVisibility(View.VISIBLE);
                    Button collapseBtn = (Button) findViewById(R.id.collapse_btn);
                    collapseBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            typeSelector.setVisibility(View.GONE);
                        }
                    });
                    
                    Button gotoEditBtn = (Button) findViewById(R.id.goto_common_edit);
                    gotoEditBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent addIntent = new Intent(IncomeListViewActivity.this, EditActivity.class);
                            startActivityForResult(addIntent, 1);
                        }
                    });
                    
                    
                }
            });
        }
    }

}
