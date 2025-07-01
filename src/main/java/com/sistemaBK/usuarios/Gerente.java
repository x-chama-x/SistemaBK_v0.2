package com.sistemaBK.usuarios;

import com.sistemaBK.BD;

import java.util.Scanner;

public class Gerente extends Empleado {
    public Gerente(String nombre, String username, String password, int id) {
        super(nombre, username, password, id);
    }

    public void asignarRolAEmpleado() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Empleado empleado = BD.buscarEmpleadoPorId(id);
        if (empleado == null) {
            System.out.println("No se encontró un empleado con ese ID");
            return;
        }

        System.out.println("Seleccione el rol a asignar:");
        System.out.println("1. Vendedor");
        System.out.println("2. Cocinero");
        System.out.print("Opción: ");

        int opcionRol = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcionRol) {
            case 1:
                asignarRolVendedor(empleado);
                break;
            case 2:
                asignarRolCocinero(empleado);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void asignarRolVendedor(Empleado empleado) {
        if (empleado.getClass() != Empleado.class) {
            System.out.println("No se puede cambiar el rol. El empleado ya tiene un rol asignado.");
            return;
        }
        BD.eliminarEmpleado(empleado);
        Vendedor vendedor = new Vendedor(empleado.getNombre(), empleado.getUsername(), empleado.getPassword(), empleado.getId());
        BD.agregarEmpleado(vendedor);
        System.out.println("Rol de Vendedor asignado correctamente a " + vendedor.getNombre());
    }

    private void asignarRolCocinero(Empleado empleado) {
        if (empleado.getClass() != Empleado.class) {
            System.out.println("No se puede cambiar el rol. El empleado ya tiene un rol asignado.");
            return;
        }
        BD.eliminarEmpleado(empleado);
        Cocinero cocinero = new Cocinero(empleado.getNombre(), empleado.getUsername(), empleado.getPassword(), empleado.getId());
        BD.agregarEmpleado(cocinero);
        System.out.println("Rol de Cocinero asignado correctamente a " + cocinero.getNombre());
    }
}