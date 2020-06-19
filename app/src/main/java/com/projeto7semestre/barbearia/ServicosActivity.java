package com.projeto7semestre.barbearia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.projeto7semestre.barbearia.funcionario.Funcionario;

public class ServicosActivity extends AppCompatActivity {

    ImageButton btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        //Adicionar seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Fim

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados("Corte Simples","20,00","30 minutos");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados("Corte + Lavagem","40,00","1 hora");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados("Barba","30,00","30 minutos");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados("Corte + Barba","60,00","1 hora");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDados("Corte Personalizado","60,00","1 hora");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Enviar informações para classe funcionario
    public void enviarDados(String servico, String preco, String tempoServico) {
        Funcionario servicos = new Funcionario();
        servicos.setServicoEscolhido(servico);
        servicos.setPreco(preco);
        servicos.setTempoServico(tempoServico);

        Intent it = new Intent(this, ProfissionaisActivity.class);
        it.putExtra("servicos", servicos);
        startActivity(it);
        finish();
    }
}