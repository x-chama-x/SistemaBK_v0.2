package com.sistemaBK.usuarios;
import com.sistemaBK.BD;
import com.sistemaBK.pedidos.Pedido;

import java.util.ArrayList;
import java.util.Scanner;

public class Cocinero extends Empleado {

    public Cocinero(String nombre, String username, String password, int id) {
        super(nombre, username, password, id);
    }

    public void despacharPedido() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pedido> pedidos = BD.getPedidosTomados();

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos tomados para despachar.");
            return;
        }

        System.out.println("\nIngrese el ID del pedido que desea despachar:");
        int idPedido = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Pedido pedidoADespachar = null;
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == idPedido) {
                pedidoADespachar = pedido;
                break;
            }
        }

        if (pedidoADespachar == null) {
            System.out.println("No se encontró un pedido con el ID: " + idPedido);
            return;
        }

        BD.despacharPedido(pedidoADespachar);
        System.out.println("Pedido despachado exitosamente: " + pedidoADespachar);
    }
}
