package com.huihai.yunque.view.activity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableDataGroupActivity extends ExpandableListActivity{
    ExpandableListAdapter mAdapter;  
    
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  
        // Set up our adapter  
        mAdapter = new MyExpandableListAdapter();  
        setListAdapter(mAdapter);  
        // register context menu, when long click the item, it will show a dialog  
        registerForContextMenu(getExpandableListView());  
    }  
  
    @Override  
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {  
        menu.setHeaderTitle("Sample menu");  
        menu.add(0, 0, 0, "expandable_list_sample_action");  
    }  
    
    // create the menu at the bottom.
    public boolean onCreateOptionsMenu(Menu menu){
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, Menu.FIRST, 0, "");
        return result;
        
    }
    
    // action when the menu is selected.
    public boolean onOptionsItemSelected(MenuItem item){
        // go to the edit for now.
//        Intent editIntent = new Intent(this, EditActivity.class);
//        this.startActivityForResult(editIntent, 0);
        Intent anotherView = new Intent(this, ExpandableDataGroupActivity.class);
        this.startActivity(anotherView);
        return true;
        
    }
    
    
    @Override  
    public boolean onContextItemSelected(MenuItem item) {  
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();  
  
        String title = ((TextView) info.targetView).getText().toString();  
          
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);  
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {  
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);   
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);   
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,  
                    Toast.LENGTH_SHORT).show();  
            return true;  
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {  
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);   
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();  
            return true;  
        }  
          
        return false;  
    }  
  
    /** 
     * A simple adapter which maintains an ArrayList of photo resource Ids.  
     * Each photo is displayed as an image. This adapter supports clearing the 
     * list of photos and adding a new photo. 
     * 
     */  
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {  
        // Sample data set.  children[i] contains the children (String[]) for groups[i].  
        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };  
        private String[][] children = {  
                { "Arnold", "Barry", "Chuck", "David" },  
                { "Ace", "Bandit", "Cha-Cha", "Deuce" },  
                { "Fluffy", "Snuggles" },  
                { "Goldy", "Bubbles" }  
        };  
          
        public Object getChild(int groupPosition, int childPosition) {  
            return children[groupPosition][childPosition];  
        }  
  
        public long getChildId(int groupPosition, int childPosition) {  
            return childPosition;  
        }  
  
        public int getChildrenCount(int groupPosition) {  
            return children[groupPosition].length;  
        }  
  
        public TextView getGenericView() {  
            // Layout parameters for the ExpandableListView  
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(  
                    ViewGroup.LayoutParams.FILL_PARENT, 64);  
  
            TextView textView = new TextView(ExpandableDataGroupActivity.this);  
            textView.setLayoutParams(lp);  
            // Center the text vertically  
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);  
            // Set the text starting position  
            textView.setPadding(70, 0, 0, 0);  
            return textView;
        }
          
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,  
                View convertView, ViewGroup parent) {  
            TextView textView = getGenericView();  
            textView.setText(getChild(groupPosition, childPosition).toString());  
            return textView;  
        }
//  
        public Object getGroup(int groupPosition) {  
            return groups[groupPosition];  
        }  
        
        public int getGroupCount() {  
            return groups.length;  
        }  
  
        public long getGroupId(int groupPosition) {  
            return groupPosition;  
        }  
  
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,  
                ViewGroup parent) {  
            TextView textView = getGenericView();  
            textView.setText(getGroup(groupPosition).toString());  
            return textView;  
        }  
  
        public boolean isChildSelectable(int groupPosition, int childPosition) {  
            return true;  
        }  
  
        public boolean hasStableIds() {  
            return true;  
        }  
  
    }  

//    public void onCreate(Bundle bundle){
//        super.onCreate(bundle);
//        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
//        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String,String>>>();
//        for(int i =0; i<20; i++){
//            Map<String, String> curGroupMap = new HashMap<String, String>();
//            groupData.add(curGroupMap);
//            curGroupMap.put("NAME", "Group" + i);
//            curGroupMap.put("IS_EVEN", (i%2 == 0)?"This group is even": "This group is odd");
//            
//            List<Map<String, String>> children = new ArrayList<Map<String,String>>();
//            for(int j=0; j<15; j++){
//                Map<String, String> curChildMap = new HashMap<String, String>();
//                children.add(curChildMap);
//                curChildMap.put("NAME", "Child" + j);
//                curChildMap.put("IS_EVEN", (j%2 == 0)? "This child is even": "This child is odd");
//            }
//            childData.add(children);
//           
//            // set up our adapter
//            SimpleExpandableListAdapter mAdapter = new SimpleExpandableListAdapter(
//                    this,
//                    groupData,
//                    R.layout.simple_expandable_list_item_1,
//                    new String[] {"NAME", "IS_EVEN"},
//                    new int[] {R.id.text1, R.id.text2},
//                    childData,
//                    R.layout.simple_expandable_list_item_2,
//                    new String[] {"NAME", "IS_EVEN"},
//                    new int[] {R.id.text1, R.id.text2}
//                    );
//            setListAdapter(mAdapter);
//            
//        }
//        
//    }
}
