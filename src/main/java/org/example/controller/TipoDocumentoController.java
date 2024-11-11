package org.example.controller;

import org.example.DAO.TipoDocumentoDAO;
import org.example.model.TipoDocumento;

import java.util.List;

public class TipoDocumentoController {
    private TipoDocumentoDAO tipoDocumentoDAO;

    // Constructor: Inicializa tipoDocumentoDAO
    public TipoDocumentoController() {
        tipoDocumentoDAO = new TipoDocumentoDAO();  // Inicializar el DAO
    }

    public List<TipoDocumento> listarTiposDocumento() {
        // Ahora tipoDocumentoDAO no será null
        return tipoDocumentoDAO.listarTiposDocumento();
    }

    public TipoDocumento getTipoDocumentoById(int idTipoDoc) {
        // Ahora tipoDocumentoDAO no será null
        return tipoDocumentoDAO.getTipoDocumentoById(idTipoDoc);
    }
}
