package com.projeto7semestre.barbearia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto7semestre.barbearia.funcionario.Funcionario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfissionaisActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtData, receberData;
    ImageButton abrirAgenda;
    CheckBox checkBox1, checkBox2, checkBox3;
    LinearLayout selectProfissional, selectProfissional2, selectProfissional3;
    Button btnConfirm;

    int id = 0;
    String hoarioSelecionado;
    Funcionario servicos = new Funcionario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissionais);

        abrirAgenda = findViewById(R.id.abrirAgenda);
        txtData = findViewById(R.id.txtData);
        receberData = findViewById(R.id.receberData);
        btnConfirm = findViewById(R.id.btnConfirm);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        selectProfissional = findViewById(R.id.selectProfissional);
        selectProfissional2 = findViewById(R.id.selectProfissional2);
        selectProfissional3 = findViewById(R.id.selectProfissional3);

        //Adicionar seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Fim

        //Receber dados da tela ServicosActivity
        Bundle bundle = getIntent().getExtras();
        servicos = (Funcionario) bundle.getSerializable("servicos");

        //selectProfissional.setVisibility(View.VISIBLE);

        //Botoes Checkbox
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    servicos.setCheckbox(1);
                    id = 1;
                    selectProfissional.setVisibility(View.VISIBLE);
                    selectProfissional2.setVisibility(View.INVISIBLE);
                    selectProfissional3.setVisibility(View.INVISIBLE);
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                }
            }
        });

        //Botoes Checkbox
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    servicos.setCheckbox(2);
                    id = 2;
                    selectProfissional.setVisibility(View.INVISIBLE);
                    selectProfissional2.setVisibility(View.VISIBLE);
                    selectProfissional3.setVisibility(View.INVISIBLE);
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                }
            }
        });

        //Botoes Checkbox
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    servicos.setCheckbox(3);
                    id = 3;
                    selectProfissional.setVisibility(View.INVISIBLE);
                    selectProfissional2.setVisibility(View.INVISIBLE);
                    selectProfissional3.setVisibility(View.VISIBLE);
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                }
            }
        });

        abrirAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ProfissionaisActivity.this, AgendaActivity.class);
                it.putExtra("agenda", servicos);
                startActivity(it);
            }
        });

        if (servicos.getCheckbox() == 1) {
            checkBox1.setChecked(true);
            if(checkBox1.isChecked()){
                id = 1;
                selectProfissional.setVisibility(View.VISIBLE);
                selectProfissional2.setVisibility(View.INVISIBLE);
                selectProfissional3.setVisibility(View.INVISIBLE);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
            }
        } else if (servicos.getCheckbox() == 2) {
            checkBox2.setChecked(true);
            if (checkBox2.isChecked()) {
                id = 2;
                selectProfissional.setVisibility(View.INVISIBLE);
                selectProfissional2.setVisibility(View.VISIBLE);
                selectProfissional3.setVisibility(View.INVISIBLE);
                checkBox1.setChecked(false);
                checkBox3.setChecked(false);
            }
        } else if (servicos.getCheckbox() == 3) {
            checkBox3.setChecked(true);
            if (checkBox3.isChecked()) {
                id = 3;
                selectProfissional.setVisibility(View.INVISIBLE);
                selectProfissional2.setVisibility(View.INVISIBLE);
                selectProfissional3.setVisibility(View.VISIBLE);
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
            }
        }

        receberData.setText(servicos.getDia());

        //Enviar opcoes escolhidas
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked()) {
                    if (receberData.getText().toString().equals("")) {
                        Toast.makeText(ProfissionaisActivity.this, "Selecione uma data", Toast.LENGTH_LONG).show();
                    } else {
                        servicos.setId(id);
                        servicos.setHorario(hoarioSelecionado);
                        Intent it = new Intent(ProfissionaisActivity.this, AgendamentoActivity.class);
                        it.putExtra("funcionario", servicos);
                        startActivity(it);
                        finish();
                    }
                } else {
                    Toast.makeText(ProfissionaisActivity.this, "Selecione um profissional", Toast.LENGTH_LONG).show();
                }

            }
        });
        //Fim

        //Inicio Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.horarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //Fim Spinner

    } //Fim OnCreate

    //Pegar data atual do sistema
    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    Date data = new Date();
    String dataFormatada = formataData.format(data);

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //Responsavel por encontrar horario selecionado no spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String horario = parent.getItemAtPosition(position).toString();
        hoarioSelecionado = horario;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
