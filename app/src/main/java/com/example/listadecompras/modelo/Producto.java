package com.example.listadecompras.modelo;

import androidx.annotation.NonNull;

public class Producto {
    private  String nombre;
    private  int cantidad;
    private String unidad;
    private boolean estado;

    public static final boolean PENDIENTE=true;  // <-- variable constante
    public static final boolean COMPRADO=false;  // <-- variable constante

    public Producto(String nombre, int cantidad, String unidad, boolean estado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.estado = estado;
    }

    /*
    public static final Producto[] productos=
            {
                    new Producto("Bebida",1,"litro"),
                    new Producto("Papas fritas",2,"Bolsas"),
                    new Producto("Aceitunas",2,"paquetes")
            };
   */


    public Producto(String nombre, int cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.estado = PENDIENTE;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public boolean isEstado() {
        return estado;
    }

    @Override
    public String toString() {
        String comprado;
        if(estado==COMPRADO){
            comprado="comprado";
        }
        else{
            comprado="pendiente";
        }
        return nombre +": " + comprado;
    }
}
