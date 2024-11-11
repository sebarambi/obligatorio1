package org.example.controller;

import org.example.DAO.ReservasDAO;
import org.example.model.Habitacion;
import org.example.model.Reservas;

import java.util.List;

public class ReservasController {
    private ReservasDAO reservasDAO;


    public ReservasController() {
        reservasDAO = new ReservasDAO();
    }

    public boolean insertarReserva(Reservas reserva) {
        return reservasDAO.insertReserva(reserva);
    }

    public Reservas obtenerReservaPorId(int idReserva) {
        return reservasDAO.getReservaById(idReserva);
    }

    public List<Reservas> obtenerReservasPorIdHuesped(int idHuesped) {
        return reservasDAO.getReservasByIdHuesped(idHuesped);
    }

    public boolean modificarReserva(Reservas reserva) {
        return reservasDAO.modificarReserva(reserva);
    }

    public boolean eliminarReserva(int idReserva) {
        Reservas reserva = obtenerReservaPorId(idReserva);

        if (reserva != null) {
            return reservasDAO.eliminarReserva(idReserva);
        } else {
            System.out.println("No se encontró la habitación con el ID especificado.");
            return false;
        }
    }
}
