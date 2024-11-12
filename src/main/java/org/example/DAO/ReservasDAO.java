package org.example.DAO;

import org.example.controller.HabitacionController;
import org.example.controller.HuespedController;
import org.example.controller.ReservasController;
import org.example.model.Habitacion;
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
    private HabitacionController habitacionController;


    public ReservasDAO() {
        this.connectionDAO = new ConnectionDAO();
        this.huespedController = new HuespedController();
        this.habitacionController = new HabitacionController();
    }

    public boolean insertReserva(Reservas reserva) {
        String query = "INSERT INTO Reservas (idHuesped, fechaInicio,  fechaFin, cantidadPersonas, observaciones, fechaReserva,idHabitacion ,precioTotal) VALUES (?,?,?,?,?,?,?,?)";

        return connectionDAO.executeUpdate(query,
                reserva.getHuesped().getIdHuesped(),
                reserva.getFechaInicio(),
                reserva.getFechaFin(),
                reserva.getCantidadPersonas(),
                reserva.getObservaciones(),
                reserva.getFechaReserva(),
                reserva.getHabitacion().getIdHabitacion(),
                reserva.getPrecioTotal()
        );
    }


    public boolean eliminarReserva(int idReserva) {
        String query = "DELETE FROM Reservas WHERE idReserva = ?";

        return connectionDAO.executeUpdate(query, idReserva);
    }


    public Reservas getReservaById(int idReserva) {
        String query = "SELECT r.idReserva, r.idHuesped, r.idHabitacion, r.fechaInicio, r.fechaFin, r.cantidadPersonas, r.observaciones, r.precioTotal, r.fechaReserva " +
                "FROM Reservas r " +
                "WHERE r.idReserva = ?";

        Reservas reserva = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idReserva);

            if (resultSet.next()) {
                int id = resultSet.getInt("idReserva");
                int idHuesped = resultSet.getInt("idHuesped");
                int idHabitacion = resultSet.getInt("idHabitacion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                String observaciones = resultSet.getString("observaciones");
                int precioTotal = resultSet.getInt("precioTotal");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                Huesped huesped = huespedController.getHuespedById(idHuesped);
                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);

                reserva = new Reservas(id, huesped, habitacion, fechaInicio, fechaFin, cantidadPersonas, observaciones, precioTotal, fechaReserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reserva;
    }


    public List<Reservas> getReservasByIdHuesped(int idHuesped) {
        String query = "SELECT r.idReserva, r.idHuesped, r.idHabitacion, r.fechaInicio, r.fechaFin, " +
                "r.cantidadPersonas, r.observaciones, r.precioTotal, r.fechaReserva " +
                "FROM Reservas r " +
                "WHERE r.idHuesped = ?";

        List<Reservas> reservasList = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idHuesped);

            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                int idHabitacion = resultSet.getInt("idHabitacion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFin = resultSet.getDate("fechaFin");
                int cantidadPersonas = resultSet.getInt("cantidadPersonas");
                String observaciones = resultSet.getString("observaciones");
                int precioTotal = resultSet.getInt("precioTotal");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                // Obtener el Huesped
                Huesped huesped = huespedController.getHuespedById(idHuesped);

                // Obtener la Habitacion
                Habitacion habitacion = habitacionController.getHabitacionById(idHabitacion);

                // Crear la reserva con todos los datos
                Reservas reserva = new Reservas(idReserva, huesped, habitacion, fechaInicio, fechaFin,
                        cantidadPersonas, observaciones, precioTotal, fechaReserva);
                reservasList.add(reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reservasList;
    }


}
