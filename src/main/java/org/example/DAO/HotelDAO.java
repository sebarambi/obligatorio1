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


}