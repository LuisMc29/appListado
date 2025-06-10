package com.example.listadotareas.home;

public class item {

    private String name;
    private String descripcion;
    private String fecha;

    public item(String name, String descripcion, String fecha) {
        this.name = name;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
