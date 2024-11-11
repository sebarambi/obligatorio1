package org.example.DAO;

import org.example.controller.PaisController;
import org.example.controller.TipoDocumentoController;
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
    private PaisController paisController;
    private TipoDocumentoController tipoDocumentoController;

    public HuespedDAO() {
        this.connectionDAO = new ConnectionDAO();
        this.paisController = new PaisController();
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


                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);


                int idTipoDocumento = resultSet.getInt("idTipoDoc");
                String descripcionTipoDoc = resultSet.getString("nombre");
                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento, nombre);


                Huesped huesped = new Huesped(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, fechaNacimiento, telefono, pais);
                huespedes.add(huesped);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return huespedes;
    }

    public Huesped getHuespedById(int idHuesped) {
        String query = "SELECT h.idHuesped, h.nombre, h.aPaterno, h.aMaterno, h.numDocumento, " +
                "h.fechaNacimiento, h.telefono, " +
                "p.idPais, p.nombrePais, " +
                "td.idTipoDoc, td.nombre " +
                "FROM Huesped h " +
                "JOIN Pais p ON h.idPais = p.idPais " +
                "JOIN TipoDocumento td ON h.idTipoDoc = td.idTipoDoc " +
                "WHERE h.idHuesped = ?";

        Huesped huesped = null;

        try {

            ResultSet resultSet = connectionDAO.executeQuery(query, idHuesped);

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String aPaterno = resultSet.getString("aPaterno");
                String aMaterno = resultSet.getString("aMaterno");
                String numDocumento = resultSet.getString("numDocumento");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                String telefono = resultSet.getString("telefono");

                int idPais = resultSet.getInt("idPais");
                String nombrePais = resultSet.getString("nombrePais");
                Pais pais = new Pais(idPais, nombrePais);

                int idTipoDocumento = resultSet.getInt("idTipoDoc");
                String descripcionTipoDoc = resultSet.getString("nombre");
                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento, descripcionTipoDoc);

                huesped = new Huesped(idHuesped, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, fechaNacimiento, telefono, pais);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return huesped;
    }

    public boolean modificarHuesped(Huesped huesped) {
        String query = "UPDATE Huesped SET nombre = ?, aPaterno = ?, aMaterno = ?, " +
                "idTipoDoc = ?, numDocumento = ?, fechaNacimiento = ?, telefono = ?, idPais = ? " +
                "WHERE idHuesped = ?";

        return connectionDAO.executeUpdate(query,
                huesped.getNombre(),
                huesped.getaPaterno(),
                huesped.getaMaterno(),
                huesped.getTipoDocumento().getIdTipoDoc(),
                huesped.getNumDocumento(),
                huesped.getFechaNacimiento(),
                huesped.getTelefono(),
                huesped.getPais().getId(),
                huesped.getIdHuesped()
        );
    }

    public boolean eliminarHuesped(int idHuesped) {
        String query = "DELETE FROM Huesped WHERE idHuesped = ?";

        return connectionDAO.executeUpdate(query, idHuesped);
    }

    public List<Huesped> getAllHuespedes() {
        String query = "SELECT idHuesped, nombre, aPaterno, aMaterno, idTipoDoc, numDocumento, fechaNacimiento, telefono, idPais FROM Huesped";
        List<Huesped> huespedes = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {

                int idHuesped = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombre");
                String aPaterno = resultSet.getString("aPaterno");
                String aMaterno = resultSet.getString("aMaterno");
                int idTipoDoc = resultSet.getInt("idTipoDoc");
                String numDocumento = resultSet.getString("numDocumento");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                String telefono = resultSet.getString("telefono");
                int idPais = resultSet.getInt("idPais");

                TipoDocumento tipoDocumento = tipoDocumentoController.getTipoDocumentoById(idTipoDoc);
                Pais pais = paisController.obtenerPaisPorId(idPais);

                Huesped huesped = new Huesped(
                        idHuesped,
                        nombre,
                        aPaterno,
                        aMaterno,
                        tipoDocumento,
                        numDocumento,
                        fechaNacimiento,
                        telefono,
                        pais
                );

                huespedes.add(huesped);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return huespedes;
    }


}
