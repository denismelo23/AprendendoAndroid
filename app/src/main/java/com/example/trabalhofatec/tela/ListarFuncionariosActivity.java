package com.example.trabalhofatec.tela;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.trabalhofatec.Banco.FuncionarioDAO;
import com.example.trabalhofatec.R;
import com.example.trabalhofatec.bean.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class ListarFuncionariosActivity extends AppCompatActivity {

    private ListView listView;
    private FuncionarioDAO funcionarioDAO;
    private List<Funcionario> funcionarios;
    private List<Funcionario> funcionarios_filtrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_funcionarios);

        listView = findViewById(R.id.lista_funcionarios);
        funcionarioDAO = new FuncionarioDAO(this);
        funcionarios = funcionarioDAO.obterTodos();
        funcionarios_filtrados.addAll(funcionarios);
        ArrayAdapter<Funcionario> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, funcionarios_filtrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_superior_func, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraFuncionario(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selecao_func, menu);
    }

    public void procuraFuncionario(String nome){
        funcionarios_filtrados.clear();
        for (Funcionario func : funcionarios){
            if (func.getNome().toLowerCase().contains(nome.toLowerCase())){
                funcionarios_filtrados.add(func);
            }
        }
        listView.invalidateViews();
    }

    public void excluir (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Funcionario funcionarioExcluir = funcionarios_filtrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Excluir o funcionário?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        funcionarios_filtrados.remove(funcionarioExcluir);
                        funcionarios.remove(funcionarioExcluir);
                        funcionarioDAO.excluirFunc(funcionarioExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void cadastrar (MenuItem item){
        Intent it = new Intent(this, CadastroFuncionarioActivity.class);
        startActivity(it);
    }

    public void atualizar (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Funcionario funcionarioAtualizar = funcionarios_filtrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroFuncionarioActivity.class);
        it.putExtra("funcionario", funcionarioAtualizar);
        startActivity(it);

    }

    @Override
    public void onResume(){
        super.onResume();
        funcionarios = funcionarioDAO.obterTodos();
        funcionarios_filtrados.clear();
        funcionarios_filtrados.addAll(funcionarios);
        listView.invalidateViews();

    }
}
