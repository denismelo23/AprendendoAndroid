package com.example.trabalhofatec.tela;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

import com.example.trabalhofatec.Banco.BanhoDAO;
import com.example.trabalhofatec.R;
import com.example.trabalhofatec.bean.Banho;

import java.util.ArrayList;
import java.util.List;

public class ListarBanhosActivity extends AppCompatActivity {

    private ListView listView;
    private BanhoDAO banhoDAO;
    private List<Banho> banhos;
    private List<Banho> banhos_filtratos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_banhos);

        listView = findViewById(R.id.lista_banhos);
        banhoDAO = new BanhoDAO(this);
        banhos = banhoDAO.obterBanhos();
        banhos_filtratos.addAll(banhos);
        ArrayAdapter<Banho> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, banhos_filtratos);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_superior_ban, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraBanho(s);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_selecao_ban, menu);
    }

    public void procuraBanho (String data){
        banhos_filtratos.clear();
        for (Banho ba : banhos){
            if (ba.getData().toLowerCase().contains(data.toLowerCase())){
                banhos_filtratos.add(ba);
            }
        }
        listView.invalidateViews();
    }

    public void excluirBanho (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Banho banhoExcluir = banhos_filtratos.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Excluir o funcionário?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        banhos_filtratos.remove(banhoExcluir);
                        banhos.remove(banhoExcluir);
                        banhoDAO.excluirBanho(banhoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void cadastrarBanho (MenuItem item){
        Intent it = new Intent(this, CadastroBanhoActivity.class);
        startActivity(it);
    }

    public void atualizarBanho (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Banho banhoAtualizar = banhos_filtratos.get(menuInfo.position);
        Intent it = new Intent(this, CadastroBanhoActivity.class);
        it.putExtra("banho", banhoAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        banhos = banhoDAO.obterBanhos();
        banhos_filtratos.clear();
        banhos_filtratos.addAll(banhos);
        listView.invalidateViews();
    }


}
