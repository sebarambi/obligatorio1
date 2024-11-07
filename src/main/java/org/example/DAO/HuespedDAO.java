package org.example.DAO;

import org.example.model.Huesped;

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

}
