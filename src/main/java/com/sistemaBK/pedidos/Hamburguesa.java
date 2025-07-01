package com.sistemaBK.pedidos;

import java.util.ArrayList;

public class Hamburguesa extends Item implements EditarItem {

    ArrayList<Ingrediente> ingredientes;
    ArrayList<Ingrediente> ingredientesAgregables;
    private double precio;

    public Hamburguesa(String nombre, double precio) {
        super(nombre);
        ingredientes = new ArrayList<>();
        ingredientesAgregables = new ArrayList<>();
        inicializarIngredientes();
    }

    public void mostrarIngredientesAgregables() {
        System.out.println("Ingredientes agregables:");
        for (int i = 0; i < ingredientesAgregables.size(); i++) {
            Ingrediente ing = ingredientesAgregables.get(i);
            System.out.printf("%d. %s - $%.2f%n", i + 1, ing.getNombre(), ing.getPrecio());
        }
    }

    public Ingrediente getIngredienteAgregableByIndex(int index) {
        if (index >= 0 && index < ingredientesAgregables.size()) {
            return ingredientesAgregables.get(index);
        }
        return null;
    }

    public void mostrarIngredientesActuales() {
        System.out.println("Ingredientes actuales:");
        java.util.Map<String, Integer> contadorIngredientes = new java.util.HashMap<>();
        java.util.Map<String, Double> preciosIngredientes = new java.util.HashMap<>();

        // Contar ingredientes y guardar sus precios
        for (Ingrediente ing : ingredientes) {
            contadorIngredientes.put(ing.getNombre(), contadorIngredientes.getOrDefault(ing.getNombre(), 0) + 1);
            preciosIngredientes.putIfAbsent(ing.getNombre(), ing.getPrecio());
        }

        int index = 1;
        for (java.util.Map.Entry<String, Integer> entry : contadorIngredientes.entrySet()) {
            String nombre = entry.getKey();
            int cantidad = entry.getValue();
            double precio = preciosIngredientes.get(nombre);

            if (cantidad > 1) {
                System.out.printf("%d. %s x %d - $%.2f c/u%n", index++, nombre, cantidad, precio);
            } else {
                System.out.printf("%d. %s - $%.2f%n", index++, nombre, precio);
            }
        }
    }

    public boolean quitarIngredientePorIndice(int index) {
        if (index >= 0 && index < ingredientes.size()) {
            ingredientes.remove(index);
            precio = calcularGuardarPrecio();
            return true;
        }
        return false;
    }

    private void inicializarIngredientes() {
        ingredientesAgregables.add(new Ingrediente("Queso", 1.0));
        ingredientesAgregables.add(new Ingrediente("Bacon", 1.5));
        ingredientesAgregables.add(new Ingrediente("Lechuga", 0.5));
        ingredientesAgregables.add(new Ingrediente("Tomate", 0.5));

        ingredientes.add(new Ingrediente("Carne", 2.0));
        ingredientes.add(new Ingrediente("Pan", 0.5));

        precio = calcularGuardarPrecio();
    }

    @Override
    public void agregarIngrediente(String nombre) {
        for (Ingrediente ingrediente : ingredientesAgregables) {
            if (ingrediente.getNombre().equalsIgnoreCase(nombre)) {
                ingredientes.add(ingrediente);
                System.out.println("Ingrediente agregado: " + ingrediente.getNombre());
                return;
            }
        }
        // calcular nuevamente el precio
        precio = calcularGuardarPrecio();
        System.out.println("Ingrediente no encontrado: " + nombre);
    }

    @Override
    public void quitarIngrediente(String nombre) {
        for (int i = 0; i < ingredientes.size(); i++) {
            if (ingredientes.get(i).getNombre().equalsIgnoreCase(nombre)) {
                ingredientes.remove(i);
                System.out.println("Ingrediente quitado: " + nombre);
                precio = calcularGuardarPrecio();
                return;
            }
        }
        System.out.println("Ingrediente no encontrado: " + nombre);
    }

    @Override
    public double calcularGuardarPrecio() {
        if (ingredientes.isEmpty()) {
            precio = 0.0;
            return 0.0;
        }
        double precioFinal = 0.0;
        for (Ingrediente ingrediente : ingredientes) {
            precioFinal += ingrediente.getPrecio();
        }
        precio = precioFinal;
        return precioFinal;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    public ArrayList<Ingrediente> getIngredientesActualesList() {
        return new ArrayList<>(ingredientes);
    }
}
