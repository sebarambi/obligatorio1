package org.example.controller;

import org.example.DAO.TipoDocumentoDAO;
import org.example.model.TipoDocumento;

import java.util.List;

public class TipoDocumentoController {
    private TipoDocumentoDAO tipoDocumentoDAO;

    public TipoDocumentoController() {
        tipoDocumentoDAO = new TipoDocumentoDAO();  //
    }

    public List<TipoDocumento> listarTiposDocumento() {
        return tipoDocumentoDAO.listarTiposDocumento();
    }

    public TipoDocumento getTipoDocumentoById(int idTipoDoc) {
        return tipoDocumentoDAO.getTipoDocumentoById(idTipoDoc);
    }
}
