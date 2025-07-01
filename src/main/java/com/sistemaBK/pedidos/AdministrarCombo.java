package com.sistemaBK.pedidos;

public interface AdministrarCombo {
    void agregarItemACombo(String nombre);
    void eliminarItemDeCombo(String nombre);
    Item getItemByName(String nombre);
    double calcularPrecioFinalCombo();
    void inicializarCombo();
}
