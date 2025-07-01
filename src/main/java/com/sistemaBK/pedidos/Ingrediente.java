package com.sistemaBK.pedidos;
public class Ingrediente {
private String nombre;
private double costo;

public Ingrediente(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public double getPrecio() {
        return costo;
    }

    public String getNombre() {
        return nombre;
    }
}
