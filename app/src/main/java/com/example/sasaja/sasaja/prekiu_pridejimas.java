package com.example.sasaja.sasaja;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class prekiu_pridejimas extends AppCompatActivity {
    ListView lv;
    Context context;
    TextView tv;
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
    private ArrayList<Preke> sarasas = new ArrayList<Preke>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prekiu_pridejimas);
        context = this;

        lv=(ListView) findViewById(R.id.listView);

        BaseAdapter manoAdapter = new CustomAdapter(this, sarasas);
        lv.setAdapter(manoAdapter);
        tv=(TextView) findViewById(R.id.textView8);
        double suma = 0;
        for (int i = 0; i < sarasas.size(); i++) {
            suma = sarasas.get(i).kaina + suma;
        }
        tv.setText(String.valueOf(suma));
    }

    public void ieskoti(View view) {
        Intent intent = new Intent(this, PrekiuPaieska.class);
        startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
    }

    public void moketi(View view) {
        Intent intent = new Intent(this, Mokejimas.class);
        startActivity(intent);
    }

     public void padeti(View view) {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage("Laukite kol prieis operatorius")
                 .setCancelable(false)
                 .setTitle("Pranešimas išsiųstas")
                 .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                         //do things
                     }
                 });
         AlertDialog alert = builder.create();
         alert.show();
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                String grazintasPavadinimas = data.getStringExtra("pavadinimas");
                double grazintaKaina = data.getDoubleExtra("kaina", 0);

                sarasas.add(new Preke(grazintasPavadinimas, grazintaKaina));
            }
        }
    }
}
