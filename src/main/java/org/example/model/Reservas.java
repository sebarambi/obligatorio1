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
    private int cantidadPersonas;
    private String observaciones;
    private Date fechaReserva;
    private Habitacion habitacion;
    private int precioTotal;


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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }
//Constructor


    public Reservas(Huesped huesped, Date fechaInicio, Date fechaFin, int cantidadPersonas, String observaciones, Habitacion habitacion) {
        this.huesped = huesped;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersonas = cantidadPersonas;
        this.observaciones = observaciones;
        this.fechaReserva = new Date();
        this.habitacion = habitacion;
        this.precioTotal = calcularPrecioTotal();
    }

    public Reservas(int idReserva, Huesped huesped, Habitacion habitacion, Date fechaInicio, Date fechaFin, int cantidadPersonas, String observaciones, int precioTotal, Date fechaReserva) {
        this.idReserva = idReserva;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersonas = cantidadPersonas;
        this.observaciones = observaciones;
        this.precioTotal = precioTotal;
        this.fechaReserva = fechaReserva;
    }


    //Metodos
    public int calcularDiasEstancia() {
        long diferenciaMillis = fechaFin.getTime() - fechaInicio.getTime();

        return (int) (diferenciaMillis / (1000 * 60 * 60 * 24)); // 1000ms * 60s * 60m * 24h
    }

    private int calcularPrecioTotal() {
        int diasEstancia = calcularDiasEstancia();
        int montoTarifa = this.habitacion.getTipoHabitacion().getTarifa().getMonto();
        return diasEstancia * montoTarifa;
    }
}
