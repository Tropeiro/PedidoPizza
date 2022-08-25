package com.example.pedidopizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class Pagamento extends AppCompatActivity {

    private Button btnVoltar;
    private double total;
    private TextView txtValor;
    private RadioGroup rdGrupo;
    private RadioButton rbPix, rbDinheiro, rbCartao;
    private String saida="", txtpizza;
    private Switch swEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

//declarações
        btnVoltar = findViewById(R.id.btnVoltar);

        txtValor = findViewById(R.id.txtValor);

        rdGrupo = findViewById(R.id.rdGrupo);

        rbPix = findViewById(R.id.rbPix);
        rbCartao = findViewById(R.id.rbCartao);
        rbDinheiro = findViewById(R.id.rbDinheiro);

        swEntrega = findViewById(R.id.swEntrega);
//declarações

        Bundle params = getIntent().getExtras();
        if(params != null){
            total = params.getDouble("total");
            txtValor.setText(String.format("Total a pagar: R$%5.2f", total));
            txtpizza = params.getString("txtpizza");
        }
        saida += txtpizza;
        rdGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(rbDinheiro.isChecked())
                    saida += "Forma de pagamento: Dinheiro.\n";
                else if(rbPix.isChecked())
                    saida += "Forma de pagamento: Pix.\n";
                else saida += "Forma de pagamento: Cartão.\n";

                AlertDialog.Builder paga = new AlertDialog.Builder(Pagamento.this);
                paga.setIcon(R.mipmap.minipizza);
                paga.setTitle("Recibo");
                String textao = String.format("%s\nTotal a pagar: R$%5.2f", saida, total);
                paga.setMessage(textao);
                paga.setNeutralButton("Ok", null);
                paga.show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();


            }
        });
    }
}