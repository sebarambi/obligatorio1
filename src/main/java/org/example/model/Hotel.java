package org.example.model;

import java.util.List;

public class Hotel {
    private int idHotel;
    private String nombreHotel;
    private Pais pais;
    private Ciudad ciudad;
    private int cantidadEstrellas;
    private List<Habitacion> habitaciones;
    private String direccion;

    //Getters and Setters
    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getCantidadEstrellas() {
        return cantidadEstrellas;
    }

    public void setCantidadEstrellas(int cantidadEstrellas) {
        this.cantidadEstrellas = cantidadEstrellas;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //Constructor

    public Hotel(int idHotel, String nombreHotel, Pais pais, Ciudad ciudad, int cantidadEstrellas, String direccion) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cantidadEstrellas = cantidadEstrellas;
        this.direccion = direccion;
        habitaciones = null;
    }


    public void agregarHabitacion(Habitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    public String mostrarInformacion() {
        return "Hotel{" +
                "ID=" + idHotel +
                ", Nombre='" + nombreHotel + '\'' +
                ", País=" + pais.getName() +
                ", Ciudad=" + ciudad.getNombreCiudad() +
                ", Estrellas=" + cantidadEstrellas +
                ", Dirección='" + direccion + '\'' +
                '}';
    }
}
