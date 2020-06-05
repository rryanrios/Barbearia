package com.projeto7semestre.barbearia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class AgendaActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    Button btnSelec, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        btnSelec = findViewById(R.id.btnSelec);
        btnVoltar = findViewById(R.id.btnVoltar);

        //INICIO DO CALENDARIO
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);

        //Adicionar seta voltar na tela
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Boquear dias anteriores ao atual
        calendarView.setMinDate(System.currentTimeMillis() - 1000);

        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AgendaActivity.this, AgendamentoActivity.class);
                it.putExtra("key", myDate.getText().toString());
                if (myDate.getText().toString().equals("Dias disponiveis")) {
                    Toast.makeText(AgendaActivity.this, "Selecione uma data", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(it);
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AgendaActivity.this, ServicosActivity.class);
                startActivity(it);
                finish();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                myDate.setText(date);
            }
        });
    }

    //Funcao botao voltar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    } // FIM DO CALENDARIO

}