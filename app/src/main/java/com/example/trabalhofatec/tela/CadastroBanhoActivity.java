package com.example.trabalhofatec.tela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trabalhofatec.Banco.AnimalDAO;
import com.example.trabalhofatec.Banco.BanhoDAO;
import com.example.trabalhofatec.Banco.FuncionarioDAO;
import com.example.trabalhofatec.R;
import com.example.trabalhofatec.bean.Animal;
import com.example.trabalhofatec.bean.Banho;
import com.example.trabalhofatec.bean.Funcionario;
import com.example.trabalhofatec.tela.ListarAnimaisActivity;
import com.example.trabalhofatec.tela.ListarFuncionariosActivity;

import java.util.ArrayList;
import java.util.List;

public class CadastroBanhoActivity extends AppCompatActivity {

    private Spinner spinner_func;
    private Spinner spinner_ani;
    private EditText Fub;
    private EditText Anib;
    private EditText data;
    private EditText hora;
    private BanhoDAO banhoDAO;
    private FuncionarioDAO funcionarioDAO;
    private List<Funcionario> funcionarios;
    private List<Animal> animais;
    private AnimalDAO animalDAO;
    private Funcionario func;
    private Animal an;
    private Banho banho = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_banho);

        func = new Funcionario();
        an = new Animal();

        spinner_func = (Spinner) findViewById(R.id.spinner_func);
        spinner_ani = (Spinner) findViewById(R.id.spinner_ani);
        Fub = findViewById(R.id.editFub);
        Anib = findViewById(R.id.editAnib);
        data = findViewById(R.id.editData);
        hora = findViewById(R.id.editHora);
        banhoDAO = new BanhoDAO(this);

        funcionarioDAO = new FuncionarioDAO(this);
        funcionarios = funcionarioDAO.obterTodos();
        ArrayAdapter<Funcionario>  adaptadorFunc = new ArrayAdapter<Funcionario>(this,R.layout.support_simple_spinner_dropdown_item, funcionarios);
        spinner_func.setAdapter(adaptadorFunc);

        animalDAO = new AnimalDAO(this);
        animais = animalDAO.obterAnimais();
        ArrayAdapter<Animal> adaptadorAni = new ArrayAdapter<Animal>(this, R.layout.support_simple_spinner_dropdown_item, animais);
        spinner_ani.setAdapter(adaptadorAni);

        Intent it = getIntent();
        if (it.hasExtra("banho")){
            banho = (Banho) it.getSerializableExtra("banho");
            //spinner_func.setSelection(getIndex(spinner_func ,func.getNome()));
            //spinner_ani.setSelection(getIndex(spinner_ani ,an.getNome()));
            Fub.setText(banho.getIdfub().toString());
            Anib.setText(banho.getIdanib().toString());
            data.setText(banho.getData());
            hora.setText(banho.getHora());
        }
    }

    public void salvarBanho (View view){
        if (banho == null){
            Banho banho = new Banho();

            //Integer trocaFu = (Integer) spinner_func.getItemAtPosition(func.getIdfu());
            //Integer trocaAn = (Integer) spinner_ani.getItemAtPosition(an.getIdani());
            Funcionario fu = (Funcionario) spinner_func.getSelectedItem();
            int trocaFu = fu.getIdfu();

            Animal a = (Animal) spinner_ani.getSelectedItem();
            int trocaAn = a.getIdani();


            banho.setIdfub(trocaFu);
            banho.setIdanib(trocaAn);
            //banho.setIdfub(Fub.getId());
            //banho.setIdanib(Anib.getId());
            banho.setData(data.getText().toString());
            banho.setHora(hora.getText().toString());
            banhoDAO.inseriBanho(banho);
            Toast.makeText(this,"Banho criado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            //Integer trocaFu = (Integer) spinner_func.getItemAtPosition(func.getIdfu());
            //Integer trocaAn = (Integer) spinner_ani.getItemAtPosition(an.getIdani());

            Funcionario fu = (Funcionario) spinner_func.getSelectedItem();
            int trocaFu = fu.getIdfu();
            Animal a = (Animal) spinner_ani.getSelectedItem();
            int trocaAn = a.getIdani();
            banho.setIdfub(trocaFu);
            banho.setIdanib(trocaAn);

            //banho.setIdfub(Fub.getId());
            //banho.setIdanib(Anib.getId());
            banho.setData(data.getText().toString());
            banho.setHora(hora.getText().toString());
            banhoDAO.atualizarBanho(banho);
            Toast.makeText(this,"Banho foi Atualizado.", Toast.LENGTH_SHORT).show();
        }
    }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }


}
