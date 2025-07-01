package com.sistemaBK.pedidos;

public interface EditarItem {
    void agregarIngrediente(String nombre);
    void quitarIngrediente(String nombre);
    double calcularGuardarPrecio();
    void mostrarIngredientesAgregables();
}
