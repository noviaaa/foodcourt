package com.example.novia.foodcourt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Novia on 11/3/2017.
 */

class DBDataSource {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;
    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;
    //ambil semua nama kolom
    private String[] allColumns = {DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME, DBHelper.COLUMN_MENU, DBHelper.COLUMN_HARGA};
    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    //method untuk create/insert food ke database
         public Food createFood(String nama, String menu, String harga) {
                // membuat sebuah ContentValues, yang berfungsi
                // untuk memasangkan data dengan nama-nama
                // kolom pada database
        ContentValues values = new ContentValues();
             values.put(DBHelper.COLUMN_NAME, nama);
             values.put(DBHelper.COLUMN_MENU, menu);
             values.put(DBHelper.COLUMN_HARGA, harga);
                // mengeksekusi perintah SQL insert data
                // yang akan mengembalikan sebuah insert ID
             long insertId = database.insert(DBHelper.TABLE_NAME, null, values);
                // setelah data dimasukkan, memanggil
                // perintah SQL Select menggunakan Cursor untuk
                // melihat apakah data tadi benar2 sudah masuk
                // dengan menyesuaikan ID = insertID
             Cursor cursor = database.query(DBHelper.TABLE_NAME,
                     allColumns, DBHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
                // pindah ke data paling pertama
             cursor.moveToFirst();
                // mengubah objek pada kursor pertama tadi
                // ke dalam objek food
             Food newFood = cursorToFood(cursor);
             cursor.close();
             return newFood;
         }

        private Food cursorToFood(Cursor cursor) {
            /* buat objek food baru  */
            Food food = new Food();
        // debug LOGCAT
        //Log.v("info", "The getLONG "+cursor.getLong(0));
        //Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));
        // Set atribut pada objek food dengan
        // data kursor yang diambil dari database
            food.setId(cursor.getLong(0));
            food.setNama_food(cursor.getString(1));
            food.setMenu_food(cursor.getString(2));
            food.setHarga_food(cursor.getString(3));
        //kembalikan sebagai objek foodg
                return food;
            }

    //mengambil semua data food
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> daftarFood = new ArrayList<Food>();
// select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, null, null, null, null, null);
// pindah ke data paling pertama
        cursor.moveToFirst();
// jika masih ada data, masukkan data food ke
// daftar food
        while (!cursor.isAfterLast()) {
            Food food = cursorToFood(cursor);
            daftarFood.add(food);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarFood;
    }

    //ambil satu food sesuai id
    public Food getFood(long id) {
        Food food = new Food(); //inisialisasi food
            //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id =" + id, null, null, null, null);
            //ambil data yang pertama
        cursor.moveToFirst();
            //masukkan data cursor ke objek food
        food = cursorToFood(cursor);
        cursor.close();
        return food;
    }

    //update food yang diedit
    public void updateFood(Food b) {
        //ambil id food
        String strFilter = "_id=" + b.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAME, b.getNama_food());
        args.put(DBHelper.COLUMN_MENU, b.getMenu_food());
        args.put(DBHelper.COLUMN_HARGA, b.getHarga_food());
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    // delete food sesuai ID
    public void deleteFood(long id) { String strFilter = "_id=" + id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
