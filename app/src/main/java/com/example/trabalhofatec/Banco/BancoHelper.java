package com.example.trabalhofatec.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoHelper extends SQLiteOpenHelper {
    private static final String Nome_banco = "pet.db";
    private static final int version = 2;

    public static final String TABELA1 = "funcionario";
    public static final String TABELA2 = "animal";
    public static final String TABELA3 = "banho";

    private final String cria_func = "create table funcionario (idfu integer primary key autoincrement, nome text, email text, telefone text)";

    private final String criar_tabela_animal = "CREATE TABLE animal(idani INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, tipo TEXT, dono TEXT)";

    private final String cria_ani = "create table animal (idani integer primary key autoincrement, nome text, tipo text, dono text)";

    private final String cria_ban = " CREATE TABLE banho ( idban INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idfub INTEGER, idanib INTEGER, data TEXT, hora TEXT, FOREIGN KEY(idfub) REFERENCES funcionario(idfu) ON UPDATE CASCADE ON DELETE CASCADE, " +
            "FOREIGN KEY(idfub) REFERENCES animal(idani) ON UPDATE CASCADE ON DELETE CASCADE)";


    public BancoHelper(Context context) {
        super(context, Nome_banco, null, version);
        //this.cria_func = "create table funcionario (idfu integer primary key autoincrement, nome text, email text, telefone text)";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(cria_func);
        db.execSQL(cria_ani);
        db.execSQL(cria_ban);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA1);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA2);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA3);
        onCreate(db);

    }
}
