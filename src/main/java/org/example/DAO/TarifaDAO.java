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
            // Ejecutamos la consulta con el parámetro idTarifa
            ResultSet resultSet = connectionDAO.executeQuery(query, new Object[]{idTarifa});

            // Si encontramos un resultado, lo asignamos al objeto tarifa
            if (resultSet.next()) {
                int id = resultSet.getInt("idTarifa");
                int monto = resultSet.getInt("monto");

                // Creamos el objeto Tarifa con los datos obtenidos
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
            // Ejecutamos la consulta para obtener todas las tarifas
            ResultSet resultSet = connectionDAO.executeQuery(query, null);

            // Recorremos los resultados y los añadimos a la lista
            while (resultSet.next()) {
                int id = resultSet.getInt("idTarifa");
                int monto = resultSet.getInt("monto");

                // Creamos el objeto Tarifa y lo añadimos a la lista
                tarifas.add(new Tarifa(id, monto));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tarifas;
    }
}
