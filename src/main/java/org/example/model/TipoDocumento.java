package org.example.model;

public class TipoDocumento {
    private int idTipoDoc ;
    private String nombre ;

    //Getters and Setters
    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Constructor

    public TipoDocumento(int idTipoDoc, String nombre) {
        this.idTipoDoc = idTipoDoc;
        this.nombre = nombre;
    }
}
