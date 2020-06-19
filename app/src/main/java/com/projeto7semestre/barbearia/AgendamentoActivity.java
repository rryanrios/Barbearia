package com.projeto7semestre.barbearia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto7semestre.barbearia.email.Email;
import com.projeto7semestre.barbearia.funcionario.Funcionario;
import com.projeto7semestre.barbearia.mask.Mask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgendamentoActivity extends AppCompatActivity {

    TextView txtData;
    EditText txtNome, txtEmail, txtFone;
    CheckBox chbMasc, chbFem;
    Button btnConfirm;

    Funcionario funcionario = new Funcionario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        //Adicionar seta voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Fim

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtFone = findViewById(R.id.txtFone);
        txtData = findViewById(R.id.txtData);
        chbMasc = findViewById(R.id.chbMasc);
        chbFem = findViewById(R.id.chbFem);
        btnConfirm = findViewById(R.id.btnConfirm);
        txtNome.requestFocus();

        chbMasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbMasc.isChecked())
                    chbFem.setChecked(false);
            }
        });

        chbFem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbFem.isChecked())
                    chbMasc.setChecked(false);
            }
        });

        //Mascara para telefone
        txtFone.addTextChangedListener(Mask.insert("(##)#####-####", txtFone));

        //Recebe os dados da tela ProfissionaisActivity que vieram da tela ServicosActivity
        Bundle bundle = getIntent().getExtras();
        //Recebendo informacoes de nome, horarioSelecionado, servicoEscolhido, preco e tempoServico
        funcionario = (Funcionario) bundle.getSerializable("funcionario");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarNome()) {
                    return;
                } else if (!validarEmail()) {
                    return;
                } else if (!validarFone()) {
                    return;
                } else if (chbMasc.isChecked() || chbFem.isChecked()) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(AgendamentoActivity.this);

                    builder.setMessage("Profissional escolhido: " + funcionario.getNome() + "\nServico: " + funcionario.getServicoEscolhido() + "\nValor R$: " + funcionario.getPreco() + "\nDia: " + funcionario.getDia() + "\nHorario marcado: " + funcionario.getHorario() + "\nTempo: " + funcionario.getTempoServico());

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(AgendamentoActivity.this, "Agendamento confirmado", Toast.LENGTH_LONG).show();
                            gravarDados(funcionario);
                            Intent it = new Intent(AgendamentoActivity.this, MainActivity.class);
                            startActivity(it);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Confirmar agendamento?");
                    alert.show();
                } else {
                    Toast.makeText(AgendamentoActivity.this, "Selecione seu sexo!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }//Fim OnCreate

    private boolean validarNome() {
        String nomeCliente = txtNome.getText().toString().trim();
        if (nomeCliente.isEmpty()) {
            txtNome.setError("Preencha o campo nome");
            return false;
        } else if (nomeCliente.length() > 15) {
            txtNome.setError("Nome muito longo");
            return false;
        } else {
            txtNome.setError(null);
            return true;
        }
    }

    private boolean validarEmail() {
        String emailCliente = txtEmail.getText().toString().trim();
        if (emailCliente.isEmpty()) {
            txtEmail.setError("Preencha o campo email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()) {
            txtEmail.setError("Digite um email valido");
            return false;
        } else {
            txtEmail.setError(null);
            return true;
        }
    }

    private boolean validarFone() {
        String telCliente = txtFone.getText().toString().trim();
        if (telCliente.isEmpty()) {
            txtFone.setError("Preencha o campo telefone");
            return false;
        } else if (txtFone.length() < 14) {
            txtFone.setError("Digite um numero de celuar valido");
            return false;
        } else {
            txtFone.setError(null);
            return true;
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

    public void gravarDados(Funcionario funcionario) {
        SharedPreferences preferences = getSharedPreferences("preferenciasDoApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("chaveHistorico", this.funcionario.toString());
        editor.apply();
    }
}