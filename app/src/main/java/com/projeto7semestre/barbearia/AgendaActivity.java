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

import com.projeto7semestre.barbearia.funcionario.Funcionario;

public class AgendaActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;
    Button btnSelec;
    String date;

    Funcionario funcionario = new Funcionario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        btnSelec = findViewById(R.id.btnSelec);

        //Adicionar seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Fim

        //Criar calendario
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);

        //Bloqueia dias anteriores ao atual
        calendarView.setMinDate(System.currentTimeMillis() - 1000);

        //Calendario
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                date = i2 + "/" + (i1 + 1) + "/" + i;
                myDate.setText(date);
            }
        });

        //Receber dados da tela ServicosActivity
        Bundle bundle = getIntent().getExtras();
        final Funcionario agenda = (Funcionario) bundle.getSerializable("agenda");

        //Envia data selecionada para tela ProfissionaisActivity
        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDate.getText().toString().equals("Dias disponiveis")) {
                    Toast.makeText(AgendaActivity.this, "Selecione uma data", Toast.LENGTH_LONG).show();
                } else {
                    agenda.setDia(date);
                    Intent it = new Intent(AgendaActivity.this, ProfissionaisActivity.class);
                    it.putExtra("servicos", agenda);
                    startActivity(it);
                    finish();
                }
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
    }
}