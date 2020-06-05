package com.projeto7semestre.barbearia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.projeto7semestre.barbearia.mask.Mask;

public class AgendamentoActivity extends AppCompatActivity {

    TextView txtData;
    EditText txtNome, txtEmail, txtFone;
    CheckBox chbMasc, chbFem, chbOutro;
    Button btnConfirm, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtFone = findViewById(R.id.txtFone);
        txtData = findViewById(R.id.txtData);
        chbMasc = findViewById(R.id.chbMasc);
        chbFem = findViewById(R.id.chbFem);
        chbOutro = findViewById(R.id.chbOutro);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);
        txtNome.requestFocus();

        txtFone.addTextChangedListener(Mask.insert("(##)#####-####", txtFone));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AgendamentoActivity.this, AgendaActivity.class);
                startActivity(it);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            txtData.setText(extras.getString("key"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}