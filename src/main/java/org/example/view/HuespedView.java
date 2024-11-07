package org.example.view;

import org.example.DAO.PaisDAO;
import org.example.DAO.TipoDocumentoDAO;
import org.example.controller.HuespedController;
import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDocumento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HuespedView {
    private Scanner scanner = new Scanner(System.in);
    private HuespedController huespedController;

    PaisDAO paisDAO = new PaisDAO();
    TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
    List<Pais> paises = paisDAO.listarPaises();
    List<TipoDocumento> tipoDocumentos = tipoDocumentoDAO.listarTiposDocumento();


    public HuespedView() {
        this.huespedController = new HuespedController();
    }

    public void insertHuesped() {
        System.out.println("Ingrese los datos del huésped a continuación:");
        System.out.println("Ingrese el ID del huésped: ");
        int idHuesped = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido paterno: ");
        String aPaterno = scanner.nextLine();

        System.out.println("Ingrese el apellido materno: ");
        String aMaterno = scanner.nextLine();

        System.out.println("Ingrese el tipo de documento: ");
        for (TipoDocumento tipo : tipoDocumentos) {
            System.out.println("ID: " + tipo.getIdTipoDoc() + ", Nombre: " + tipo.getNombre());
        }
        int idSeleccionado = scanner.nextInt();
        scanner.nextLine();
        TipoDocumento tipoSeleccionado = null;
        for (TipoDocumento tipo : tipoDocumentos) {
            if (tipo.getIdTipoDoc() == idSeleccionado) {
                tipoSeleccionado = tipo;
                break;
            }
        }
        if (tipoSeleccionado != null) {
            System.out.println("Seleccionaste el tipo de documento: " + tipoSeleccionado.getNombre());
        } else {
            System.out.println("ID no válido. No se encontró el tipo de documento.");
            return;
        }

        System.out.println("Ingrese el número de documento: ");
        String numDocumento = scanner.nextLine();

        System.out.println("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
        String fechaIngresada = scanner.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaSeleccionada = null;
        try {
            fechaSeleccionada = formato.parse(fechaIngresada);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, ingrese la fecha en formato yyyy-MM-dd.");
            return;
        }

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        System.out.println("Seleccione el País:");
        for (Pais pais : paises) {
            System.out.println("ID: " + pais.getId() + ", Nombre: " + pais.getName());
        }
        int idPaisSeleccionado = scanner.nextInt();
        scanner.nextLine();
        Pais paisSeleccionado = null;

        for (Pais pais : paises) {
            if (pais.getId() == idPaisSeleccionado) {
                paisSeleccionado = pais;
                break;
            }
        }
        if (paisSeleccionado != null) {
            System.out.println("Seleccionaste el país: " + paisSeleccionado.getName());
        } else {
            System.out.println("ID no válido. No se encontró el país.");
            return;
        }

        Huesped huesped = new Huesped(idHuesped, nombre, aPaterno, aMaterno, tipoSeleccionado, numDocumento, fechaSeleccionada, telefono, paisSeleccionado);
        boolean huespedInserted = this.huespedController.insertHuesped(huesped);

        if (huespedInserted) {
            System.out.println("Huésped insertado exitosamente! " + huesped.toString());
        } else {
            System.out.println("Ocurrió un error al insertar el huésped.");
        }
    }
}
