package com.example.trabalhofatec.tela;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.trabalhofatec.Banco.AnimalDAO;
import com.example.trabalhofatec.R;
import com.example.trabalhofatec.bean.Animal;

import java.util.ArrayList;
import java.util.List;

public class ListarAnimaisActivity extends AppCompatActivity {

    private ListView listView;
    private AnimalDAO animalDAO;
    private List<Animal> animais;
    private List<Animal> animais_filtrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_animais);

        listView = findViewById(R.id.lista_animais);
        animalDAO = new AnimalDAO(this);
        animais = animalDAO.obterAnimais();
        animais_filtrados.addAll(animais);
        ArrayAdapter<Animal> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, animais_filtrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_superior_ani, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraAnimal(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selecao_ani, menu);
    }

    public void procuraAnimal(String nome){
        animais_filtrados.clear();
        for (Animal ani : animais){
            if (ani.getNome().toLowerCase().contains(nome.toLowerCase())){
                animais_filtrados.add(ani);
            }
        }
        listView.invalidateViews();
    }

    public void excluirAnimal (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Animal animalExcluir = animais_filtrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Excluir o funcionário?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        animais_filtrados.remove(animalExcluir);
                        animais.remove(animalExcluir);
                        animalDAO.excluirAni(animalExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void cadastrarAnimal (MenuItem item){
        Intent it = new Intent(this, CadastroAnimalActivity.class);
        startActivity(it);
    }

    public void atualizarAnimal (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Animal animalAtualizar = animais_filtrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroAnimalActivity.class);
        it.putExtra("animal", animalAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        animais = animalDAO.obterAnimais();
        animais_filtrados.clear();
        animais_filtrados.addAll(animais);
        listView.invalidateViews();
    }
}
