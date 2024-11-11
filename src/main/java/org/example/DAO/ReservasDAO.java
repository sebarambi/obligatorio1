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
                reserva.getHuesped().getIdHuesped(),  // El ID del huésped es necesario para la condición WHERE
                reserva.getIdReserva()  // El ID de la reserva es necesario para la condición WHERE
        );
    }


    public Reservas getReservaById(int idReserva) {
        String query = "SELECT r.idReserva, r.idHuesped, r.fechaInicio, r.fechaFin, r.cantidadPersonas, r.observaciones, r.fechaReserva " +
                "FROM Reservas r " +
                "WHERE r.idReserva = ?";


        Reservas reserva = null;

        try {
            // Ejecutar la consulta y obtener el ResultSet
            ResultSet resultSet = connectionDAO.executeQuery(query, idReserva);

            // Si hay un resultado, lo asignamos al objeto reserva
            if (resultSet.next()) {
                int id = resultSet.getInt("idReserva");
                int idHuesped = resultSet.getInt("idHuesped");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                String observaciones = resultSet.getString("observaciones");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                // Obtener el Huesped usando el idHuesped
                Huesped huesped = huespedController.getHuespedById(idHuesped);

                // Crear el objeto Reserva con los valores obtenidos
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
            // Ejecutar la consulta y obtener el ResultSet
            ResultSet resultSet = connectionDAO.executeQuery(query, idHuesped);

            // Iterar sobre los resultados y añadir cada reserva a la lista
            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                String observaciones = resultSet.getString("observaciones");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                // Obtener el Huesped usando el idHuesped
                Huesped huesped = huespedController.getHuespedById(idHuesped);

                // Crear el objeto Reserva con los valores obtenidos y añadirlo a la lista
                Reservas reserva = new Reservas(idReserva,huesped,fechaInicio,fechaFin,cantidadPersonas,observaciones,fechaReserva);
                reservasList.add(reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reservasList;
    }


}
