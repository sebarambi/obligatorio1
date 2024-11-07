package org.example.DAO;

import org.example.model.Huesped;

import java.sql.ResultSet;
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
        String query = "SELECT idHuesped, nombre, aPaterno, aMaterno, numDocumento, fechaNacimiento, telefono, idPais, idTipoDoc FROM Huesped;";
        List<Huesped> huespedes = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String aPaterno = resultSet.getString("aPaterno");
                String aMaterno = resultSet.getString("aMaterno");
                String numDocumento = resultSet.getString("numDocumento");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento"); // Obtenemos la fecha como Date
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");
                int idTipoDocumento = resultSet.getInt("idTipoDoc");

                // Creamos el objeto Huesped y lo agregamos a la lista
                Huesped huesped = new Huesped(id, nombre, aPaterno, aMaterno, numDocumento, fechaNacimiento, telefono, idPais, idTipoDocumento);
                huespedes.add(huesped);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return huespedes;
    }


}
