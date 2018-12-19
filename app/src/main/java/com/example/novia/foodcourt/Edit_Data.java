package com.example.novia.foodcourt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Edit_Data extends Activity implements View.OnClickListener {

    private DBDataSource dataSource; private long id;
    private String harga;
    private String menu;
    private String nama;
    private EditText edNama;
    private EditText edHarga;
    private EditText edMenu;
    private TextView txId;
    private Button btnSave;
    private Button btnCancel;
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);

            //inisialisasi variabel
        edNama = (EditText) findViewById(R.id.editText_nama);
        edHarga = (EditText) findViewById(R.id.editText_harga);
        edMenu = (EditText) findViewById(R.id.editText_menu);
        txId = (TextView) findViewById(R.id.text_id_food);
            //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();
            // ambil data food dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        harga = bun.getString("harga");
        menu = bun.getString("menu");
        nama = bun.getString("nama");
            //masukkan data-data food tersebut ke field editor
        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edHarga.setText(harga); edMenu.setText(menu);
            //set listener pada tombol
        btnSave = (Button) findViewById(R.id.button_save_update);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update food)
            case R.id.button_save_update :
                food = new Food();
                food.setHarga_food(edHarga.getText().toString());
                food.setNama_food(edNama.getText().toString());
                food.setMenu_food(edMenu.getText().toString());
                food.setId(id);
                dataSource.updateFood(food);
                Intent i = new Intent(this, ViewData.class); startActivity(i);
                Edit_Data.this.finish(); dataSource.close(); break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_update : finish(); dataSource.close();
                break;
        }
    }
}
