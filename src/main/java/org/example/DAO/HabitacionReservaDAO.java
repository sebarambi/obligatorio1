package org.example.DAO;

import org.example.controller.HabitacionReservaController;

public class HabitacionReservaDAO {
    private ConnectionDAO connectionDAO;
    private HabitacionReservaController habitacionReservaController;

    public HabitacionReservaDAO() {
        this.connectionDAO = new ConnectionDAO();
        this.habitacionReservaController = new HabitacionReservaController();

    }
}
