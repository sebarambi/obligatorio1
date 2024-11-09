package org.example.DAO;

import org.example.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private ConnectionDAO connectionDAO;

    public HabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public boolean insertHabitacion(Habitacion habitacion) {
        String query = "INSERT INTO Habitacion (idHabitacion, capacidadCamas, ocupada, camaDoble, idTipoHab, aireAcondicionado, balcon, idHotel) VALUES (?,?,?,?,?,?,?,?)";

        return connectionDAO.executeUpdate(query,
                habitacion.getIdHabitacion(),
                habitacion.getCapacidadCamas(),
                habitacion.isOcupada(),
                habitacion.isCamaDoble(),
                habitacion.getTipoHabitacion().getIdTipoHabitacion(),
                habitacion.isAireAcondicionado(),
                habitacion.isBalcon(),
                habitacion.getHotel().getIdHotel()
        );
    }

    public List<Habitacion> listarHabitacionesPorIdHotel(int idHotel) {
        String query = "SELECT h.idHabitacion, h.capacidadCamas, h.ocupada, h.camaDoble, " +
                "h.aireAcondicionado, h.balcon, th.idTipoHab, th.descripcion, " +
                "th.idTarifa, t.monto, ho.idHotel, ho.nombreHotel " +
                "FROM habitacion h " +
                "JOIN tipohabitacion th ON h.idTipoHab = th.idTipoHab " +
                "JOIN hotel ho ON h.idHotel = ho.idHotel " +
                "JOIN tarifa t ON th.idTarifa = t.idTarifa " +
                "WHERE ho.idHotel = " + idHotel + ";";

        List<Habitacion> habitaciones = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                // Recuperar datos de Habitacion
                int idHabitacion = resultSet.getInt("idHabitacion");
                int capacidadCamas = resultSet.getInt("capacidadCamas");
                boolean ocupada = resultSet.getBoolean("ocupada");
                boolean camaDoble = resultSet.getBoolean("camaDoble");
                boolean aireAcondicionado = resultSet.getBoolean("aireAcondicionado");
                boolean balcon = resultSet.getBoolean("balcon");

                // Recuperar datos de TipoHabitacion
                int idTipoHab = resultSet.getInt("idTipoHab");
                String descripcionTipoHab = resultSet.getString("descripcion");

                // Recuperar datos de Hotel
                int idHotelRecuperado = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombreHotel");

                // Recuperar datos de Tarifa
                int idTarifa = resultSet.getInt("idTarifa");
                int montoTarifa = resultSet.getInt("monto");

                // Crear el objeto Tarifa
                Tarifa tarifa = new Tarifa(idTarifa, montoTarifa);

                // Crear el objeto TipoHabitacion
                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHab, descripcionTipoHab, tarifa);

                // Crear el objeto Hotel
                Hotel hotel = new Hotel(idHotelRecuperado, nombreHotel);

                // Crear el objeto Habitacion y asignar los objetos relacionados
                Habitacion habitacion = new Habitacion(idHabitacion, capacidadCamas, ocupada, camaDoble,
                        tipoHabitacion, aireAcondicionado, balcon, hotel);
                habitaciones.add(habitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return habitaciones;
    }

}
