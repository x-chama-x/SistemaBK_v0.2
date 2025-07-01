package com.sistemaBK.usuarios;
public abstract class Usuario {
    private String nombre;
    protected String username;
    protected String password;
    private int id;

    public Usuario(String nombre, String username, String password, int id) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.id = id;

    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return STR."Usuario{nombre='\{nombre}', id=\{id}, username='\{username}', password='\{password}'}";
    }
}
