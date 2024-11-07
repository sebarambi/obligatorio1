package org.example.view;

import org.example.DAO.PaisDAO;
import org.example.controller.HuespedController;
import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDocumento;

import java.util.List;
import java.util.Scanner;

public class HuespedView {
    private Scanner scanner = new Scanner(System.in);
    private HuespedController huespedController;

    PaisDAO paisDAO = new PaisDAO();
    List<Pais> paises = paisDAO.listarPaises();


    public HuespedView() {
        this.huespedController = new HuespedController();
    }

    public void insertHuesped() {
        System.out.println("Ingrese los datos del huésped a continuación:");
        System.out.print("Ingrese el ID del huésped: ");
        int idHuesped = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido paterno: ");
        String aPaterno = scanner.nextLine();

        System.out.print("Ingrese el apellido materno: ");
        String aMaterno = scanner.nextLine();

        System.out.print("Ingrese el número de documento: ");
        String numDocumento = scanner.nextLine();

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        System.out.println("Seleccione el Pais:");
        for (Pais pais : paises) {
            System.out.println("ID: " + pais.getId() + ", Nombre: " + pais.getName());
        }


        Huesped huesped = new Huesped ();
        boolean huespedInserted = this.huespedController.insertHuesped(huesped);

        if (huespedInserted) {
            System.out.println("Usuario insertado! " + huesped.toString());
        } else {
            System.out.println("Ocurrio un error al insertar el usuario");
        }


    }
}
