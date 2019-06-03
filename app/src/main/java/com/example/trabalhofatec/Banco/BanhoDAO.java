package com.example.trabalhofatec.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhofatec.bean.Banho;

import java.util.ArrayList;
import java.util.List;

public class BanhoDAO {

    private static BancoHelper bancoHelper;
    private static SQLiteDatabase banco;

    public BanhoDAO (Context context){
        bancoHelper = new BancoHelper(context);
        banco = bancoHelper.getWritableDatabase();
    }

    public long inseriBanho (Banho banho){
        ContentValues values = new ContentValues();
        values.put("idfub", banho.getIdfub());
        values.put("idanib", banho.getIdanib());
        values.put("data", banho.getData());
        values.put("hora", banho.getHora());
        return banco.insert("banho", null,values);
    }

    public List<Banho> obterBanhos(){
        List<Banho> banhos = new ArrayList<>();
        Cursor cursor = banco.query("banho", new String[]{"idban","idfub", "idanib","data","hora"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Banho ban = new Banho();
            ban.setIdban(cursor.getInt(0));
            ban.setIdfub(cursor.getInt(1));
            ban.setIdanib(cursor.getInt(2));
            ban.setData(cursor.getString(3));
            ban.setHora(cursor.getString(4));
            banhos.add(ban);
        }
        return banhos;
    }

    public void excluirBanho (Banho banho){
        banco.delete("banho", "idban = ?", new String[]{banho.getIdban().toString()});
    }

    public void atualizarBanho (Banho banho){
        ContentValues values = new ContentValues();
        values.put("idfub", banho.getIdfub());
        values.put("idanib", banho.getIdanib());
        values.put("data", banho.getData());
        values.put("hora", banho.getHora());
        banco.update("banho", values, "idban = ?", new String[]{banho.getIdban().toString()});
    }
}
