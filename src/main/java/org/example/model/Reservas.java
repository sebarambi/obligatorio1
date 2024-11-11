package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservas {
    private int idReserva;
    private Huesped huesped;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Habitacion> habitacionList;
    private int cantidadPersonas;
    private String observaciones;
    private Date fechaReserva;


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

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    //Constructor


    public Reservas(Huesped huesped, Date fechaInicio, Date fechaFin, int cantidadPersonas, String observaciones) {
        this.huesped = huesped;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersonas = cantidadPersonas;
        this.observaciones = observaciones;
        this.fechaReserva = new Date();  // Esto obtiene la fecha y hora actuales
    }

    public Reservas(int idReserva, Huesped huesped, Date fechaInicio, Date fechaFin, int cantidadPersonas, String observaciones, Date fechaReserva) {
        this.idReserva = idReserva;
        this.huesped = huesped;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersonas = cantidadPersonas;
        this.observaciones = observaciones;
        this.fechaReserva = fechaReserva;
    }
}
