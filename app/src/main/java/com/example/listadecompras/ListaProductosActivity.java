package com.example.listadecompras;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.listadecompras.modelo.ComprasDatabaseHelper;
import com.example.listadecompras.modelo.ListaDeCompras;
import com.example.listadecompras.modelo.Producto;

import java.util.ArrayList;

public class ListaProductosActivity extends ListActivity {

    ListView lista;
    ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarLista();
    }
    public void cargarLista(){
        lista=getListView();
        ArrayList<Producto> productos= helper.listaProductos(); //Cargamos la lista desde la base de datos
        ArrayAdapter<Producto> listaAdapter=new ArrayAdapter<Producto>(
                this, android.R.layout.simple_list_item_1,
                productos
        );
        lista.setAdapter(listaAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l){
               //leer el elemento de la lista que se seleccionó
               Object obj=lista.getItemAtPosition(i);
               //Transformamos la línea a String
               String linea=obj.toString();
               //Obtenemos el nombre separanado el String en un arreglo de tipo String
               String[] separar=linea.split(":");
               //Llamar a DetallesActivity
               Intent intent=new Intent(ListaProductosActivity.this,DetallesActivity.class);
               //Enviamos el nombre del producto
               intent.putExtra("nombreProducto",separar[0]);
               startActivityForResult(intent, 1);
           }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                cargarLista();
            }
        }
    }
}