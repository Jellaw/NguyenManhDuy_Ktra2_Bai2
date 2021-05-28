package com.example.nguyenmanhduy_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteController extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="lichthi.db";
    private static final int DATABASE_VERSION = 1;
    public SQLiteController(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE lichthi("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenmonhoc TEXT, "+
                "ngaythi TEXT, "+
                "giothi TEXT," +
                "kieuthi TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+"orders");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void addlichthi(Lichthi lichthi){
        String query = "INSERT INTO lichthi(tenmonhoc,ngaythi,giothi,kieuthi) VALUES (?,?,?,?)";
        String[] args = {lichthi.getTenmonhoc(), lichthi.getNgaythi(), lichthi.getGiothi(), lichthi.getKieuthi()};
        SQLiteDatabase statement = getWritableDatabase();
        statement.execSQL(query,args);
    }

    public int updateLichthi(Lichthi lichthi){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenmonhoc", lichthi.getTenmonhoc());
        values.put("ngaythi", lichthi.getNgaythi());
        values.put("giothi", lichthi.getGiothi());
        values.put("kieuthi", lichthi.getKieuthi());
        String whereClause = "tenmonhoc= ?";
        String[] whereArgs = {String.valueOf(lichthi.getTenmonhoc())};
        return database.update("lichthi", values, whereClause, whereArgs);
    }


    public ArrayList<Lichthi> getAllLichthi(){
        ArrayList<Lichthi> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor resultSet = statement.query("lichthi",null,null,null,null,null,null);

        while ((resultSet!=null) && (resultSet.moveToNext())){
            int id = resultSet.getInt(0);
            String tenmonhoc = resultSet.getString(1);
            String ngaythi = resultSet.getString(2);
            String giothi = resultSet.getString(3);
            String kieuthi = resultSet.getString(4);
            list.add(new Lichthi(id,tenmonhoc,ngaythi,giothi,kieuthi));
        }
        resultSet.close();
        return list;
    }


    public Lichthi searchLichthiByName(String name){
        String whereClause = "tenmonhoc=?";
        String[] whereArgs = {String.valueOf(name)};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor resultSet = sqLiteDatabase.query("lichthi", null, whereClause, whereArgs, null,null, null);
        if(resultSet.moveToNext()){
            int id = resultSet.getInt(0);
            String ngaythi = resultSet.getString(2);
            String giothi = resultSet.getString(3);
            String kieuthi = resultSet.getString(4);
            Lichthi lichthi = new Lichthi(id,name,ngaythi,giothi,kieuthi);
            return lichthi;
        }
        return null;
    }
}
