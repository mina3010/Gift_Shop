package com.example.giftstore.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.giftstore.Model.Store;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "GIFT_STORE";
    private static final int DB_VERSION = 4;
    private static final String TABLE_STORE = "Store";

    private static final String _STORE_ID = "_id";
    private static final String NAME = "name";
    private static final String UNIT = "unit";
    private static final String GROUP = "grou";
    private static final String UNIT_PRICE = "unit_price";
    private static final String TOTAL_PRICE = "total_price";
    private static final String TIMES_TAMP = "timesTamp";
    private static final String TOTAL_ALL = "total_all";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String store_items = "CREATE TABLE " + TABLE_STORE +
                " (" + _STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT , " +
                UNIT + " INTEGER , " +
                GROUP + " INTEGER , " +
                UNIT_PRICE + " DOUBLE , " +
                TOTAL_PRICE + " DOUBLE , " +
                TIMES_TAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                TOTAL_ALL + " DOUBLE);";
        Log.d("mina", "d");

        db.execSQL(store_items);
        Log.d("mina", "d1");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        onCreate(db);
    }

    SQLiteDatabase db = this.getWritableDatabase();
    //insert to categories
    public boolean insert(Store store) throws SQLiteException {
        ContentValues content = new ContentValues();
        content.put(Database.NAME, store.getCategoryName());
        content.put(Database.UNIT, store.getUnit());
        content.put(Database.GROUP, store.getGroup());
        content.put(Database.UNIT_PRICE, store.getUnitPrice());
        content.put(Database.TOTAL_PRICE, store.getTotalPrice());
        content.put(Database.TOTAL_ALL, store.getTotalAll());
        content.put(Database.TIMES_TAMP, store.getTimesTamp());
        long result = db.insert(TABLE_STORE, null, content);
        Log.d("mina", "d1");

        db.close();
        return result != -1;
    }

    public boolean update(Store store) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Database.NAME, store.getCategoryName());
        content.put(Database.UNIT, store.getUnit());
        content.put(Database.GROUP, store.getGroup());
        content.put(Database.UNIT_PRICE, store.getUnitPrice());
        content.put(Database.TOTAL_PRICE, store.getTotalPrice());
        content.put(Database.TOTAL_ALL, store.getTotalAll());
        content.put(Database.TIMES_TAMP, store.getTimesTamp());
        String args[] = {String.valueOf(store.getId())};
        int result = db.update(TABLE_STORE, content, "_id = ?",args);
        return result > 0;
    }

    //delete
    public boolean delete(Store store) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        String args[] ={String.valueOf(store.getId())};
        int result = db.delete(TABLE_STORE, "_id=?", args);
        return result > 0;
    }


    public ArrayList<Store> getAllStore() {
        ArrayList<Store> stores = new ArrayList<>();
        if (db != null) {
            Cursor cursor = db.rawQuery(" select * from " + TABLE_STORE, null);
            while (cursor.moveToNext()) {

                int id = cursor.getInt(cursor.getColumnIndex(Database._STORE_ID));
                String nameCategory = cursor.getString(cursor.getColumnIndex(Database.NAME));
                int unit = cursor.getInt(cursor.getColumnIndex(Database.UNIT));
                int group = cursor.getInt(cursor.getColumnIndex(Database.GROUP));
                double unit_price = cursor.getDouble(cursor.getColumnIndex(Database.UNIT_PRICE));
                double total_price = cursor.getDouble(cursor.getColumnIndex(Database.TOTAL_PRICE));
                double total_all = cursor.getDouble(cursor.getColumnIndex(Database.TOTAL_ALL));
                String timestamp = cursor.getString(cursor.getColumnIndex(Database.TIMES_TAMP));
                stores.add(new Store(id, nameCategory, unit, group, unit_price, total_price,total_all,timestamp));

                Log.d("mina", "d2");
            }

            cursor.close();

            return stores;
        } else {
            Toast.makeText(context, "Data is Null;)", Toast.LENGTH_SHORT).show();
            return null;

        }
    }

}
