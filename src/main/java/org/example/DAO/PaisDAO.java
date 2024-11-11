package org.example.DAO;

import org.example.model.Pais;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {
    private ConnectionDAO connectionDAO;

    public PaisDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<Pais> getAllPaises() {
        String query = "SELECT idPais, nombrePais FROM pais;";
        List<Pais> paises = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("idPais");
                String nombre = resultSet.getString("nombrePais");

                Pais pais = new Pais(id, nombre);
                paises.add(pais);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paises;
    }
    public Pais getPaisPorId(int idPais) {
        String query = "SELECT idPais, nombrePais FROM Pais WHERE idPais = " + idPais;
        Pais pais = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            if (resultSet.next()) {
                int id = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");

                pais = new Pais(id, nombrePais);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pais;
    }

}
