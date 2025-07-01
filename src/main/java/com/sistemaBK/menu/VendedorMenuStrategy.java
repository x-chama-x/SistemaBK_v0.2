package com.sistemaBK.menu;

import com.sistemaBK.*;
import com.sistemaBK.usuarios.Usuario;
import com.sistemaBK.usuarios.Vendedor;

import java.util.Scanner;
import static com.sistemaBK.Sistema.limpiarPantalla;
import static com.sistemaBK.Sistema.pausar;

public class VendedorMenuStrategy implements MenuStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMenu(Usuario usuario) {
        if (!(usuario instanceof Vendedor)) {
            System.out.println("Error: Este menú solo puede ser accedido por un Vendedor");
            return;
        }

        Vendedor vendedor = (Vendedor) usuario;
        boolean salir = false;

        while (!salir) {
            limpiarPantalla();
            System.out.println("\n=== MENÚ VENDEDOR ===");
            System.out.println("1. Tomar pedido");
            System.out.println("2. Ver pedidos tomados");
            System.out.println("3. Ver pedidos despachados");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    limpiarPantalla();
                    vendedor.tomarPedido();
                    break;
                case 2:
                    limpiarPantalla();
                    BD.mostrarPedidosTomados();
                    break;
                case 3:
                    limpiarPantalla();
                    BD.mostrarPedidosDespachados();
                    break;
                case 4:
                    salir = true;
                    System.out.println("Saliendo del menú vendedor...");
                    pausar();
                    limpiarPantalla();
                    break;
                default:
                    System.out.println("Opción no válida");
                    pausar();
                    limpiarPantalla();
                    break;
            }

            if (!salir) {
                pausar();
            }
        }
    }
}
