package com.sistemaBK.menu;

import com.sistemaBK.usuarios.*;

import java.util.HashMap;
import java.util.Map;

public class MenuStrategyHandler {
    private static final Map<Class<? extends Usuario>, MenuStrategy> menuStrategies = new HashMap<>();

    static {
        menuStrategies.put(Gerente.class, new GerenteMenuStrategy());
        menuStrategies.put(Vendedor.class, new VendedorMenuStrategy());
        menuStrategies.put(Cocinero.class, new CocineroMenuStrategy());
        menuStrategies.put(Inspector.class, new InspectorMenuStrategy());
    }

    public static void mostrarMenuPara(Usuario usuario) {
        MenuStrategy strategy = menuStrategies.get(usuario.getClass());
        if (strategy != null) {
            strategy.mostrarMenu(usuario);
        } else {
            System.out.println("No hay un menú disponible para este tipo de usuario.");
        }
    }

    // Método para agregar nuevas estrategias de menú en tiempo de ejecución
    public static void agregarMenuStrategy(Class<? extends Usuario> userClass, MenuStrategy strategy) {
        menuStrategies.put(userClass, strategy);
    }
}
