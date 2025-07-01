package com.sistemaBK.pedidos;

import com.sistemaBK.BD;

public class Familiar extends Combo {

    public Familiar(String nombre) {
        super(nombre);
        inicializarCombo();
    }

    @Override
    public void inicializarCombo() {
        // Inicializa el combo familiar con items predeterminados
        agregarItemACombo("Hamburguesa Clásica");
        agregarItemACombo("Hamburguesa Clásica");
        agregarItemACombo("Papas Fritas");
        agregarItemACombo("Papas Fritas");
        agregarItemACombo("Coca-Cola");
        agregarItemACombo("Coca-Cola");
        agregarItemACombo("Coca-Cola");
        agregarItemACombo("Coca-Cola");
        calcularPrecioFinalCombo(); // Calcula el precio inicial
    }

    @Override
    public void agregarItemACombo(String nombre) {
        Item itemOriginal = BD.getItemByName(nombre);
        if (itemOriginal != null) {
            Item itemCopia = null;

            // Crear una nueva instancia según el tipo de Item
            if (itemOriginal instanceof Hamburguesa) {
                Hamburguesa original = (Hamburguesa) itemOriginal;
                itemCopia = new Hamburguesa(original.getNombre(), original.getPrecio());
            } else if (itemOriginal instanceof Papas) {
                Papas original = (Papas) itemOriginal;
                itemCopia = new Papas(original.getNombre(), original.getPrecio());
            } else if (itemOriginal instanceof Postre) {
                itemCopia = new Postre(itemOriginal.getNombre());
            } else if (itemOriginal instanceof Bebida) {
                itemCopia = new Bebida(itemOriginal.getNombre(), itemOriginal.getPrecio());
            }

            if (itemCopia != null) {
                listaItems.add(itemCopia);
            }
        } else {
            System.out.println("Item no encontrado: " + nombre);
        }
        precio = calcularPrecioFinalCombo();
    }

    @Override
    public void eliminarItemDeCombo(String nombre) {
        Item item = getItemByName(nombre);
        if (item != null) {
            listaItems.remove(item);
            System.out.println("Item eliminado del combo: " + nombre);
        } else {
            System.out.println("Item no encontrado en el combo: " + nombre);
        }
        precio = calcularPrecioFinalCombo();
    }

    @Override
    public Item getItemByName(String nombre) {
        for (Item item : listaItems) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public double calcularPrecioFinalCombo() {
        double precioTotal = 0.0;
        for (Item item : listaItems) {
            precioTotal += item.getPrecio();
        }
        // Aplicamos un descuento del 15% por ser combo familiar
        precio = precioTotal * 0.85;
        return precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
