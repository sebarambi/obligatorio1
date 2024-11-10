package org.example.model;

import java.util.ArrayList;
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
        if (habitaciones == null) {
            return new ArrayList<>();
        }
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

    public Hotel(int idHotel, String nombreHotel, Pais pais, Ciudad ciudad, int cantidadEstrellas, String direccion, List<Habitacion> habitaciones) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cantidadEstrellas = cantidadEstrellas;
        this.direccion = direccion;
        this.habitaciones = habitaciones;
    }

    public Hotel(int idHotel, String nombreHotel) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
    }

    public void agregarHabitacion(Habitacion habitacion) {
        this.habitaciones.add(habitacion);
    }

    public void mostrarInformacion() {
        System.out.println("ID: " + getIdHotel());
        System.out.println("Nombre: " + getNombreHotel());
        System.out.println("Pais: " + getPais().getName());
        System.out.println("Ciudad: " + getCiudad().getNombreCiudad());
        System.out.println("Cantidad de estrellas: " + getCantidadEstrellas());
        System.out.println("Direccion: " + getDireccion());
        System.out.println();
        if (getHabitaciones().isEmpty()) {
            System.out.println("El hotel todavia no cuenta con habitaciones cargadas");
        } else {
            System.out.println("Las habitaciones del hotel son: ");
            for (Habitacion habitacion : getHabitaciones()) {
                System.out.println("---------------------------------------------------------------------");
                habitacion.mostrarInformacion();
                System.out.println("---------------------------------------------------------------------");

            }
        }
    }

    public void mostrarInformacionSinHab() {
        System.out.println("ID: " + getIdHotel());
        System.out.println("Nombre: " + getNombreHotel());
        System.out.println("Pais: " + getPais().getName());
        System.out.println("Ciudad: " + getCiudad().getNombreCiudad());
        System.out.println("Cantidad de estrellas: " + getCantidadEstrellas());
        System.out.println("Direccion: " + getDireccion());
        System.out.println("<====================================================================>");

    }
}

