package org.example.controller;

import org.example.DAO.ReservasDAO;

public class ReservasController {
    private ReservasDAO reservasDAO = new ReservasDAO();

    public ReservasController() {
        this.reservasDAO = new ReservasDAO();
    }

}
