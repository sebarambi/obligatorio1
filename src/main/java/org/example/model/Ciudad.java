package org.example.model;

public class Ciudad {
    private int idCiudad;
    private String nombreCiudad;
    private Pais pais;

    //Getters and Setters
    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    //Constructor

    public Ciudad(int idCiudad, String nombreCiudad, Pais pais) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.pais = pais;
    }

    public Ciudad(int idCiudad, String nombreCiudad) {
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
    }
}
