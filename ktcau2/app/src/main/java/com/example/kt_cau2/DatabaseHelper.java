package com.example.kt_cau2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EMPLOYEES = "employees";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_AGE + " INTEGER)";
        db.execSQL(createTableQuery);

        // Insert sample data
        insertEmployee(db, "NV001", "Nguyễn Đại Nhân", 30);
        insertEmployee(db, "NV002", "Trần Đại Nghĩa", 28);
        insertEmployee(db, "NV003", "Hoàng Đại Lễ", 35);
        insertEmployee(db, "NV004", "Phạm Đại Trí", 25);
        insertEmployee(db, "NV005", "Trương Đại Tín", 32);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public void insertEmployee(SQLiteDatabase db, String id, String name, int age) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        db.insert(TABLE_EMPLOYEES, null, values);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPLOYEES, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                int age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE));
                employees.add(new Employee(id, name, age));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return employees;
    }

    public void deleteEmployee(String employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEES, COLUMN_ID + " = ?", new String[]{employeeId});
        db.close();
    }
}


