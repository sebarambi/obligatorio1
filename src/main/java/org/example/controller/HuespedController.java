package org.example.controller;

import org.example.DAO.HuespedDAO;
import org.example.model.Huesped;

public class HuespedController {
    private HuespedDAO huespedDAO = new HuespedDAO();

    public HuespedController() {

    }

    public Huesped getHuespedById(int idHuesped) {
        return this.huespedDAO.getHuespedById(idHuesped);
    }

    public boolean insertHuesped(Huesped huesped) {
        return this.huespedDAO.insertHuesped(huesped);
    }
}
