package com.example.giftstore.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giftstore.Data.Database;
import com.example.giftstore.Model.Store;
import com.example.giftstore.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddCategories extends AppCompatActivity {

    EditText etName, unit,group,unitPrice,totalPrice;
    Button addNewCategory;
    Database db;
    int get_id,get_unit,get_group;
    double get_unit_price,totalAll;
    String get_name;
    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categories);
        db = new Database(AddCategories.this);


        etName =findViewById(R.id.input_name);
        unit=findViewById(R.id.input_unit);
        group=findViewById(R.id.input_group);
        unitPrice=findViewById(R.id.input_unitPrice);
        totalPrice=findViewById(R.id.input_totalPrice);
        addNewCategory=findViewById(R.id.btn_addNewCategory);

        Intent intent=getIntent();
        category= intent.getIntExtra(MainActivity.CATEGORY_KEY,-1);
        Log.d("mina3", " "+category);


        //edit fun
        get_id=getID();
        get_name=getName();
        get_unit=getUnit();
        get_group=getGroup();
        get_unit_price=getUnitPrice();


        if(category==-1){
            addNewCategory.setOnClickListener(new View.OnClickListener() {
                String name,dateTime;
                int unitStore,groupStore;
                double total,uPrice,t;
                @Override
                public void onClick(View v) {
                   // clearData();
                   // insertData();
                    name= etName.getText().toString().trim();
                    unitStore=Integer.parseInt(unit.getText().toString().trim());
                    groupStore=Integer.parseInt(group.getText().toString().trim());
                    uPrice=Double.parseDouble(unitPrice.getText().toString().trim());
                    total =(uPrice*unitStore)+((groupStore*12)*uPrice);
                    totalAll+=total;
                    MainActivity.totalAll_tv.setText(String.valueOf(totalAll));

                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
                    dateTime= simpleDateFormat.format(calendar.getTime());
                    Store store = new Store(name, unitStore, groupStore, uPrice, total,totalAll,dateTime);

                    Log.d("mina", "add"+name +"/"+ unitStore+"/"+ groupStore+"/"+ uPrice+"/"+total);
                    db.insert(store);
                    setResult(RESULT_OK);
                    finish();
                }
            });

        }else {
            etName.setText(get_name);
            unit.setText(String.valueOf(get_unit));
            group.setText(String.valueOf(get_group));
            unitPrice.setText(String.valueOf((int) get_unit_price));
            addNewCategory.setText("تعديل");
            addNewCategory.setOnClickListener(new View.OnClickListener() {

                String name1,dateTime1;
                int unitStore1,groupStore1;
                double total1,uPrice1;
                @Override
                public void onClick(View v) {
                    name1= etName.getText().toString().trim();
                    unitStore1=Integer.parseInt(unit.getText().toString().trim());
                    groupStore1=Integer.parseInt(group.getText().toString().trim());
                    uPrice1=Double.parseDouble(unitPrice.getText().toString().trim());
                    total1 =(uPrice1*unitStore1)+((groupStore1*12)*uPrice1);

                    totalAll+=total1;
                    MainActivity.totalAll_tv.setText(String.valueOf(totalAll));

                    Calendar calendar=Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
                    dateTime1= simpleDateFormat.format(calendar.getTime());
                    Store store = new Store(get_id ,name1, unitStore1, groupStore1, uPrice1, total1,totalAll,dateTime1);
                    db.update(store);
                    Toast.makeText(AddCategories.this, "تم التعديل بنجاح", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();


                }
            });
        }

    }

    private int getID(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);
        return id;
    }
    private String getName(){
        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("name");
        return categoryName;
    }
    private int getUnit(){
        Intent intent = getIntent();
        int u = intent.getIntExtra("unit",-1);
        return u;
    }
    private int getGroup(){
        Intent intent = getIntent();
        int g = intent.getIntExtra("group",-1);
        return g;
    }
    private double getUnitPrice(){
        Intent intent = getIntent();
        double up = intent.getDoubleExtra("unitPrice",-1);
        return up;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == MainActivity.EDIT_CATEGORY && resultCode == RESULT_OK) {


        }
    }
}