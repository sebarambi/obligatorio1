package org.example.model;

import java.util.Date;

public class Huesped {
    private int idHuesped;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private TipoDocumento tipoDocumento;
    private String numDocumento;
    private Date fechaNacimiento;
    private String telefono;
    private Pais pais;


    //Getters and Setters
    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Huesped(int idHuesped, String nombre, String aPaterno, String aMaterno, TipoDocumento tipoDocumento, String numDocumento, Date fechaNacimiento, String telefono, Pais pais) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.pais = pais;
    }

    public Huesped() {
    }
}
