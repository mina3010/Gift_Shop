package com.example.giftstore.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giftstore.Adapter.AdapterStore;
import com.example.giftstore.Data.Database;
import com.example.giftstore.Model.Store;
import com.example.giftstore.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_CATEGORY=1;
    public static final int EDIT_CATEGORY=1;
    public static final String CATEGORY_KEY= "category_key";
    Database db;
    AdapterStore adapter;
    RecyclerView rv;
    List<Store>storeList=new ArrayList<>();
    TextView add;
    public static TextView totalAll_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        add=findViewById(R.id.addCategory);
        totalAll_tv=findViewById(R.id.total_all_show);
        rv=findViewById(R.id.rv_store);

        db = new Database(MainActivity.this);
        storeList = db.getAllStore();
        adapter = new AdapterStore(this ,storeList);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this, AddCategories.class);
                startActivityForResult(i,ADD_CATEGORY);
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        storeList = db.getAllStore();
        adapter = new AdapterStore(this ,storeList);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if ( requestCode == ADD_CATEGORY && resultCode == RESULT_OK) {
                storeList = db.getAllStore();
                adapter = new AdapterStore(this ,storeList);
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        }

}
