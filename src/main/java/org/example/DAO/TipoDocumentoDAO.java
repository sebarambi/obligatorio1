package org.example.DAO;

import org.example.model.TipoDocumento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {
    private ConnectionDAO connectionDAO;

    public TipoDocumentoDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public List<TipoDocumento> listarTiposDocumento() {
        String query = "SELECT idTipoDoc, nombre FROM tipodocumento;";
        List<TipoDocumento> tiposDocumento = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("idTipoDoc");
                String nombre = resultSet.getString("nombre");


                TipoDocumento tipoDoc = new TipoDocumento(id, nombre);
                tiposDocumento.add(tipoDoc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tiposDocumento;
    }
    public TipoDocumento getTipoDocumentoById(int idTipoDoc) {
        String query = "SELECT idTipoDoc, nombre FROM tipodocumento WHERE idTipoDoc = ?";

        TipoDocumento tipoDocumento = null;

        try {
            // Ejecutar la consulta y obtener el ResultSet
            ResultSet resultSet = connectionDAO.executeQuery(query, idTipoDoc);

            // Si hay un resultado, lo asignamos al objeto tipoDocumento
            if (resultSet.next()) {
                int id = resultSet.getInt("idTipoDoc");
                String nombre = resultSet.getString("nombre");

                // Crear el objeto TipoDocumento con los valores obtenidos
                tipoDocumento = new TipoDocumento(id, nombre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tipoDocumento;
    }
}
