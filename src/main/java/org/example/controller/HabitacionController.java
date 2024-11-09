package org.example.controller;

import org.example.DAO.HabitacionDAO;
import org.example.DAO.HuespedDAO;
import org.example.model.Habitacion;
import org.example.model.Huesped;
import org.example.view.HabitacionView;

public class HabitacionController {
    private HabitacionDAO habitacionDAO = new HabitacionDAO();

    public HabitacionController() {

    }

    public boolean insertHabitacion(Habitacion habitacion) {
        return this.habitacionDAO.insertHabitacion(habitacion);
    }
}
