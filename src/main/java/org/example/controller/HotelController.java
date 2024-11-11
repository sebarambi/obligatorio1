package org.example.controller;

import org.example.DAO.HotelDAO;
import org.example.model.Hotel;
import org.example.model.Huesped;

import java.util.List;

public class HotelController {
    private HotelDAO hotelDAO = new HotelDAO();

    public HotelController() {

    }

    public boolean insertHotel(Hotel hotel) {
        return this.hotelDAO.insertHotel(hotel);
    }

    public Hotel getHotelById(int idHotel) {
        return this.hotelDAO.getHotelById(idHotel);
    }

    public List<Hotel> listarHoteles() {
        return this.hotelDAO.listarHoteles();
    }

    public boolean modificarHotel(Hotel hotel) {
        return hotelDAO.modificarHotel(hotel);
    }
    public boolean eliminarHotel(int id) {
        return this.hotelDAO.eliminarHotel(id);
    }

    public List<Hotel> obtenerHotelesPorCiudad(int idCiudad) {
        return hotelDAO.obtenerHotelesPorCiudad(idCiudad);
    }

    public List<Hotel> obtenerHotelesPorNombre(String nombreHotel) {
        return hotelDAO.obtenerHotelesPorNombre(nombreHotel);
    }

    public List<Hotel> obtenerHotelesPorEstrellas(int cantidadEstrellas) {
        return hotelDAO.obtenerHotelesPorEstrellas(cantidadEstrellas);
    }

}
