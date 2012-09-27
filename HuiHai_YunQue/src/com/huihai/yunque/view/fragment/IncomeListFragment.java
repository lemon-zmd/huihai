package com.huihai.yunque.view.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.huihai.yunque.R;
import com.huihai.yunque.persist.dao.ItemsDAO;
import com.huihai.yunque.view.activity.common.quickaction.ActionItem;
import com.huihai.yunque.view.activity.common.quickaction.QuickAction;
import com.huihai.yunque.view.fragment.adapter.ViewPagerFragmentAdapter.Types;

/**
 * the income list fragment. it should create different fragment according to
 * different input arguments.
 * 
 * @author lemon
 * 
 */
public class IncomeListFragment extends ListFragment {

    private static final String BUNDLE_TYPE = "bundle_type";
    private static final int ID_ADD = 1;
    private static final int ID_ACCEPT = 2;
    private static final int ID_UPLOAD = 3;

    /**
     * Create a new instance of CountingFragment, providing "num" as an
     * argument.
     */
    public static IncomeListFragment newInstance(Types type) {

        IncomeListFragment f = new IncomeListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(BUNDLE_TYPE, type.toString());
        f.setArguments(args);

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * The Fragment's UI is just a simple text view showing its instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String sArgs = getArguments() != null ? getArguments().getString(BUNDLE_TYPE) : "ALL";
        Types type = Types.valueOf(sArgs);

        View v = inflater.inflate(R.layout.list_all_view, container, false);
        switch (type) {

        case All:
            fillDataInAll(v.getContext());
            break;

        case ByType:
            fillDataByType();
            break;

        case ByDate:
            fillDataByDate();
            break;

        default:
            fillDataInAll(v.getContext());
            break;
        }
        // fill data to the list because it it a list fragment so we can use the
        // api.
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        ActionItem addItem = new ActionItem(ID_ADD, "Add", getResources().getDrawable(R.drawable.ic_add));
        ActionItem acceptItem = new ActionItem(ID_ACCEPT, "Accept", getResources().getDrawable(R.drawable.ic_accept));
        ActionItem uploadItem = new ActionItem(ID_UPLOAD, "Upload", getResources().getDrawable(R.drawable.ic_up));

        // use setSticky(true) to disable QuickAction dialog being dismissed
        // after an item is clicked
        uploadItem.setSticky(true);

        final QuickAction mQuickAction = new QuickAction(this.getActivity());

        mQuickAction.addActionItem(addItem);
        mQuickAction.addActionItem(acceptItem);
        mQuickAction.addActionItem(uploadItem);

        final Activity activity = this.getActivity();
        // setup the action item click listener
        mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            // @Override
            public void onItemClick(QuickAction quickAction, int pos, int actionId) {
                ActionItem actionItem = quickAction.getActionItem(pos);

                if (actionId == ID_ADD) {
                    Toast.makeText(activity.getApplicationContext(), "Add item selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity.getApplicationContext(), actionItem.getTitle() + " selected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
            // @Override
            public void onDismiss() {
                Toast.makeText(activity.getApplicationContext(), "Ups..dismissed", Toast.LENGTH_SHORT).show();
            }
        });

        mQuickAction.show(v);
    }

    /**
     * we can fill data here using the listAdapter.
     * 
     * @param ctx
     *            the {@link Context} of the inflated {@link View}.
     */
    private void fillDataInAll(Context ctx) {
        Cursor listCursor = ItemsDAO.getInstance().findAllItems();
        SimpleCursorAdapter items = new SimpleCursorAdapter(ctx, R.layout.common_list_item, listCursor, new String[] {
                "item_type", "owner", "date", "_id" }, new int[] { R.id.common_item_1, R.id.common_item_2,
                R.id.happen_time, R.id.item_id_hidden });
        setListAdapter(items);
    }

    private void fillDataByType() {
//        Cursor listCursor = ItemsDAO.getInstance().findAllItems();
//        SimpleCursorAdapter items = new SimpleCursorAdapter(ctx, R.layout.common_list_item, listCursor, new String[] {
//                "item_type", "owner", "date", "_id" }, new int[] { R.id.common_item_1, R.id.common_item_2,
//                R.id.happen_time, R.id.item_id_hidden });
//        setListAdapter(items);
        
        
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "Music： " + i); // 文字
            if (i % 2 > 0) {
                map.put("ItemImage", R.drawable.common_list_icon); // 图片
            }
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), listItems, R.layout.type_list_item, new String[] {
                "ItemTitle", "ItemImage" }, new int[] { R.id.common_item_1, R.id.icon });

        setListAdapter(adapter);
    }

    private void fillDataByDate() {
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.date_list_item, R.id.common_item_1,
                new String[] { "之前(25)", "前天(20)", "昨天(120)", "今天(10)" }));
    }
}
