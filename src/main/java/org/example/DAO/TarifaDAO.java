package org.example.DAO;

import org.example.model.Tarifa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO {
    private ConnectionDAO connectionDAO;

    public TarifaDAO () {
        this.connectionDAO = new ConnectionDAO();
    }

    public Tarifa getTarifaById(int idTarifa) {
        String query = "SELECT t.idTarifa, t.monto FROM Tarifa t WHERE t.idTarifa = ?";

        Tarifa tarifa = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, new Object[]{idTarifa});

            if (resultSet.next()) {
                int id = resultSet.getInt("idTarifa");
                int monto = resultSet.getInt("monto");

                tarifa = new Tarifa(id, monto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tarifa;
    }

    public List<Tarifa> getAllTarifas() {
        String query = "SELECT t.idTarifa, t.monto FROM Tarifa t";
        List<Tarifa> tarifas = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, null);

            while (resultSet.next()) {
                int id = resultSet.getInt("idTarifa");
                int monto = resultSet.getInt("monto");

                tarifas.add(new Tarifa(id, monto));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tarifas;
    }
}
