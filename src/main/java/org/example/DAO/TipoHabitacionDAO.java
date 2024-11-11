package org.example.DAO;

import org.example.controller.TarifaController;
import org.example.model.Tarifa;
import org.example.model.TipoHabitacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAO {
    private ConnectionDAO connectionDAO;
    private TarifaController tarifaController;

    public TipoHabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
        this.tarifaController = new TarifaController();
    }

    public TipoHabitacion getTipoHabitacionById(int idTipo) {
        String query = "SELECT idTipoHab, descripcion, idTarifa FROM TipoHabitacion WHERE idTipoHab = ?";

        TipoHabitacion tipoHabitacion = null;

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idTipo);

            if (resultSet.next()) {
                int id = resultSet.getInt("idTipoHab");
                String descripcion = resultSet.getString("descripcion");
                int idTarifa = resultSet.getInt("idTarifa");

                Tarifa tarifa = tarifaController.getTarifaById(idTarifa);

                tipoHabitacion = new TipoHabitacion(id, descripcion, tarifa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tipoHabitacion;
    }


    public List<TipoHabitacion> listarTipoHabitaciones() {
        String query = "SELECT th.idTipoHab, th.descripcion, t.idTarifa, t.monto " +
                "FROM tipohabitacion th " +
                "JOIN tarifa t ON th.idTarifa = t.idTarifa;";

        List<TipoHabitacion> tipoHabitaciones = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int idTipoHabitacion = resultSet.getInt("idTipoHab");
                String descripcion = resultSet.getString("descripcion");

                int idTarifa = resultSet.getInt("idTarifa");
                int monto = resultSet.getInt("monto");

                Tarifa tarifa = new Tarifa(idTarifa, monto);

                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, descripcion, tarifa);
                tipoHabitaciones.add(tipoHabitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tipoHabitaciones;
    }

}
