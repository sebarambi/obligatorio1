package org.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservas {
    private int idReserva;
    private Huesped huesped;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Habitacion> habitacionList;

    //Getters and Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Habitacion> getHabitacionList() {
        return habitacionList;
    }

    public void setHabitacionList(List<Habitacion> habitacionList) {
        this.habitacionList = habitacionList;
    }
    //Constructor


    public Reservas( Huesped huesped, Date fechaInicio, Date fechaFin) {
        this.huesped = huesped;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.habitacionList = new ArrayList<>();
    }
}
