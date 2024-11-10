package org.example.controller;

import org.example.DAO.ReservasDAO;
import org.example.model.Hotel;
import org.example.model.Reservas;

public class ReservasController {
    private ReservasDAO reservasDAO = new ReservasDAO();

    public ReservasController() {
        this.reservasDAO = new ReservasDAO();
    }
    public boolean insertarReserva(Reservas reservas) {
        return this.reservasDAO.insertReserva(reservas);
    }
}
