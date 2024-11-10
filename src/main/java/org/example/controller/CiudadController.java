package org.example.controller;

import org.example.DAO.CiudadDAO;
import org.example.model.Ciudad;

import java.util.List;


public class CiudadController {
    private CiudadDAO ciudadDAO = new CiudadDAO();

    public CiudadController() {
    }

    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadDAO.getAllCiudades();
    }

    public Ciudad obtenerCiudadPorId(int idCiudad) {
        return ciudadDAO.getCiudadPorId(idCiudad);
    }

}
