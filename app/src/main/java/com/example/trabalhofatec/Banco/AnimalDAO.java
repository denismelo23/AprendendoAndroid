package com.example.trabalhofatec.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhofatec.bean.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    private static BancoHelper bancoHelper;
    private static SQLiteDatabase banco;

    public AnimalDAO (Context context){
        bancoHelper = new BancoHelper(context);
        banco = bancoHelper.getWritableDatabase();
    }

    public long inserirAni (Animal animal){
        ContentValues values = new ContentValues();
        values.put("nome", animal.getNome());
        values.put("tipo", animal.getTipo());
        values.put("dono", animal.getDono());
        return banco.insert("animal",null,values);
    }

    public List<Animal> obterAnimais() {
        List<Animal> animais = new ArrayList<>();
        Cursor cursor = banco.query("animal", new String[]{"idani", "nome", "tipo", "dono"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Animal ani = new Animal();
            ani.setIdani(cursor.getInt(0));
            ani.setNome(cursor.getString(1));
            ani.setTipo(cursor.getString(2));
            ani.setDono(cursor.getString(3));
            animais.add(ani);
        }
        return animais;
    }

    public void excluirAni (Animal animal){
        banco.delete("animal", "idani = ?", new String[]{animal.getIdani().toString()});
    }

    public void atualizarAni (Animal animal){
        ContentValues values = new ContentValues();
        values.put("nome", animal.getNome());
        values.put("tipo", animal.getTipo());
        values.put("dono", animal.getDono());
        banco.update("animal", values, "idani = ?", new String[]{animal.getIdani().toString()});
    }

}
