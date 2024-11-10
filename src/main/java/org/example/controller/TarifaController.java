package org.example.controller;

import org.example.DAO.ConnectionDAO;
import org.example.DAO.TarifaDAO;
import org.example.model.Tarifa;

import java.util.List;

public class TarifaController {
    private TarifaDAO tarifaDAO = new TarifaDAO();


    public TarifaController() {

        ConnectionDAO connectionDAO = new ConnectionDAO();
        tarifaDAO = new TarifaDAO();
    }

    // Método para obtener Tarifa por ID
    public Tarifa getTarifaById(int idTarifa) {
        return tarifaDAO.getTarifaById(idTarifa);
    }

    // Método para obtener todas las Tarifas
    public List<Tarifa> getAllTarifas() {
        return tarifaDAO.getAllTarifas();
    }

}
