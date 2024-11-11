package org.example.model;


import java.util.Date;

public class Ocupaciones {
    private int idOcupacion;
    private Habitacion habitacion;
    private Reservas reservas;
    private Huesped huesped;
    private Date fechaEntrada;
    private Date fechaSalida;
    private boolean reservadoSistema;
    private int precio;

    //Getters and Setters
    public int getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(int idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Reservas getReservas() {
        return reservas;
    }

    public void setReservas(Reservas reservas) {
        this.reservas = reservas;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaENtrada) {
        this.fechaEntrada = fechaENtrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isReservadoSistema() {
        return reservadoSistema;
    }

    public void setReservadoSistema(boolean reservadoSistema) {
        this.reservadoSistema = reservadoSistema;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    //Constructor
    public Ocupaciones(int idOcupacion, Habitacion habitacion, Reservas reservas, Huesped huesped, Date fechaENtrada, Date fechaSalida, boolean reservadoSistema, int precio) {
        this.idOcupacion = idOcupacion;
        this.habitacion = habitacion;
        this.reservas = reservas;
        this.huesped = huesped;
        this.fechaEntrada = fechaENtrada;
        this.fechaSalida = fechaSalida;
        this.reservadoSistema = reservadoSistema;
        this.precio = calcularPrecioTotal();
    }

    public int calcularDiasEstancia() {
        // Calcular la diferencia en milisegundos
        long diferenciaMillis = fechaSalida.getTime() - fechaEntrada.getTime();

        // Convertir milisegundos a días y redondear el valor a int
        return (int) (diferenciaMillis / (1000 * 60 * 60 * 24)); // 1000ms * 60s * 60m * 24h
    }
    private int calcularPrecioTotal() {
        int diasEstancia = calcularDiasEstancia();
        int montoTarifa = this.habitacion.getTipoHabitacion().getTarifa().getMonto();
        return diasEstancia * montoTarifa;
    }
}
