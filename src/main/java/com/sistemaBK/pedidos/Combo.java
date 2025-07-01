package com.sistemaBK.pedidos;

import java.util.ArrayList;

public abstract class Combo extends Item implements AdministrarCombo {
    protected ArrayList<Item> listaItems;
    protected double precio;

    public Combo(String nombre) {
        super(nombre);
        this.listaItems = new ArrayList<>();
    }

    public ArrayList<Item> getListaItems() {
        return listaItems;
    }

    public ArrayList<Item> getItems() {
        return listaItems;
    }

    public double calcularPrecioFinalCombo() {
        double precioTotal = 0.0;
        for (Item item : listaItems) {
            precioTotal += item.getPrecio();
        }
        return precioTotal;
    }

    @Override
    public double getPrecio() {
        return calcularPrecioFinalCombo();
    }
}
