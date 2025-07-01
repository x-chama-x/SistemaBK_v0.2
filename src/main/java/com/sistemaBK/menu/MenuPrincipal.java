package com.sistemaBK.menu;

import com.sistemaBK.BD;
import com.sistemaBK.usuarios.Usuario;

import java.util.Scanner;
import static com.sistemaBK.Sistema.limpiarPantalla;
import static com.sistemaBK.Sistema.pausar;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        mostrarMenuInicial();
    }

    private void mostrarMenuInicial() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== SISTEMA DE BURGER KING ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    salir = true;
                    limpiarPantalla();
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void iniciarSesion() {
        System.out.print("Ingrese su usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su Contraseña: ");
        String password = scanner.nextLine();

        if (BD.verificarUsuario(username, password)) {
            Usuario usuario = BD.obtenerUsuarioPorUsername(username);
            MenuStrategyHandler.mostrarMenuPara(usuario);
        } else {
            System.out.println("Usuario o contraseña incorrectos");
            pausar();
            limpiarPantalla();
        }
    }
}
