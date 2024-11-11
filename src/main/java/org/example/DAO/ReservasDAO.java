package org.example.DAO;

import org.example.controller.HuespedController;
import org.example.controller.ReservasController;
import org.example.model.Huesped;
import org.example.model.Reservas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservasDAO {
    private ConnectionDAO connectionDAO;

    private HuespedController huespedController;


    public ReservasDAO() {
        this.connectionDAO = new ConnectionDAO();
        this.huespedController = new HuespedController();
    }

    public boolean insertReserva(Reservas reserva) {
        String query = "INSERT INTO Reservas (idHuesped, fechaInicio, fechaFin, cantidadPersonas) VALUES (?,?,?,?)";

        return connectionDAO.executeUpdate(query,
                reserva.getHuesped().getIdHuesped(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reserva.getCantidadPersonas()

        );
    }
    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM Reservas WHERE idReserva = ?";

        return connectionDAO.executeUpdate(query, idReserva);
    }

    public boolean modificarReserva(Reservas reserva) {
        String query = "UPDATE Reservas SET fechaInicio = ?, fechaFin = ?, " +
                "cantidadPersonas = ?, observaciones = ?, idHuesped = ? " +
                "WHERE idReserva = ?";

        return connectionDAO.executeUpdate(query,
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reserva.getCantidadPersonas(),
                reserva.getObservaciones(),
                reserva.getHuesped().getIdHuesped(),
                reserva.getIdReserva()
        );
    }


    public Reservas getReservaById(int idReserva) {
        String query = "SELECT r.idReserva, r.idHuesped, r.fechaInicio, r.fechaFin, r.cantidadPersonas, r.observaciones, r.fechaReserva " +
                "FROM Reservas r " +
                "WHERE r.idReserva = ?";


        Reservas reserva = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idReserva);

            if (resultSet.next()) {
                int id = resultSet.getInt("idReserva");
                int idHuesped = resultSet.getInt("idHuesped");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                String observaciones = resultSet.getString("observaciones");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                Huesped huesped = huespedController.getHuespedById(idHuesped);

                reserva = new Reservas(huesped, fechaInicio, fechaFin, cantidadPersonas, observaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reserva;
    }

    public List<Reservas> getReservasByIdHuesped(int idHuesped) {
        String query = "SELECT r.idReserva, r.idHuesped, r.fechaInicio, r.fechaFin, " +
                "r.cantidadPersonas, r.observaciones, r.fechaReserva " +
                "FROM Reservas r " +
                "WHERE r.idHuesped = ?";

        List<Reservas> reservasList = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idHuesped);

            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                String observaciones = resultSet.getString("observaciones");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                Huesped huesped = huespedController.getHuespedById(idHuesped);

                Reservas reserva = new Reservas(idReserva,huesped,fechaInicio,fechaFin,cantidadPersonas,observaciones,fechaReserva);
                reservasList.add(reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reservasList;
    }


}
