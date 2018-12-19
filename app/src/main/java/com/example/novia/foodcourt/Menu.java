package com.example.novia.foodcourt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener {

    private Button bTambah;
    private Button bLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bTambah = (Button) findViewById(R.id.button_tambah);
        bTambah.setOnClickListener(this);
        bLihat = (Button) findViewById(R.id.button_view);
        bLihat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_tambah :
                Intent i = new Intent(this, Create_Data.class); startActivity(i);
                break;
            case R.id.button_view :
                Intent i2 = new Intent(this, ViewData.class);
                startActivity(i2);
                break;
        }
    }
}
