package org.example.controller;

import org.example.DAO.PaisDAO;
import org.example.model.Pais;

import java.util.List;

public class PaisController {
    private PaisDAO paisDAO = new PaisDAO(); // Instancia de PaisDAO

    public PaisController() {
    }

    public List<Pais> obtenerTodosLosPaises() {
        return paisDAO.getAllPaises(); // Usar la instancia paisDAO
    }

    public Pais obtenerPaisPorId(int idPais) {
        return paisDAO.getPaisPorId(idPais);
    }
}
