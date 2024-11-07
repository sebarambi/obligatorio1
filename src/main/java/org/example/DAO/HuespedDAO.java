package org.example.DAO;

import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDocumento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuespedDAO {
    private ConnectionDAO connectionDAO;

    public HuespedDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    public boolean insertHuesped(Huesped huesped) {
        String query = "INSERT INTO Huesped (idHuesped,nombre, aPaterno, aMaterno,idTipoDoc, numDocumento, fechaNacimiento, telefono , idPais) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";

        return connectionDAO.executeUpdate(query,
                huesped.getIdHuesped(),
                huesped.getNombre(),
                huesped.getaPaterno(),
                huesped.getaMaterno(),
                huesped.getTipoDocumento().getIdTipoDoc(),
                huesped.getNumDocumento(),
                huesped.getFechaNacimiento(),
                huesped.getTelefono(),
                huesped.getPais().getId()
        );
    }

    public List<Huesped> listarHuespedes() {
        String query = "SELECT h.idHuesped, h.nombre, h.aPaterno, h.aMaterno, h.numDocumento, " +
                "h.fechaNacimiento, h.telefono, " +
                "p.idPais, p.nombrePais, " +
                "td.idTipoDoc, td.nombre " +
                "FROM Huesped h " +
                "JOIN Pais p ON h.idPais = p.idPais " +
                "JOIN TipoDocumento td ON h.idTipoDoc = td.idTipoDoc;";

        List<Huesped> huespedes = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String aPaterno = resultSet.getString("aPaterno");
                String aMaterno = resultSet.getString("aMaterno");
                String numDocumento = resultSet.getString("numDocumento");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                String telefono = resultSet.getString("telefono");

                // Crear el objeto Pais directamente con los datos de la consulta
                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);

                // Crear el objeto TipoDocumento directamente con los datos de la consulta
                int idTipoDocumento = resultSet.getInt("idTipoDoc");
                String descripcionTipoDoc = resultSet.getString("nombre");
                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento, nombre);

                // Crear el objeto Huesped con todos los datos y agregarlo a la lista
                Huesped huesped = new Huesped(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, fechaNacimiento, telefono, pais);
                huespedes.add(huesped);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return huespedes;
    }


}
