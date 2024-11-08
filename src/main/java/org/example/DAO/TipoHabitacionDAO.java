package org.example.DAO;

import org.example.model.TipoHabitacion;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
