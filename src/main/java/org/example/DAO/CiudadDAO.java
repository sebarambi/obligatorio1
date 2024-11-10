package org.example.DAO;

import org.example.model.Ciudad;
import org.example.model.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CiudadDAO {
    private ConnectionDAO connectionDAO;

    public CiudadDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<Ciudad> getAllCiudades() {
        String query = "SELECT c.idCiudad, c.nombreCiudad, p.idPais, p.nombrePais " +
                "FROM ciudad c " +
                "JOIN pais p ON c.idPais = p.idPais;";
        List<Ciudad> ciudades = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");

                // Crear el objeto Pais asociado
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);

                // Crear el objeto Ciudad y asignar el objeto Pais
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                ciudades.add(ciudad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ciudades;
    }

    public List<Ciudad> listarCiudadesPorIdPais(int idPais) {
        String query = "SELECT c.idCiudad, c.nombreCiudad, p.idPais, p.nombrePais " +
                "FROM ciudad c " +
                "JOIN pais p ON c.idPais = p.idPais " +
                "WHERE p.idPais = " + idPais + ";";
        List<Ciudad> ciudades = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int idCiudad = resultSet.getInt("idCiudad");
                String nombreCiudad = resultSet.getString("nombreCiudad");

                // Crear el objeto Pais asociado
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);

                // Crear el objeto Ciudad y asignar el objeto Pais
                Ciudad ciudad = new Ciudad(idCiudad, nombreCiudad, pais);
                ciudades.add(ciudad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ciudades;
    }
    public Ciudad getCiudadPorId(int idCiudad) {
        String query = "SELECT idCiudad, nombreCiudad, idPais FROM Ciudad WHERE idCiudad = ?";
        Ciudad ciudad = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idCiudad);

            if (resultSet.next()) {
                // Recuperar los datos de la ciudad
                int id = resultSet.getInt("idCiudad");
                String nombre = resultSet.getString("nombreCiudad");
                // Crear el objeto Ciudad
                ciudad = new Ciudad(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ciudad;
    }
}
