package com.sistemaBK.menu;

import com.sistemaBK.*;
import com.sistemaBK.usuarios.Cocinero;
import com.sistemaBK.usuarios.Usuario;

import java.util.Scanner;
import static com.sistemaBK.Sistema.limpiarPantalla;
import static com.sistemaBK.Sistema.pausar;

public class CocineroMenuStrategy implements MenuStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMenu(Usuario usuario) {
        if (!(usuario instanceof Cocinero)) {
            System.out.println("Error: Este menú solo puede ser accedido por un Cocinero");
            return;
        }

        Cocinero cocinero = (Cocinero) usuario;
        boolean salir = false;

        while (!salir) {
            limpiarPantalla();
            System.out.println("\n=== MENÚ COCINERO ===");
            System.out.println("1. Despachar pedido");
            System.out.println("2. Ver pedidos tomados por vendedor");
            System.out.println("3. Ver pedidos despachados");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    cocinero.despacharPedido();
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
                    System.out.println("Saliendo del menú cocinero...");
                    pausar();
                    limpiarPantalla();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor intente de nuevo.");
                    break;
            }

            if (!salir) {
                System.out.println("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        }
    }
}
