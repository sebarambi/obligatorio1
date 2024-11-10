package org.example.DAO;

import org.example.controller.HotelController;
import org.example.controller.TipoHabitacionController;
import org.example.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitacionDAO {
    private ConnectionDAO connectionDAO;
    private TipoHabitacionController tipoHabitacionController;
    private HotelController hotelController;

    public HabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
        this.tipoHabitacionController = new TipoHabitacionController();
        this.hotelController = new HotelController();
    }

    public boolean insertHabitacion(Habitacion habitacion) {
        String query = "INSERT INTO Habitacion (idHabitacion, capacidadCamas, camaDoble, idTipoHab, aireAcondicionado, balcon, idHotel) VALUES (?,?,?,?,?,?,?)";

        return connectionDAO.executeUpdate(query,
                habitacion.getIdHabitacion(),
                habitacion.getCapacidadCamas(),
                habitacion.isCamaDoble(),
                habitacion.getTipoHabitacion().getIdTipoHabitacion(),
                habitacion.isAireAcondicionado(),
                habitacion.isBalcon(),
                habitacion.getHotel().getIdHotel()
        );
    }

    public boolean modificarHabitacion(Habitacion habitacion) {
        String query = "UPDATE Habitacion SET capacidadCamas = ?, camaDoble = ?, " +
                "idTipoHab = ?, aireAcondicionado = ?, balcon = ?, idHotel = ? " +
                "WHERE idHabitacion = ?";

        return connectionDAO.executeUpdate(query,
                habitacion.getCapacidadCamas(),
                habitacion.isCamaDoble(),
                habitacion.getTipoHabitacion().getIdTipoHabitacion(),
                habitacion.isAireAcondicionado(),
                habitacion.isBalcon(),
                habitacion.getHotel().getIdHotel(),
                habitacion.getIdHabitacion()  // El ID de la habitación es necesario para la condición WHERE
        );
    }

    public boolean eliminarHabitacion(int idHabitacion) {
        String query = "DELETE FROM Habitacion WHERE idHabitacion = ?";

        return connectionDAO.executeUpdate(query, idHabitacion);
    }


    public List<Habitacion> getAllHabitaciones() {
        String query = "SELECT idHabitacion, capacidadCamas, camaDoble, idTipoHab, aireAcondicionado, balcon, idHotel FROM Habitacion";
        List<Habitacion> habitaciones = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int idHabitacion = resultSet.getInt("idHabitacion");
                int capacidadCamas = resultSet.getInt("capacidadCamas");
                boolean camaDoble = resultSet.getBoolean("camaDoble");
                boolean aireAcondicionado = resultSet.getBoolean("aireAcondicionado");
                boolean balcon = resultSet.getBoolean("balcon");
                int idTipoHab = resultSet.getInt("idTipoHab");
                int idHotel = resultSet.getInt("idHotel");

                // Obtener los detalles completos de TipoHabitacion y Hotel desde las controladoras
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHab);
                Hotel hotel = hotelController.getHotelById(idHotel);

                // Crear el objeto Habitacion
                Habitacion habitacion = new Habitacion(
                        idHabitacion,
                        capacidadCamas,
                        camaDoble,
                        tipoHabitacion,
                        aireAcondicionado,
                        balcon,
                        hotel
                );
                habitaciones.add(habitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return habitaciones;
    }

    public Habitacion getHabitacionById(int idHabitacion) {
        String query = "SELECT h.idHabitacion, h.capacidadCamas, h.camaDoble, " +
                "h.aireAcondicionado, h.balcon, h.idTipoHab, h.idHotel " +
                "FROM Habitacion h " +
                "WHERE h.idHabitacion = ?";

        Habitacion habitacion = null;

        try {
            // Ejecutar la consulta y obtener el ResultSet
            ResultSet resultSet = connectionDAO.executeQuery(query, idHabitacion);

            // Si hay un resultado, lo asignamos al objeto habitacion
            if (resultSet.next()) {
                int id = resultSet.getInt("idHabitacion");
                int capacidadCamas = resultSet.getInt("capacidadCamas");
                boolean camaDoble = resultSet.getBoolean("camaDoble");
                boolean aireAcondicionado = resultSet.getBoolean("aireAcondicionado");
                boolean balcon = resultSet.getBoolean("balcon");

                // Obtener el TipoHabitacion usando el idTipoHab
                int idTipoHab = resultSet.getInt("idTipoHab");
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHab);

                // Obtener el Hotel usando el idHotel
                int idHotel = resultSet.getInt("idHotel");
                Hotel hotel = hotelController.getHotelById(idHotel);

                // Crear el objeto Habitacion con los valores obtenidos
                habitacion = new Habitacion(id, capacidadCamas, camaDoble, tipoHabitacion, aireAcondicionado, balcon, hotel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return habitacion;
    }


    public List<Habitacion> listarHabitacionesPorIdHotel(int idHotel) {
        String query = "SELECT h.idHabitacion, h.capacidadCamas, h.camaDoble, " +
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
                Habitacion habitacion = new Habitacion(idHabitacion, capacidadCamas, camaDoble,
                        tipoHabitacion, aireAcondicionado, balcon, hotel);
                habitaciones.add(habitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return habitaciones;
    }

    public List<Habitacion> getHabitacionesDisponibles(Date fechaInicio, Date fechaFin) {
        // Asegurarnos de que las fechas están correctamente formateadas
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaInicioStr = formato.format(fechaInicio);
        String fechaFinStr = formato.format(fechaFin);

        // Consulta SQL
        String query = "SELECT h.idHabitacion, h.capacidadCamas, h.camaDoble, h.aireAcondicionado, " +
                "h.balcon, h.idTipoHab, h.idHotel " +
                "FROM Habitacion h " +
                "WHERE h.idHabitacion NOT IN (" +
                "    SELECT hr.idHabitacion " +
                "    FROM HabitacionReserva hr " +
                "    INNER JOIN Reservas r ON hr.idReserva = r.idReserva " +
                "    WHERE (r.fechaInicio BETWEEN ? AND ? OR r.fechaFin BETWEEN ? AND ?) " +
                ") AND h.idHabitacion NOT IN (" +
                "    SELECT o.idHabitacion " +
                "    FROM Ocupaciones o " +
                "    WHERE (o.fecha_entrada BETWEEN ? AND ? OR o.fecha_salida BETWEEN ? AND ?) " +
                ")";

        List<Habitacion> habitacionesDisponibles = new ArrayList<>();

        try (PreparedStatement preparedStatement = connectionDAO.getConnection().prepareStatement(query)) {
            // Usar PreparedStatement para evitar problemas con la inyección de SQL y el formato de las fechas
            preparedStatement.setString(1, fechaInicioStr);
            preparedStatement.setString(2, fechaFinStr);
            preparedStatement.setString(3, fechaInicioStr);
            preparedStatement.setString(4, fechaFinStr);
            preparedStatement.setString(5, fechaInicioStr);
            preparedStatement.setString(6, fechaFinStr);
            preparedStatement.setString(7, fechaInicioStr);
            preparedStatement.setString(8, fechaFinStr);

            // Ejecutar la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Obtener los valores de la base de datos
                int idHabitacion = resultSet.getInt("idHabitacion");
                int capacidadCamas = resultSet.getInt("capacidadCamas");
                boolean camaDoble = resultSet.getBoolean("camaDoble");
                boolean aireAcondicionado = resultSet.getBoolean("aireAcondicionado");
                boolean balcon = resultSet.getBoolean("balcon");
                int idTipoHab = resultSet.getInt("idTipoHab");
                int idHotel = resultSet.getInt("idHotel");

                // Obtener el tipo de habitación y el hotel mediante sus controladoras
                TipoHabitacion tipoHabitacion = tipoHabitacionController.getTipoHabitacionById(idTipoHab);
                Hotel hotel = hotelController.getHotelById(idHotel);

                // Crear la habitación y agregarla a la lista
                Habitacion habitacion = new Habitacion(
                        idHabitacion,
                        capacidadCamas,
                        camaDoble,
                        tipoHabitacion,
                        aireAcondicionado,
                        balcon,
                        hotel
                );
                habitacionesDisponibles.add(habitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Mejorar manejo de excepciones si es necesario
        }

        return habitacionesDisponibles;
    }


}
