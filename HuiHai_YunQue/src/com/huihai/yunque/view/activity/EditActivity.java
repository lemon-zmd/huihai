package com.huihai.yunque.view.activity;


import android.app.ActivityGroup;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.huihai.yunque.R;
import com.huihai.yunque.persist.dao.ItemsDAO;

public class EditActivity extends ActivityGroup{
    
    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        
         this.setContentView(R.layout.edit_item);
        
         Button submitBtn = (Button) findViewById(R.id.save);
        
         submitBtn.setOnClickListener(insertListener());
    }
    
    private View.OnClickListener insertListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemsDAO nd = ItemsDAO.getInstance();
                nd.insert(createCV());
                Toast.makeText(EditActivity.this, "新条目已添加", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private ContentValues createCV(){
//        final Spinner item_type = (Spinner) findViewById(R.id.item_type);
        
        final EditText plate_no = (EditText) findViewById(R.id.plate_no);
        
        final EditText unit_price = (EditText) findViewById(R.id.price);
        
        final EditText send_weight = (EditText) findViewById(R.id.sendWeight);
        
        final EditText receive_weight = (EditText) findViewById(R.id.receiveWeight);
        
        final EditText payment = (EditText) findViewById(R.id.payment);
        
        final EditText owner = (EditText) findViewById(R.id.owner);
        
//        final EditText date = (EditText) findViewById(R.id.create_date);
        
        ContentValues initialValues = new ContentValues();
        initialValues.put("plate_no", plate_no.getText().toString());
        initialValues.put("unit_price", unit_price.getText().toString());
        initialValues.put("send_weight", send_weight.getText().toString());
        initialValues.put("receive_weight", receive_weight.getText().toString());
        initialValues.put("payment", payment.getText().toString());
        initialValues.put("owner", owner.getText().toString());
//        initialValues.put("date", date.getText().toString());
        return initialValues;
    }
}
