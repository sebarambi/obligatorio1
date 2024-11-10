package org.example.model;

public class Habitacion {
    private int idHabitacion;
    private int capacidadCamas;
    private boolean ocupada;
    private boolean camaDoble;
    private TipoHabitacion tipoHabitacion;
    private boolean aireAcondicionado;
    private boolean balcon;
    private Hotel hotel;

    //Getters and Setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getCapacidadCamas() {
        return capacidadCamas;
    }

    public void setCapacidadCamas(int capacidadCamas) {
        this.capacidadCamas = capacidadCamas;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean isCamaDoble() {
        return camaDoble;
    }

    public void setCamaDoble(boolean camaDoble) {
        this.camaDoble = camaDoble;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }
    //Constructor


    public Habitacion(int idHabitacion, int capacidadCamas, boolean ocupada, boolean camaDoble, TipoHabitacion tipoHabitacion, boolean aireAcondicionado, boolean balcon, Hotel hotel) {
        this.idHabitacion = idHabitacion;
        this.capacidadCamas = capacidadCamas;
        this.ocupada = ocupada;
        this.camaDoble = camaDoble;
        this.tipoHabitacion = tipoHabitacion;
        this.aireAcondicionado = aireAcondicionado;
        this.balcon = balcon;
        this.hotel = hotel;
    }

    //Metodos
    public void mostrarInformacion() {
        System.out.println("ID de Habitacion: " + getIdHabitacion());
        System.out.println("Hotel: " + getHotel().getNombreHotel());
        System.out.println("Capacidad de camas: " + getCapacidadCamas());
        System.out.println("Esta ocupada actualmente: " + (isOcupada() ? "Sí" : "No"));
        System.out.println("Cama doble: " + (isCamaDoble() ? "Sí" : "No"));
        System.out.println("Tipo de habitacion: " + getTipoHabitacion().getDescripcion());
        System.out.println("Aire Acondicionado: " + (isAireAcondicionado() ? "Sí" : "No"));
        System.out.println("Balcon: " + (isBalcon() ? "Sí" : "No"));
    }
}
