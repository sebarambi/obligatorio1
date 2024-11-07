package org.example.DAO;

import org.example.model.Hotel;

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
}