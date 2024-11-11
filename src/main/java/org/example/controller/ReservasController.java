package org.example.controller;

import org.example.DAO.ReservasDAO;
import org.example.model.Reservas;

import java.util.List;

public class ReservasController {
    // Ya se instancia en la declaración de la variable
    private ReservasDAO reservasDAO;

    // Constructor sin la instancia redundante de ReservasDAO
    public ReservasController() {
        // La instanciación debería hacerse solo una vez, y puede pasarse por un constructor o configurarse en otro lugar si es necesario
        reservasDAO = new ReservasDAO();
    }

    // Método para insertar una nueva reserva
    public boolean insertarReserva(Reservas reserva) {
        return reservasDAO.insertReserva(reserva);
    }

    // Obtener una reserva por ID
    public Reservas obtenerReservaPorId(int idReserva) {
        return reservasDAO.getReservaById(idReserva);
    }

    // Obtener todas las reservas de un huésped por ID
    public List<Reservas> obtenerReservasPorIdHuesped(int idHuesped) {
        return reservasDAO.getReservasByIdHuesped(idHuesped);
    }

    // Modificar una reserva
    public boolean modificarReserva(Reservas reserva) {
        return reservasDAO.modificarReserva(reserva);  // Delegamos al DAO para hacer el update
    }
}
