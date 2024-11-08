package org.example.DAO;

import org.example.model.Ciudad;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private ConnectionDAO connectionDAO;

    public HotelDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public boolean insertHotel(Hotel hotel) {
        String query = "INSERT INTO Hotel (idHotel,nombreHotel,idPais,idCiudad, cantidadEstrellas, direccion) VALUES (?, ?, ?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query,
                hotel.getIdHotel(),
                hotel.getNombreHotel(),
                hotel.getPais().getId(),
                hotel.getCiudad().getIdCiudad(),
                hotel.getCantidadEstrellas(),
                hotel.getDireccion()
        );
    }

    public List<Hotel> listarHoteles() {
        String query = "SELECT h.idHotel, h.nombreHotel, h.direccion, h.cantidadEstrellas, " +
                "p.idPais, p.nombrePais, " +
                "c.idCiudad, c.nombreCiudad " +
                "FROM Hotel h " +
                "JOIN Pais p ON h.idPais = p.idPais " +
                "JOIN Ciudad c ON h.idCiudad = c.idCiudad;";

        List<Hotel> hoteles = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int idHotel = resultSet.getInt("idHotel");
                String nombreHotel = resultSet.getString("nombreHotel");
                String direccion = resultSet.getString("direccion");
                int cantidadEstrellas = resultSet.getInt("cantidadEstrellas");

                // Recuperar información del país
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);

                // Recuperar información de la ciudad
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad);

                // Crear el objeto Hotel sin las habitaciones
                HabitacionDAO habitacionDAO = new HabitacionDAO();
                List<Habitacion> listaHabitacionPorId = habitacionDAO.listarHabitacionesPorIdHotel(idHotel);
                Hotel hotel = new Hotel(idHotel, nombreHotel, pais, ciudad, cantidadEstrellas, direccion ,listaHabitacionPorId);
                hoteles.add(hotel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return hoteles;
    }

    public Hotel getHotelById(int idHotel) {
        String query = "SELECT h.idHotel, h.nombreHotel, h.cantidadEstrellas, h.direccion, " +
                "p.idPais, p.nombrePais, " +
                "c.idCiudad, c.nombreCiudad " +
                "FROM Hotel h " +
                "JOIN Pais p ON h.idPais = p.idPais " +
                "JOIN Ciudad c ON h.idCiudad = c.idCiudad " +
                "WHERE h.idHotel = ?";

        Hotel hotel = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idHotel);

            // Si hay un resultado, lo asignamos al objeto hotel
            if (resultSet.next()) {
                String nombreHotel = resultSet.getString("nombreHotel");
                int cantidadEstrellas = resultSet.getInt("cantidadEstrellas");
                String direccion = resultSet.getString("direccion");

                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);

                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad);

                // Crear el objeto Hotel con los valores obtenidos
                hotel = new Hotel(idHotel, nombreHotel, pais, ciudad, cantidadEstrellas, direccion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return hotel;
    }
    public boolean modificarHotel(Hotel hotel) {
        String query = "UPDATE Hotel SET nombreHotel = ?, idPais = ?, idCiudad = ?, " +
                "cantidadEstrellas = ?, direccion = ? " +
                "WHERE idHotel = ?";

        return connectionDAO.executeUpdate(query,
                hotel.getNombreHotel(),
                hotel.getPais().getId(),
                hotel.getCiudad().getIdCiudad(),
                hotel.getCantidadEstrellas(),
                hotel.getDireccion(),
                hotel.getIdHotel()  // El ID del hotel es necesario para la condición WHERE
        );
    }
    public boolean eliminarHotel(int idHotel) {
        String query = "DELETE FROM Hotel WHERE idHotel = ?";

        return connectionDAO.executeUpdate(query, idHotel);
    }




}