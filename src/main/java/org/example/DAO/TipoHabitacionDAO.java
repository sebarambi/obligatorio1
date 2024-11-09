package org.example.DAO;

import org.example.model.Tarifa;
import org.example.model.TipoHabitacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionDAO {
    private ConnectionDAO connectionDAO;

    public TipoHabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public TipoHabitacion getTipoDeHabitacionById(int idTipo) {
        String query = "SELECT idTipo, descripcion FROM TipoHabitacion WHERE idTipo = ?";

        TipoHabitacion tipoHabitacion = null;

        try {
            // Ejecutamos la consulta pasando el idTipo como parámetro
            ResultSet resultSet = connectionDAO.executeQuery(query, idTipo);

            // Si encontramos el resultado, lo asignamos al objeto tipoHabitacion
            if (resultSet.next()) {
                int id = resultSet.getInt("idTipo");
                String descripcion = resultSet.getString("descripcion");

                // Creamos el objeto TipoHabitacion con los datos obtenidos
                tipoHabitacion = new TipoHabitacion(id, descripcion);
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
                // Recuperar datos de TipoHabitacion
                int idTipoHabitacion = resultSet.getInt("idTipoHab");
                String descripcion = resultSet.getString("descripcion");

                // Recuperar datos de Tarifa
                int idTarifa = resultSet.getInt("idTarifa");
                int monto = resultSet.getInt("monto");

                // Crear el objeto Tarifa
                Tarifa tarifa = new Tarifa(idTarifa, monto);

                // Crear el objeto TipoHabitacion y asignar la tarifa
                TipoHabitacion tipoHabitacion = new TipoHabitacion(idTipoHabitacion, descripcion, tarifa);
                tipoHabitaciones.add(tipoHabitacion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tipoHabitaciones;
    }

}
