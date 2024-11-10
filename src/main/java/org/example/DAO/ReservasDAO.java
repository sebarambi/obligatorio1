package org.example.DAO;

import org.example.controller.ReservasController;
import org.example.model.Reservas;

public class ReservasDAO {
    private ConnectionDAO connectionDAO;
    private ReservasController reservasController;

    public ReservasDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public boolean insertReserva(Reservas reserva) {
        String query = "INSERT INTO Reservas (idHuesped, fechaInicio, fechaFin) VALUES (?,?,?)";

        return connectionDAO.executeUpdate(query,
                reserva.getHuesped().getIdHuesped(),
                reserva.getFechaInicio(),
                reserva.getFechaFin()
        );
    }

}
