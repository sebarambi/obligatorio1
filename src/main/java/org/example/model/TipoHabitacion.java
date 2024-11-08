package org.example.model;

public class TipoHabitacion {
    private int idTipoHabitacion;
    private String descripcion;
    private Tarifa tarifa;

    //Getters and Setters
    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Constructor

    public TipoHabitacion(int idTipoHabitacion, String descripcion, Tarifa tarifa) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.descripcion = descripcion;
        this.tarifa = tarifa;
    }

    public TipoHabitacion(int idTipoHabitacion, String descripcion) {
        this.idTipoHabitacion = idTipoHabitacion;
        this.descripcion = descripcion;
    }
}
