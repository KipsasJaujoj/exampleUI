package com.example.sasaja.sasaja;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class PrekiuPaieska extends AppCompatActivity {

    private ConstraintLayout clKonteineris;
    private EditText etPaieska;
    public ArrayList<Preke> sarasas = new ArrayList<Preke>();
    public CustomAdapter adapter1;
    public ListView lvProduktai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prekiu_paieska);

        initialize();

        etPaieska.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                adapter1.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        sarasas.add(new Preke("Batonas", 1.99));
        sarasas.add(new Preke("Duona", 1.49));
        sarasas.add(new Preke("Pienas", 1.29));
        sarasas.add(new Preke("Jogurtas", 0.59));



        adapter1 = new CustomAdapter(PrekiuPaieska.this, sarasas);
        lvProduktai.setAdapter(adapter1);
    }

    private void initialize() {
        etPaieska = (EditText) findViewById(R.id.etPaieska);
        lvProduktai = (ListView) findViewById(R.id.listView3);
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

    public void baigtiSuReiksme(String p, double k) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("pavadinimas", p);
        resultIntent.putExtra("kaina", k);
        setResult(prekiu_pridejimas.RESULT_OK, resultIntent);
        finish();
    }

    public void baigti(View view) {
        finish();
    }
}
