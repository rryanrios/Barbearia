package com.projeto7semestre.barbearia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declarando varialvel para determinar tempo
    private long backPressedTime = 0;
    ImageButton btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNav = findViewById(R.id.btnNav);

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://beautyguys.000webhostapp.com";
                Intent site = new Intent(Intent.ACTION_VIEW);
                site.setData(Uri.parse(url));
                startActivity(site);
            }
        });

    }

    public void abrirServicos(View view) {
        Intent it = new Intent(this, ServicosActivity.class);
        startActivity(it);
    }

    public void abrirProfissional(View view) {
        Intent it = new Intent(this, PerfisProfissionaisActivity.class);
        startActivity(it);
    }

    @Override
    //Impede saidas inesperadas
    public void onBackPressed() {
        long t = System.currentTimeMillis();
        // 2 segundos para sair
        if (t - backPressedTime > 2000) {
            backPressedTime = t;
            Toast.makeText(this, "Pressione 2x para sair", Toast.LENGTH_SHORT).show();
            // se pressionado novamente encerrar app
        } else {
            super.onBackPressed();
        }
    }

    //Cria menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    //Da funcao ao objeto menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_agendamentos:
                Intent it = new Intent(this, HistoricoActivity.class);
                startActivity(it);
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
