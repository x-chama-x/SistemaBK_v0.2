package com.sistemaBK.pedidos;
public abstract class Item {
    private String nombre;

    public Item(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract double getPrecio();


    @Override
    public String toString() {
        return STR."Item{nombre='\{nombre}'}";
    }
}
