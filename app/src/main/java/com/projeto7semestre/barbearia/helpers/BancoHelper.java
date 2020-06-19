package com.projeto7semestre.barbearia.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BancoHelper extends SQLiteOpenHelper {
    public static final String BANCO = "bdpesoa";
    public static final String TABLE_CLIENTE = "cliente";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NOME = "nome";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_FONE = "telefone";
    public static final String FIELD_SEXO = "sexo";
    private static final int VERSION = 1;

    public BancoHelper(@Nullable Context context) {
        super(context, BANCO, null, VERSION);
    }

    @Override
    //Não é o mesmo onCreate do MainActivity, este, esta relacionado ao banco de dados.
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CLIENTE + "( " + FIELD_ID + " integer primary key autoincrement, " +
                FIELD_NOME + " text, " +
                FIELD_EMAIL + " text, " +
                FIELD_SEXO + " text, " +
                FIELD_FONE + " double)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);
        onCreate(db);
    }
}
