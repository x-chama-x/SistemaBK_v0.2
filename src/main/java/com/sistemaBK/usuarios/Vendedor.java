package com.sistemaBK.usuarios;
import com.sistemaBK.BD;
import com.sistemaBK.pedidos.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static com.sistemaBK.Sistema.limpiarPantalla;
import static com.sistemaBK.Sistema.pausar;

public class Vendedor extends Empleado {

    public Vendedor(String nombre, String username, String password, int id) {
        super(nombre, username, password, id);
    }

    private int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1; // Valor inválido que será manejado por el código que llama
        }
    }

    private void gestionarIngredientesItem(EditarItem item, Scanner scanner) {
        while (true) {
            limpiarPantalla();
            String nombreItem = "";
            if (item instanceof Item) {
                nombreItem = ((Item) item).getNombre();
            }
            System.out.println("\n=== GESTIONAR INGREDIENTES " + nombreItem.toUpperCase() + " ===");
            System.out.println("1. Agregar ingrediente");
            System.out.println("2. Quitar ingrediente");
            System.out.println("3. Ver ingredientes actuales");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion(scanner);
            if (opcion == 0) break;

            boolean seModificaron = false;
            switch (opcion) {
                case 1:
                    if (item instanceof EditarItem) {
                        limpiarPantalla();
                        if (item instanceof Hamburguesa) {
                            ((Hamburguesa) item).mostrarIngredientesAgregables();
                        } else if (item instanceof Papas) {
                            ((Papas) item).mostrarIngredientesAgregables();
                        } else if (item instanceof Postre) {
                            ((Postre) item).mostrarIngredientesAgregables();
                        }

                        System.out.print("\nSeleccione el número del ingrediente a agregar: ");
                        int ingredienteNum = leerOpcion(scanner);

                        Ingrediente ingrediente = null;
                        if (item instanceof Hamburguesa) {
                            ingrediente = ((Hamburguesa) item).getIngredienteAgregableByIndex(ingredienteNum - 1);
                        } else if (item instanceof Papas) {
                            ingrediente = ((Papas) item).getIngredienteAgregableByIndex(ingredienteNum - 1);
                        } else if (item instanceof Postre) {
                            ingrediente = ((Postre) item).getIngredienteAgregableByIndex(ingredienteNum - 1);
                        }

                        if (ingrediente != null) {
                            item.agregarIngrediente(ingrediente.getNombre());
                            seModificaron = true;
                        } else {
                            System.out.println("Número de ingrediente inválido.");
                        }
                    }
                    break;
                case 2:
                    if (item instanceof EditarItem) {
                        limpiarPantalla();
                        if (item instanceof Hamburguesa) {
                            ((Hamburguesa) item).mostrarIngredientesActuales();
                        } else if (item instanceof Papas) {
                            ((Papas) item).mostrarIngredientesActuales();
                        } else if (item instanceof Postre) {
                            ((Postre) item).mostrarIngredientesActuales();
                        }

                        System.out.print("\nSeleccione el número del ingrediente a quitar: ");
                        int ingredienteNum = leerOpcion(scanner);

                        boolean quitado = false;
                        if (item instanceof Hamburguesa) {
                            quitado = ((Hamburguesa) item).quitarIngredientePorIndice(ingredienteNum - 1);
                        } else if (item instanceof Papas) {
                            quitado = ((Papas) item).quitarIngredientePorIndice(ingredienteNum - 1);
                        } else if (item instanceof Postre) {
                            quitado = ((Postre) item).quitarIngredientePorIndice(ingredienteNum - 1);
                        }

                        if (quitado) {
                            System.out.println("Ingrediente quitado exitosamente.");
                            seModificaron = true;
                        } else {
                            System.out.println("No se pudo quitar el ingrediente seleccionado.");
                        }
                    }
                    break;
                case 3:
                    if (item instanceof EditarItem) {
                        limpiarPantalla();
                        if (item instanceof Hamburguesa) {
                            ((Hamburguesa) item).mostrarIngredientesActuales();
                        } else if (item instanceof Papas) {
                            ((Papas) item).mostrarIngredientesActuales();
                        } else if (item instanceof Postre) {
                            ((Postre) item).mostrarIngredientesActuales();
                        }
                    }
                    break;
            }

            // Forzar el recálculo del precio si hubo cambios
            if (seModificaron && item instanceof Item) {
                if (item instanceof Hamburguesa) {
                    ((Hamburguesa) item).calcularGuardarPrecio();
                } else if (item instanceof Papas) {
                    ((Papas) item).calcularGuardarPrecio();
                } else if (item instanceof Postre) {
                    ((Postre) item).calcularGuardarPrecio();
                }
            }

            pausar();
        }
    }

    public void tomarPedido() {
        Scanner scanner = new Scanner(System.in);
        Pedido pedido = new Pedido(LocalDate.now());

        while (true) {
            limpiarPantalla();
            System.out.println("\n=== TOMAR PEDIDO ===");
            System.out.println("1. Agregar Combo");
            System.out.println("2. Agregar Item Individual");
            System.out.println("3. Ver Pedido Actual");
            System.out.println("4. Finalizar Pedido");
            System.out.println("0. Cancelar Pedido");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    agregarCombo(pedido, scanner);
                    break;
                case 2:
                    agregarItemIndividual(pedido, scanner);
                    break;
                case 3:
                    mostrarDetallePedido(pedido);
                    pausar();
                    break;
                case 4:
                    if (!pedido.getCombos().isEmpty() || !pedido.getItems().isEmpty()) {
                        double precioFinal = pedido.calcularPrecioFinal();
                        BD.agregarPedido(pedido);
                        System.out.println("Pedido tomado exitosamente con ID: " + pedido.getId());
                        System.out.println("Precio final del pedido: $" + precioFinal);
                        return;
                    } else {
                        System.out.println("El pedido está vacío.");
                        pausar();
                    }
                    break;
                case 0:
                    System.out.println("Pedido cancelado.");
                    return;
                default:
                    System.out.println("Opción no válida.");
                    pausar();
            }
            limpiarPantalla();
        }
    }

    private void mostrarDetallePedido(Pedido pedido) {
        limpiarPantalla();
        System.out.println("\n=== DETALLE DEL PEDIDO ACTUAL ===");
        System.out.println("ID: " + pedido.getId());
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.println("Estado: " + pedido.getEstado());

        if (!pedido.getCombos().isEmpty()) {
            System.out.println("\nCOMBOS:");
            for (Combo combo : pedido.getCombos()) {
                System.out.println("\n- " + combo.getNombre() + " ($" + String.format("%.2f", combo.getPrecio()) + ")");
                System.out.println("  Items del combo:");
                for (Item item : combo.getItems()) {
                    System.out.println("  * " + item.getNombre() + " ($" + String.format("%.2f", item.getPrecio()) + ")");
                    if (item instanceof EditarItem) {
                        mostrarIngredientesDeItem(item);
                    }
                }
            }
        }

        if (!pedido.getItems().isEmpty()) {
            System.out.println("\nITEMS INDIVIDUALES:");
            for (Item item : pedido.getItems()) {
                System.out.println("\n- " + item.getNombre() + " ($" + String.format("%.2f", item.getPrecio()) + ")");
                if (item instanceof EditarItem) {
                    mostrarIngredientesDeItem(item);
                }
            }
        }

        System.out.println("\nPrecio Total: $" + String.format("%.2f", pedido.calcularPrecioFinal()));
    }

    private void mostrarIngredientesDeItem(Item item) {
        System.out.println("    Ingredientes:");
        if (item instanceof Hamburguesa) {
            mostrarIngredientesAgrupados(((Hamburguesa) item).getIngredientesActualesList());
        } else if (item instanceof Papas) {
            mostrarIngredientesAgrupados(((Papas) item).getIngredientesActualesList());
        } else if (item instanceof Postre) {
            mostrarIngredientesAgrupados(((Postre) item).getIngredientesActualesList());
        }
    }

    private void mostrarIngredientesAgrupados(ArrayList<Ingrediente> ingredientes) {
        java.util.Map<String, Integer> contadorIngredientes = new java.util.HashMap<>();
        java.util.Map<String, Double> preciosIngredientes = new java.util.HashMap<>();

        for (Ingrediente ing : ingredientes) {
            contadorIngredientes.put(ing.getNombre(), contadorIngredientes.getOrDefault(ing.getNombre(), 0) + 1);
            preciosIngredientes.putIfAbsent(ing.getNombre(), ing.getPrecio());
        }

        for (java.util.Map.Entry<String, Integer> entry : contadorIngredientes.entrySet()) {
            String nombre = entry.getKey();
            int cantidad = entry.getValue();
            double precio = preciosIngredientes.get(nombre);

            if (cantidad > 1) {
                System.out.printf("    * %s x %d - $%.2f c/u%n", nombre, cantidad, precio);
            } else {
                System.out.printf("    * %s - $%.2f%n", nombre, precio);
            }
        }
    }

    private void agregarCombo(Pedido pedido, Scanner scanner) {
        while (true) {
            limpiarPantalla();
            BD.mostrarCombos();
            System.out.print("\nSeleccione el número del combo a agregar (0 para volver): ");

            int opcion = leerOpcion(scanner);
            if (opcion == 0) break;

            Combo combo = BD.getComboByIndex(opcion - 1);
            if (combo != null) {
                pedido.agregarCombo(combo);
                System.out.println("Combo agregado: " + combo.getNombre());
                gestionarIngredientesCombo(combo, scanner);
            } else {
                System.out.println("Número de combo no válido.");
            }
            pausar();
        }
    }

    private void agregarItemIndividual(Pedido pedido, Scanner scanner) {
        while (true) {
            limpiarPantalla();
            mostrarItems();
            System.out.print("\nSeleccione el número del item a agregar (0 para volver): ");

            int opcion = leerOpcion(scanner);
            if (opcion == 0) break;

            Item item = BD.getItemByIndex(opcion - 1);
            if (item != null) {
                if (item instanceof EditarItem) {
                    gestionarIngredientesItem((EditarItem) item, scanner);
                }
                pedido.agregarItem(item);
                System.out.println("Item agregado: " + item.getNombre());
            } else {
                System.out.println("Número de item no válido.");
            }
            pausar();
        }
    }

    private void gestionarIngredientesCombo(Combo combo, Scanner scanner) {
        while (true) {
            limpiarPantalla();
            System.out.println("\n=== GESTIONAR COMBO " + combo.getNombre().toUpperCase() + " ===");
            System.out.println("1. Modificar Items del combo");
            System.out.println("2. Ver detalle del combo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    modificarItemsCombo(combo, scanner);
                    break;
                case 2:
                    mostrarDetalleCombo(combo);
                    pausar();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opción no válida.");
                    pausar();
            }
        }
    }

    private void modificarItemsCombo(Combo combo, Scanner scanner) {
        while (true) {
            limpiarPantalla();
            System.out.println("\n=== MODIFICAR ITEMS DEL COMBO ===");
            mostrarItemsComboAgrupados(combo);
            System.out.println("\n0. Volver");
            System.out.print("Seleccione el item a modificar: ");

            int opcion = leerOpcion(scanner);
            if (opcion == 0) break;

            ArrayList<Item> itemsCombo = combo.getItems();
            if (opcion > 0 && opcion <= itemsCombo.size()) {
                Item item = itemsCombo.get(opcion - 1);
                if (item instanceof EditarItem) {
                    gestionarIngredientesItem((EditarItem) item, scanner);
                } else {
                    System.out.println("Este item no permite modificar ingredientes.");
                    pausar();
                }
            } else {
                System.out.println("Opción no válida.");
                pausar();
            }
        }
    }

    private void mostrarItemsComboAgrupados(Combo combo) {
        ArrayList<Item> items = combo.getItems();

        // Contadores para cada tipo de item
        java.util.Map<String, Integer> contadorActual = new java.util.HashMap<>();

        // Mostramos cada item individualmente con su número de orden dentro de su tipo
        int index = 1;
        for (Item item : items) {
            String nombre = item.getNombre();
            int total = contarItemsDelMismoTipo(items, nombre);

            // Incrementamos el contador para este tipo de item
            int actual = contadorActual.getOrDefault(nombre, 0) + 1;
            contadorActual.put(nombre, actual);

            // Formato: "1. Hamburguesa Clásica (1 de 4) - $2.50"
            System.out.printf("%d. %s (%d de %d) - $%.2f%n",
                index++, nombre, actual, total, item.getPrecio());
        }
    }

    private int contarItemsDelMismoTipo(ArrayList<Item> items, String nombre) {
        return (int) items.stream()
                .filter(item -> item.getNombre().equals(nombre))
                .count();
    }

    private void mostrarDetalleCombo(Combo combo) {
        limpiarPantalla();
        System.out.println("\n=== DETALLE DEL COMBO " + combo.getNombre().toUpperCase() + " ===");

        ArrayList<Item> items = combo.getItems();
        java.util.Map<String, Integer> contadorActual = new java.util.HashMap<>();
        double precioTotal = 0.0;

        System.out.println("\nItems del combo:");
        int index = 1;
        for (Item item : items) {
            String nombre = item.getNombre();
            int total = contarItemsDelMismoTipo(items, nombre);
            int actual = contadorActual.getOrDefault(nombre, 0) + 1;
            contadorActual.put(nombre, actual);

            System.out.printf("\n%d. %s (%d de %d) - $%.2f%n",
                index++, nombre, actual, total, item.getPrecio());

            if (item instanceof EditarItem) {
                mostrarIngredientesDeItem(item);
            }
            precioTotal += item.getPrecio();
        }

        System.out.println("\nSubtotal: $" + String.format("%.2f", precioTotal));
        System.out.println("Precio final del combo con descuento: $" + String.format("%.2f", combo.getPrecio()));
    }

    private void mostrarItems() {
        ArrayList<Item> items = BD.getItems();
        System.out.println("\n=== ITEMS DISPONIBLES ===");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getNombre() + " - $" + items.get(i).getPrecio());
        }
    }
}
