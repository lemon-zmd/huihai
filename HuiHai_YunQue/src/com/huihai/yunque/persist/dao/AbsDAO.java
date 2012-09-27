package com.huihai.yunque.persist.dao;

import com.huihai.yunque.persist.common.PersistUtil;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * the abstract DAO.
 * @author lemon
 *
 */
public abstract class AbsDAO {
    private final String TB_NAME;
    protected final SQLiteDatabase sdb;
    private final String[] columns;

    protected AbsDAO(String tb_name, String[] cols){
        sdb = PersistUtil.getExternalDBHandler();
        columns = cols;
        TB_NAME = tb_name;
    }

    protected SQLiteDatabase getDBHandler(){
        return sdb;
    }

    public void insert(ContentValues cv){
        try{
            sdb.insert(TB_NAME, null, cv);
        }finally{
        }
    }
    
    public Cursor findAllItems(){
        return sdb.query(TB_NAME, columns, null, null, null, null, null);
    }
    
    public Cursor findById(long id) {
        return sdb.query(TB_NAME, columns, "_id=", new String[] {id+""}, null, null, null);
    }
    
    public void deleteById(long... ids){
        sdb.delete(TB_NAME, "_id in " + constructClause(ids), null);
    }
    
    protected String constructClause(long ... ids){
        StringBuilder sb = new StringBuilder("(");
        for (long i : ids){
            sb.append(i + ",");
        }
        
        return sb.append("null);").toString();
    }
}
