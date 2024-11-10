package org.example.controller;

import org.example.DAO.HabitacionDAO;
import org.example.DAO.HuespedDAO;
import org.example.model.Habitacion;
import org.example.model.Huesped;
import org.example.view.HabitacionView;

import java.util.Date;
import java.util.List;

public class HabitacionController {
    private HabitacionDAO habitacionDAO = new HabitacionDAO();

    public HabitacionController() {

    }

    public boolean insertHabitacion(Habitacion habitacion) {
        return this.habitacionDAO.insertHabitacion(habitacion);
    }

    public List<Habitacion> getAllHabitaciones() {
        return habitacionDAO.getAllHabitaciones();
    }

    public Habitacion getHabitacionById(int idHabitacion) {
        return habitacionDAO.getHabitacionById(idHabitacion);
    }

    public boolean modificarHabitacion(Habitacion habitacion) {
        return habitacionDAO.modificarHabitacion(habitacion);
    }

    public boolean eliminarHabitacion(int idHabitacion) {
        Habitacion habitacion = getHabitacionById(idHabitacion);

        if (habitacion != null) {
            return habitacionDAO.eliminarHabitacion(idHabitacion);
        } else {
            System.out.println("No se encontró la habitación con el ID especificado.");
            return false;
        }
    }

    public List<Habitacion> listarHabitacionesDisponibles(Date fechaInicio, Date fechaFin) {
        // Llamamos al DAO para obtener las habitaciones disponibles
        List<Habitacion> habitacionesDisponibles = habitacionDAO.getHabitacionesDisponibles(fechaInicio, fechaFin);
        return habitacionesDisponibles;
    }
}
