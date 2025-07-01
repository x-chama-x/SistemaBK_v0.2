package com.sistemaBK;

public class Main {
    public static void main(String[] args) {

        // Inicializar la base de datos
        BD bd = new BD();

        // Inicializar el sistema (esto creará usuarios, items y combos)
        Sistema s= new Sistema();
        s.iniciar(); // Inicia el menú principal del sistema
    }
}