package com.sistemaBK.menu;

import com.sistemaBK.usuarios.Inspector;
import com.sistemaBK.usuarios.Usuario;

import java.util.Scanner;
import static com.sistemaBK.Sistema.limpiarPantalla;
import static com.sistemaBK.Sistema.pausar;

public class InspectorMenuStrategy implements MenuStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMenu(Usuario usuario) {
        if (!(usuario instanceof Inspector)) {
            System.out.println("Error: Este menú solo puede ser accedido por un Inspector");
            return;
        }

        Inspector inspector = (Inspector) usuario;
        boolean salir = false;

        while (!salir) {
            limpiarPantalla();
            System.out.println("\n=== MENÚ INSPECTOR ===");
            System.out.println("1. Generar listado de ventas");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    inspector.generarListadoVentas();
                    pausar();
                    break;
                case 2:
                    salir = true;
                    System.out.println("Saliendo del menú de Inspector");
                    pausar();
                    limpiarPantalla();
                    break;
                default:
                    System.out.println("Opción no válida");
                    limpiarPantalla();
            }
        }
    }
}
