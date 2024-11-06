package org.example.model;

public class HabitacionReservas {
    private int idHabitacion;
    private int idReserva;

    //Getters and Setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    //Constructor

    public HabitacionReservas(int idHabitacion, int idReserva) {
        this.idHabitacion = idHabitacion;
        this.idReserva = idReserva;
    }
}
