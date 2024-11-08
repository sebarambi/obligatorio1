package org.example.model;

public class Tarifa {
    private int idTarifa;
    private int monto;

    //Getters and Setters
    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    //Constructor
    public Tarifa(int idTarifa, int monto) {
        this.idTarifa = idTarifa;
        this.monto = monto;
    }
}
