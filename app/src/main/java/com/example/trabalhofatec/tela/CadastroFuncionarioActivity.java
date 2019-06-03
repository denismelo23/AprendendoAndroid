package com.example.trabalhofatec.tela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhofatec.Banco.FuncionarioDAO;
import com.example.trabalhofatec.R;
import com.example.trabalhofatec.bean.Funcionario;

public class CadastroFuncionarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private FuncionarioDAO funcionarioDAO;
    private Funcionario funcionario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        nome = findViewById(R.id.editNomeA);
        email = findViewById(R.id.editEmail);
        telefone = findViewById(R.id.editTelefone);
        funcionarioDAO = new FuncionarioDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("funcionario")){
            funcionario = (Funcionario) it.getSerializableExtra("funcionario");
            nome.setText(funcionario.getNome());
            email.setText(funcionario.getEmail());
            telefone.setText(funcionario.getTelefone());
        }

    }

    public void salvarFunc (View view){
        if (funcionario == null){
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome.getText().toString());
            funcionario.setEmail(email.getText().toString());
            funcionario.setTelefone(telefone.getText().toString());
            funcionarioDAO.inserirFunc(funcionario);
            //long id = funcionarioDAO.inserirFunc(func);
            Toast.makeText(this, "Funcionário criado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            funcionario.setNome(nome.getText().toString());
            funcionario.setEmail(email.getText().toString());
            funcionario.setTelefone(telefone.getText().toString());
            funcionarioDAO.atualizar(funcionario);
            Toast.makeText(this, "Funcionário foi Atualizado.", Toast.LENGTH_SHORT).show();
        }


    }

}
