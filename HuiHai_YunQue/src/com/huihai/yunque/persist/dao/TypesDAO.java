package com.huihai.yunque.persist.dao;

import com.huihai.yunque.persist.common.PersistUtil;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TypesDAO extends AbsDAO{

    private static final String TB_NAME = "types";
    private final SQLiteDatabase sdb;
    private static final String[] columns = new String[] {"type_id", "type_name", "type_desc"};
    private static TypesDAO instance = new TypesDAO();
    
    private TypesDAO(){
        super(TB_NAME, columns);
        sdb = PersistUtil.getExternalDBHandler();
    }
    
    public static TypesDAO getInstance(){
        return instance;
    }

    public Cursor findById(long id) {
        return sdb.query(TB_NAME, columns, "type_id=", new String[] {id+""}, null, null, null);
    }
}
