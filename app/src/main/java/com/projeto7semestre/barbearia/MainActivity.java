package com.projeto7semestre.barbearia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void abrirServicos(View view) {
        Intent it = new Intent(this, ServicosActivity.class);
        startActivity(it);
    }

    public void abrirProfissional(View view) {
        Intent it = new Intent(this, ProfissionaisActivity.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {        // para previnir saídas inesperadas irritantes
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 segundos para sair
            backPressedTime = t;
            Toast.makeText(this, "Pressione 2x para sair", Toast.LENGTH_SHORT).show();
        } else {    // se pressionado novamente encerrar app
            // clean up
            super.onBackPressed();       // bye
        }

    }
}
