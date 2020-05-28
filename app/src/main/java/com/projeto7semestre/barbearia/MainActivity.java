package com.projeto7semestre.barbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void abrirAgenda(View view){
        Intent it = new Intent(this, AgendaActivity.class);
        startActivity(it);
    }
    public void abrirServicos(View view){
        Intent it = new Intent(this, ServicosActivity.class);
        startActivity(it);
    }
}
