package com.example.trabalhofatec.tela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.trabalhofatec.R;

public class MenuNavegacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navegacao);

    }

    public void ir_funcionarios(View view){
        Intent it = new Intent(this, ListarFuncionariosActivity.class);
        startActivity(it);
    }

    public void ir_animais (View view) {
        Intent it = new Intent(this, ListarAnimaisActivity.class);
        startActivity(it);
    }


    public void ir_banhos (View view){
        Intent it = new Intent(this, ListarBanhosActivity.class);
        startActivity(it);
    }


}
