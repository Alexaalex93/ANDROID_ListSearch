package com.example.cice.listsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> lista_ordenada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_ordenada = new ArrayList<>();

        lista_ordenada.add("Ana");
        lista_ordenada.add("Alex");
        lista_ordenada.add("Paco");
        lista_ordenada.add("Oscar");
        lista_ordenada.add("Javi");
        lista_ordenada.add("Zalacain");

        Collections.sort(lista_ordenada);

        ListAdapter Adapter = new ArrayAdapter<String>(this, R.layout.fila, lista_ordenada);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(Adapter);

        SearchView searchView = (SearchView) findViewById(R.id.cajaBusqueda);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(getClass().getCanonicalName(), "onQueryTextSummit" + query); //Para utilizarlo cuando le das al la tecla de buscar
                
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { //Para que se ejecute con un cambio ("en tiempo real")
                Log.d(getClass().getCanonicalName(),"onQueryTextChange" + newText);

                ArrayList<String> lista_coincidencias = new ArrayList<String>();

                for(String s: lista_ordenada){
                    if(s.toLowerCase().contains(newText.toLowerCase()))
                        lista_coincidencias.add(s);
                }

                ListAdapter listAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.fila, lista_coincidencias);

                ListView listView1 = (ListView) findViewById(R.id.listView);
                listView1.setAdapter(listAdapter);
                return false;
            }
        });


    }
}
