package com.example.bankconnect;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TRANSACTION_TABLE = "transaction_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_1 = "icon";
    public static final String COLUMN_2 = "label";
    public static final String COLUMN_3 = "price";
    public static final String COLUMN_4 = "date";
    public static final String COLUMN_5 = "num_compte";
    public static final String COLUMN_6 = "num_ref";
    public static final String COLUMN_7 = "solde";


    public DbHelper(@Nullable Context context) {
        super(context, "database_transaction.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String certificationStatement = "CREATE TABLE " + TRANSACTION_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_1 + " TEXT," + COLUMN_2 + " TEXT, " + COLUMN_3 + " TEXT, " + COLUMN_4 + " TEXT, " + COLUMN_5 + " TEXT, " + COLUMN_6 + " TEXT, " + COLUMN_7 + " TEXT )";
        db.execSQL(certificationStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String userStatement = "DROP TABLE IF EXISTS " + TRANSACTION_TABLE ;
        db.execSQL(userStatement);

    }


    public boolean addTransaction(Transaction item){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues cv= new ContentValues();

        cv.put(COLUMN_1,item.getIcon());
        cv.put(COLUMN_2,item.getLabel());
        cv.put(COLUMN_3,item.getPrice());
        cv.put(COLUMN_4,item.getDate());
        cv.put(COLUMN_5,item.getNumCompte());
        cv.put(COLUMN_6,item.getNumRef());
        cv.put(COLUMN_7,item.getSold());


        long rs= db.insert(TRANSACTION_TABLE,null,cv);
        db.close();
        if(rs == -1){
            return  false;
        }else {
            return true;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Transaction> getAll(){

        ArrayList<Transaction> items= new ArrayList<Transaction>();

        String query="SELECT * FROM " + TRANSACTION_TABLE;
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {

                 Transaction item = new Transaction(

                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_1)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_2)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_3)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_4)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_5)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_6)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_7))
                );

                items.add(item);

            } while (cursor.moveToNext());

        }else {
            //failure
        }

        cursor.close();
        db.close();

         return  items;
    }



    public Boolean checkEmailIfExist(String email){

       Boolean result = false;

        String query="SELECT * FROM " + TRANSACTION_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
              if(email.equals(cursor.getString(4))){
                  result = true;
                  break;
              }

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return result;
    }

    public Boolean checkPhoneIfExist(String phone){

        Boolean result = false;

        String query="SELECT * FROM " + TRANSACTION_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {

                System.out.println(cursor.getString(8));

                if(phone.equals(cursor.getString(8))){
                    result = true;
                    break;
                }

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return result;
    }



    public Boolean checkLogin(String email,String password){

        Boolean result = false;

        String query="SELECT * FROM " + TRANSACTION_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {



                if(email.equals(cursor.getString(4)) && password.equals(cursor.getString(9)) ){
                    result = true;
                    break;
                }

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return result;
    }


    @SuppressLint("Range")
    public Transaction findById(Integer id) {
        Transaction item= null;

        String query="SELECT * FROM " + TRANSACTION_TABLE + " WHERE " + COLUMN_ID + "=" + id;
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {

                item = new Transaction(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_1)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_2)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_3)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_4)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_5)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_6)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_7))
                );

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return item;
    }
}
