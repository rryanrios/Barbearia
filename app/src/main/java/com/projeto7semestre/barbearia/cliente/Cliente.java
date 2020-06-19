package com.projeto7semestre.barbearia.cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.projeto7semestre.barbearia.helpers.BancoHelper;

import java.io.Serializable;

public class Cliente implements Serializable {

    private SQLiteDatabase db;
    private BancoHelper bancoHelper;

    public Cliente(Context context) {
        bancoHelper = new BancoHelper(context);
    }

    /*public add(Cliente cliente){
        ContentValues valores = new ContentValues();
        valores.put(BancoHelper.FIELD_NOME, cliente.getNome());
        valores.put(BancoHelper.FIELD_EMAIL, cliente.getEmail());
        valores.put(BancoHelper.FIELD_FONE, cliente.getTelefone());
        valores.put(BancoHelper.FIELD_SEXO, cliente.getSexo());
        this.db = bancoHelper.getReadableDatabase();
        long resultado = this.db.insert(BancoHelper.TABLE_CLIENTE, null, valores);
        if (resultado == -1) {
            return false;
        } else {
            return true;
        }
    }*/

    String nome;
    String email;
    double telefone;
    String sexo;

    public Cliente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTelefone() {
        return telefone;
    }

    public void setTelefone(double telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
