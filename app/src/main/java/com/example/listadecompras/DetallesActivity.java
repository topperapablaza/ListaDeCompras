package com.example.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listadecompras.modelo.ComprasDatabaseHelper;
import com.example.listadecompras.modelo.ListaDeCompras;
import com.example.listadecompras.modelo.Producto;

public class DetallesActivity extends AppCompatActivity {
    public Producto producto;
    public Intent intent;
    public ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        //Obtener el nombre del producto
        intent=getIntent();
        String nombreProducto=(String) intent.getExtras().get("nombreProducto");

        //Traer el producto desde la base de datos
        producto=helper.getProducto(nombreProducto);

        //Nombre de producto
        TextView txtNombre=(TextView) findViewById(R.id.txtNombre);
        txtNombre.setText(producto.getNombre());

        //Cantidad y unidad
        TextView txtCantidad=(TextView) findViewById(R.id.txtCantidad);
        txtCantidad.setText("Cantidad: "+producto.getCantidad()+" "+producto.getUnidad());

        //Estado y Boton
        TextView txtEstado=(TextView) findViewById(R.id.txtEstado);
        Button estado=(Button) findViewById(R.id.estado);

        if(producto.isEstado()==Producto.COMPRADO){
            txtEstado.setText("Comprado");
            estado.setText("Marcar como pendiente");
        }
        else{
            txtEstado.setText("Pendiente");
            estado.setText("Marcar como comprado");
        }
        estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto.setEstado(!producto.isEstado());
                //Actualizar la base de datos
                String msg=helper.cambiarEstado(producto);
                Toast.makeText(DetallesActivity.this,msg, Toast.LENGTH_SHORT).show();

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}