package org.example.controller;

import org.example.DAO.HotelDAO;
import org.example.model.Hotel;

public class HotelController {
    private HotelDAO hotelDAO = new HotelDAO();

    public HotelController() {

    }


    public boolean insertHotel(Hotel hotel) {
        return this.hotelDAO.insertHotel(hotel);
    }


}
