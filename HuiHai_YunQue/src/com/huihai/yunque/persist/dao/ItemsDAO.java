package com.huihai.yunque.persist.dao;

import android.database.Cursor;

public class ItemsDAO extends AbsDAO{
    private static final String TB_NAME = "items_v_0_1";
    private static final String[] columns = new String[]{"_id",
        "item_type", "owner", "plate_no", "unit_price", "send_weight", "receive_weight", "payment", "date"};
    private static ItemsDAO instance = new ItemsDAO();
    
    private ItemsDAO(){
        super(TB_NAME, columns);
    }
    
    public static ItemsDAO getInstance(){
        return instance;
    }

    public Cursor getByType(long type_id) {
        return sdb.query(TB_NAME, columns, "type_id=", new String[]{type_id+""}, null, null, null);
    }
}
