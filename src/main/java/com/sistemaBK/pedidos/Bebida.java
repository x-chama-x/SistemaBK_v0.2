package com.sistemaBK.pedidos;

public class Bebida extends Item {
    private double precio;

    public Bebida(String nombre, double precioBase) {
        super(nombre);
        this.precio = precioBase;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
