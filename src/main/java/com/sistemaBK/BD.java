package com.sistemaBK;

import com.sistemaBK.pedidos.Combo;
import com.sistemaBK.pedidos.Estado;
import com.sistemaBK.pedidos.Item;
import com.sistemaBK.pedidos.Pedido;
import com.sistemaBK.usuarios.*;

import java.util.ArrayList;

public class BD {
    public static ArrayList<Item> Items;
    public static ArrayList<Combo> Combos;
    public static ArrayList<Usuario> usuarios;
    public static ArrayList<Pedido> pedidos;
    public static int asignarId = 1;

    public BD() {
        Items = new ArrayList<>();
        Combos = new ArrayList<>();
        usuarios = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public static ArrayList<Item> getItems() {
        return Items;
    }

    public static ArrayList<Combo> getCombos() {
        return Combos;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public static ArrayList<Pedido> getPedidosDespachados() {
        ArrayList<Pedido> pedidosDespachados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == Estado.DESPACHADO) {
                pedidosDespachados.add(pedido);
            }
        }
        return pedidosDespachados; // Retorna la lista de pedidos en estado DESPACHADO
    }

    public static ArrayList<Pedido> getPedidosTomados() {
        ArrayList<Pedido> pedidosTomados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == Estado.TOMADO) {
                pedidosTomados.add(pedido);
            }
        }
        return pedidosTomados; // Retorna la lista de pedidos en estado TOMADO
    }

    public static Item getItemByName(String nombre) {
        for (Item item : Items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                return item;
            }
        }
        System.out.println("Item no encontrado: " + nombre);
        return null; // Retorna null si no se encuentra el item
    }

    public static Combo getComboByName(String nombre) {
        for (Combo combo : Combos) {
            if (combo.getNombre().equalsIgnoreCase(nombre)) {
                return combo;
            }
        }
        System.out.println("Combo no encontrado: " + nombre);
        return null; // Retorna null si no se encuentra el combo
    }

    public static void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public static void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        System.out.println("Lista de usuarios registrados:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public static void mostrarItemsDeCombo(Combo combo) {
        ArrayList<Item> listaItems = combo.getListaItems();
        if (listaItems.isEmpty()) {
            System.out.println("El combo " + combo.getNombre() + " no tiene items.");
            return;
        }

        System.out.println(combo.getNombre() + " - $" + String.format("%.2f", combo.getPrecio()));
        System.out.println("Contiene:");

        // Crear un mapa temporal para contar los items y almacenar sus precios
        java.util.Map<String, Integer> itemCount = new java.util.HashMap<>();
        java.util.Map<String, Double> itemPrices = new java.util.HashMap<>();

        // Contar la frecuencia de cada item y guardar sus precios
        for (Item item : listaItems) {
            String nombre = item.getNombre();
            itemCount.put(nombre, itemCount.getOrDefault(nombre, 0) + 1);
            itemPrices.putIfAbsent(nombre, item.getPrecio());
        }

        // Mostrar cada item con su cantidad y precio individual
        for (java.util.Map.Entry<String, Integer> entry : itemCount.entrySet()) {
            String nombre = entry.getKey();
            int cantidad = entry.getValue();
            double precioUnitario = itemPrices.get(nombre);
            System.out.printf("  - %dx %s (Individual: $%.2f)%n",
                            cantidad, nombre, precioUnitario);
        }
    }

    public static void mostrarCombos() {
        if (Combos.isEmpty()) {
            System.out.println("No hay combos registrados en el sistema.");
            return;
        }
        System.out.println("Lista de combos registrados:");
        for (int i = 0; i < Combos.size(); i++) {
            System.out.print((i + 1) + ". ");
            mostrarItemsDeCombo(Combos.get(i));
        }
    }

    public static void mostrarListadoEmpleadosSinRol() {
        System.out.println("Listado de empleados sin rol asignado:");
        boolean hayEmpleadosSinRol = false;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Empleado && !(usuario instanceof Gerente || usuario instanceof Vendedor || usuario instanceof Cocinero || usuario instanceof Inspector)) {
                System.out.println(usuario);
                hayEmpleadosSinRol = true;
            }
        }
        if (!hayEmpleadosSinRol) {
            System.out.println("No hay empleados sin rol asignado.");
        }
    }

    public static void mostrarListadoEmpleadosConRol() {
        System.out.println("Listado de empleados con rol asignado:");
        boolean hayEmpleadosConRol = false;
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Empleado && (usuario instanceof Gerente || usuario instanceof Vendedor || usuario instanceof Cocinero || usuario instanceof Inspector)) {
                String rol = "";
                if (usuario instanceof Gerente) {
                    rol = "GERENTE";
                } else if (usuario instanceof Vendedor) {
                    rol = "VENDEDOR";
                } else if (usuario instanceof Cocinero) {
                    rol = "COCINERO";
                } else if (usuario instanceof Inspector) {
                    rol = "INSPECTOR";
                }
                System.out.println(usuario + " - Rol: " + rol);
                hayEmpleadosConRol = true;
            }
        }
        if (!hayEmpleadosConRol) {
            System.out.println("No hay empleados con rol asignado.");
        }
    }

    public static boolean verificarUsuario(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return true; // Usuario encontrado y verificado
            }
        }
        return false; // Usuario no encontrado o contraseña incorrecta
    }

    public static Usuario obtenerUsuarioPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario; // Retorna el usuario si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra el usuario
    }

    public static void mostrarPedidosTomados() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en el sistema.");
            return;
        }
        boolean hayPedidosTomados = false;
        System.out.println("Lista de pedidos tomados:");

        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == Estado.TOMADO) {
                System.out.println(pedido);
                hayPedidosTomados = true;
            }
        }
        if (!hayPedidosTomados) {
            System.out.println("No hay pedidos en estado TOMADO.");
        }
    }

    public static void mostrarPedidosDespachados() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en el sistema.");
            return;
        }
        boolean hayPedidosDespachados = false;
        System.out.println("Lista de pedidos despachados:");

        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == Estado.DESPACHADO) {
                System.out.println(pedido);
                hayPedidosDespachados = true;
            }
        }
        if (!hayPedidosDespachados) {
            System.out.println("No hay pedidos en estado DESPACHADO.");
        }
    }

    public static void despacharPedido(Pedido pedido) {
        pedido.setEstado(Estado.DESPACHADO);
    }

    public static Empleado buscarEmpleadoPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Empleado && usuario.getId() == id) {
                return (Empleado) usuario; // Retorna el empleado si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra el empleado
    }

    public static void eliminarEmpleado(Empleado empleado) {
        usuarios.remove(empleado); // Elimina al empleado de la lista de usuarios
    }

    public static void agregarEmpleado(Empleado empleado) {
        usuarios.add(empleado); // Agrega al empleado a la lista de usuarios
    }

    public static Combo getComboByIndex(int i) {
        if (i < 0 || i >= Combos.size()) {
            return null; // Retorna null si el índice es inválido
        }
        return Combos.get(i); // Retorna el combo en la posición indicada
    }

    public static void mostrarItems() {
        System.out.println("\n=== ITEMS DISPONIBLES ===");
        for (int i = 0; i < Items.size(); i++) {
            System.out.println((i + 1) + ". " + Items.get(i).getNombre() + " - $" + Items.get(i).getPrecio());
        }
    }

    public static Item getItemByIndex(int index) {
        if (index >= 0 && index < Items.size()) {
            return Items.get(index);
        }
        return null;
    }

}