package com.sistemaBK.usuarios;

import com.sistemaBK.BD;
import com.sistemaBK.pedidos.Pedido;

import java.util.ArrayList;

public class Inspector extends Usuario {
    public Inspector(String nombre, String username, String password, int id) {
        super(nombre, username, password, id);
    }

    // metodo que genera un listado de ventas a partir de los pedidos despachados
    public void generarListadoVentas() {
        ArrayList<Pedido> pedidosDespachados = BD.getPedidosDespachados();
        if (pedidosDespachados.isEmpty()) {
            System.out.println("No hay pedidos despachados disponibles para generar el listado de ventas.");
            return;
        }

        System.out.println("\n=== LISTADO DE VENTAS ===");
        double totalVentas = 0;
        for (Pedido pedido : pedidosDespachados) {
            System.out.println(pedido);
            totalVentas += pedido.calcularPrecioFinal();
        }
        System.out.println("\nTotal de ventas: $" + String.format("%.2f", totalVentas));
    }
}
