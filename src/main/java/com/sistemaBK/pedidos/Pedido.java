package com.sistemaBK.pedidos;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    private static int contadorId = 1; // Contador estático para generar IDs únicos
    private int id;
    private LocalDate fecha;
    private double precioFinal;
    private Estado estado;
    private ArrayList<Combo> combos = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    public Pedido(LocalDate fecha) {
        id = asignarID();
        this.fecha = fecha;
        this.estado = Estado.TOMADO; // Estado inicial del pedido
        this.precioFinal = 0.0; // Precio inicial del pedido
    }

    private int asignarID() {
        return contadorId++;
    }

    public double calcularPrecioFinal() {
        double precioTotal = 0.0;
        for (Combo combo : combos) {
            precioTotal += combo.calcularPrecioFinalCombo();
        }
        for (Item item : items) {
            precioTotal += item.getPrecio();
        }
        this.precioFinal = precioTotal;
        return precioFinal;
    }

    public void agregarCombo(Combo c){
        combos.add(c);
    }

    public void agregarItem(Item i) {
        items.add(i);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha.toString();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("id=").append(id);
        sb.append(", fecha=").append(fecha);
        sb.append(", precioFinal=").append(String.format("%.2f", precioFinal));
        sb.append(", estado=").append(estado);

        if (!combos.isEmpty()) {
            sb.append("\nCombos:");
            for (Combo combo : combos) {
                sb.append("\n  - ").append(combo.getNombre()).append(" ($").append(String.format("%.2f", combo.getPrecio())).append(")");
            }
        }

        if (!items.isEmpty()) {
            sb.append("\nItems:");
            for (Item item : items) {
                sb.append("\n  - ").append(item.getNombre()).append(" ($").append(String.format("%.2f", item.getPrecio())).append(")");
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
