package com.example.trabalhofatec.tela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trabalhofatec.Banco.AnimalDAO;
import com.example.trabalhofatec.R;
import com.example.trabalhofatec.bean.Animal;

public class CadastroAnimalActivity extends AppCompatActivity {

    private EditText nome;
    private EditText tipo;
    private EditText dono;
    private AnimalDAO animalDAO;
    private Animal animal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        nome = findViewById(R.id.editNomeA);
        tipo = findViewById(R.id.editTipo);
        dono = findViewById(R.id.editDono);
        animalDAO = new AnimalDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("animal")){
            animal = (Animal) it.getSerializableExtra("animal");
            nome.setText(animal.getNome());
            tipo.setText(animal.getTipo());
            dono.setText(animal.getDono());
        }

    }

    public void salvarAni (View view){
        if (animal == null){
            Animal animal = new Animal();
            animal.setNome(nome.getText().toString());
            animal.setTipo(tipo.getText().toString());
            animal.setDono(dono.getText().toString());
            animalDAO.inserirAni(animal);
            Toast.makeText(this,"Animal criado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            animal.setNome(nome.getText().toString());
            animal.setTipo(tipo.getText().toString());
            animal.setDono(dono.getText().toString());
            animalDAO.atualizarAni(animal);
            Toast.makeText(this,"Animal foi Atualizado.", Toast.LENGTH_SHORT).show();
        }
    }
}
