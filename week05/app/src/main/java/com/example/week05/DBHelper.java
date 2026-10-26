package com.example.week05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "employee_db";
    private static final String TB_NAME = "employee";

    private static final String Col_ID = "id";
    private static final String Col_Name = "name";
    private static final String Col_Email = "email";
    private static final String Col_Address = "address";
    private static final String Col_Phone = "phone";

    private SQLiteDatabase mySQLite;

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TB_NAME + " ("
                    + Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Col_Name + " TEXT NOT NULL, "
                    + Col_Email + " TEXT NOT NULL, "
                    + Col_Address + " TEXT, "
                    + Col_Phone + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TB_NAME
                    + " ADD COLUMN " + Col_Address + " TEXT");

            db.execSQL("ALTER TABLE " + TB_NAME
                    + " ADD COLUMN " + Col_Phone + " TEXT");
        }
    }

    public void addEmployee(EmployeeModel employeeModel) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_Name, employeeModel.getName());
        contentValues.put(Col_Email, employeeModel.getEmail());
        contentValues.put(Col_Address, employeeModel.getAddress());
        contentValues.put(Col_Phone, employeeModel.getPhone());

        mySQLite = this.getWritableDatabase();
        mySQLite.insert(TB_NAME, null, contentValues);
    }

    public void updateEmployee(EmployeeModel employeeModel) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_Name, employeeModel.getName());
        contentValues.put(Col_Email, employeeModel.getEmail());
        contentValues.put(Col_Address, employeeModel.getAddress());
        contentValues.put(Col_Phone, employeeModel.getPhone());

        mySQLite = this.getWritableDatabase();

        mySQLite.update(
                TB_NAME,
                contentValues,
                Col_ID + " = ?",
                new String[]{String.valueOf(employeeModel.getId())}
        );
    }

    public void deleteEmployee(int id) {
        mySQLite = this.getWritableDatabase();

        mySQLite.delete(
                TB_NAME,
                Col_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    public List<EmployeeModel> getEmployeeList() {
        String viewData = "SELECT id, name, email, address, phone FROM "
                + TB_NAME;

        mySQLite = this.getReadableDatabase();

        List<EmployeeModel> storeEmployee = new ArrayList<>();

        Cursor cursor = mySQLite.rawQuery(viewData, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String address = cursor.getString(3);
                String phone = cursor.getString(4);

                storeEmployee.add(
                        new EmployeeModel(id, name, email, address, phone)
                );

            } while (cursor.moveToNext());
        }

        cursor.close();

        return storeEmployee;
    }
}