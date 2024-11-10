package org.example.controller;

import org.example.DAO.TipoHabitacionDAO;
import org.example.model.TipoHabitacion;

public class TipoHabitacionController {
    private TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

    public TipoHabitacionController() {

    }

    public TipoHabitacion getTipoHabitacionById(int idTipoHabitacion) {
        return tipoHabitacionDAO.getTipoHabitacionById(idTipoHabitacion);
    }
}
