package com.example.novia.foodcourt;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Novia on 11/3/2017.
 */

public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {

    //inisialisasi kontroller
    private DBDataSource dataSource;
//inisialisasi arraylist
    private ArrayList<Food> values;
    private Button editButton;
    private Button delButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata);
        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();
        // ambil semua data food
        values = dataSource.getAllFood();
        // masukkan data food ke array adapter
        ArrayAdapter<Food> adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, values);
        // set adapter pada list
        setListAdapter(adapter);
        // mengambil listview untuk diseT onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {
        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Food b = (Food) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data);
        //apabila tombol edit diklik
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // TODO Auto-generated method stub
                switchToEdit(b.getId());
                dialog.dismiss();
                }
            }
        );
        //apabila tombol delete di klik
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete food
                dataSource.deleteFood(b.getId());
                dialog.dismiss();
                finish();
                startActivity(getIntent());
                }
            }
        );
        return true;
    }

    public void switchToEdit(long id)
    {
        Food b = dataSource.getFood(id);
        Intent i = new Intent(this, Edit_Data.class); Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_food());
        bun.putString("menu", b.getMenu_food());
        bun.putString("harga", b.getHarga_food());
        i.putExtras(bun);
        finale(); startActivity(i);
    }
    public void finale()
    {
        ViewData.this.finish();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
