package com.sistemaBK.menu;

import com.sistemaBK.*;
import com.sistemaBK.usuarios.Gerente;
import com.sistemaBK.usuarios.Usuario;

import java.util.Scanner;
import static com.sistemaBK.Sistema.limpiarPantalla;
import static com.sistemaBK.Sistema.pausar;

public class GerenteMenuStrategy implements MenuStrategy {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMenu(Usuario usuario) {
        if (!(usuario instanceof Gerente)) {
            System.out.println("Error: Este menú solo puede ser accedido por un Gerente");
            return;
        }

        Gerente gerente = (Gerente) usuario;
        boolean salir = false;

        while (!salir) {
            limpiarPantalla();
            System.out.println("\n=== MENÚ GERENTE ===");
            System.out.println("1. Ver empleados sin rol");
            System.out.println("2. Ver empleados con rol");
            System.out.println("3. Asignar rol a empleado");
            System.out.println("4. Ver pedidos tomados");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    limpiarPantalla();
                    BD.mostrarListadoEmpleadosSinRol();
                    pausar();
                    break;
                case 2:
                    limpiarPantalla();
                    BD.mostrarListadoEmpleadosConRol();
                    pausar();
                    break;
                case 3:
                    limpiarPantalla();
                    gerente.asignarRolAEmpleado();
                    pausar();
                    break;
                case 4:
                    limpiarPantalla();
                    BD.mostrarPedidosTomados();
                    pausar();
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo del menú gerente...");
                    pausar();
                    limpiarPantalla();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}
