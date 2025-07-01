package com.sistemaBK;

import com.sistemaBK.menu.MenuPrincipal;
import com.sistemaBK.pedidos.*;
import com.sistemaBK.usuarios.*;

import java.util.Scanner;

public class Sistema {
    private MenuPrincipal menuPrincipal;

    public Sistema() {
        menuPrincipal = new MenuPrincipal();
        inicializarUsuariosDeFabrica();
        inicializarItemsDeFabrica();
        incializarCombos();
    }

    public void iniciar() {
        menuPrincipal.mostrarMenu();  // Ya no necesitamos pasar null como parámetro
    }

    private int generarId() {
        return BD.asignarId++;
    }

    private void inicializarUsuariosDeFabrica() {
        // Crear usuario gerente que administra este sistema
        Gerente g = new Gerente("Francisco", "francisco", "123", generarId());
        BD.agregarUsuario(g);
        System.out.println("Usuario gerente creado: " + " usuario: " + g.getUsername() + " clave: " + g.getPassword());

        // Crear un usuario Inspector para el sistema
        Inspector i = new Inspector("Roberto", "Rob", "123", generarId());
        BD.agregarUsuario(i);
        System.out.println("Usuario Inspector creado: " + " usuario: " + i.getUsername() + " clave: " + i.getPassword());

        // Crear empleados sin rol asignado
        Empleado e1 = new Empleado("Franco", "franco", "123",generarId());
        Empleado e2 = new Empleado("María", "maria", "123",generarId());
        Empleado e3 = new Empleado("Carlos", "carlos", "123",generarId());

        // crear empleados con rol asignado HARDCODEADO PARA TESTS
        Vendedor v1 = new Vendedor("Ana", "ana", "123", generarId());
        BD.agregarUsuario(v1);
        System.out.println("Usuario Vendedor creado: " + " usuario: " + v1.getUsername() + " clave: " + v1.getPassword());
        Cocinero c1 = new Cocinero("Luis", "luis", "123", generarId());
        BD.agregarUsuario(c1);
        System.out.println("Usuario Cocinero creado: " + " usuario: " + c1.getUsername() + " clave: " + c1.getPassword());
        System.out.println();

        // Agregar empleados sin rol al sistema
        BD.agregarUsuario(e1);
        BD.agregarUsuario(e2);
        BD.agregarUsuario(e3);

        System.out.println("Empleados sin rol creados y agregados al sistema.");
    }

    private void inicializarItemsDeFabrica() {
        // Crear y agregar ítems a la BD
        BD.getItems().add(new Hamburguesa("Hamburguesa Clásica", 0.0)); // El 0.0 es un placeholder, el precio se calcula
        BD.getItems().add(new Papas("Papas Fritas", 0.0));             // El 0.0 es un placeholder, el precio se calcula
        BD.getItems().add(new Bebida("Coca-Cola", 2.5));                 // El precio base se usa directamente
        BD.getItems().add(new Postre("Helado de Frutilla"));            // El precio se calcula con sus ingredientes base

        System.out.println("Ítems básicos cargados en la BD.");

    }

    private void incializarCombos() {
        // Crear y agregar combos a la BD
        Familiar comboFamiliar = new Familiar("Familiar");
        BD.getCombos().add(comboFamiliar);
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pausar() {
        System.out.println("\nPresione ENTER para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void mostrarListadoEmpleadosSinRol(){
        BD.mostrarListadoEmpleadosSinRol();
    }
}
