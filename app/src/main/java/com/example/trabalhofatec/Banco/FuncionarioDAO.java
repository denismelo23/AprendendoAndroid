package com.example.trabalhofatec.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhofatec.bean.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private static BancoHelper bancoHelper;
    private static SQLiteDatabase banco;

    public FuncionarioDAO (Context context){
        bancoHelper = new BancoHelper(context);
        banco = bancoHelper.getWritableDatabase();

    }

    public long inserirFunc (Funcionario funcionario){
        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("email", funcionario.getEmail());
        values.put("telefone",funcionario.getTelefone());
        return banco.insert("funcionario",null,values);

    }

    public List<Funcionario> obterTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        Cursor cursor = banco.query("funcionario", new String[]{"idfu", "nome", "email", "telefone"},
                null, null, null , null , null);
        while (cursor.moveToNext()){
            Funcionario func = new Funcionario();
            func.setIdfu(cursor.getInt(0));
            func.setNome(cursor.getString(1));
            func.setEmail(cursor.getString(2));
            func.setTelefone(cursor.getString(3));
            funcionarios.add(func);
        }

        return funcionarios;
    }

    public void excluirFunc (Funcionario funcionario){
        banco.delete("funcionario", "idfu = ?", new String[]{funcionario.getIdfu().toString()});

    }

    public void atualizar (Funcionario funcionario){
        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("email", funcionario.getEmail());
        values.put("telefone",funcionario.getTelefone());
        banco.update("funcionario", values, "idfu= ?", new String[]{funcionario.getIdfu().toString()});
    }

}
