package org.example.view;

import org.example.controller.HuespedController;
import org.example.model.Huesped;

import java.util.Scanner;

public class HuespedView {
    private Scanner scanner = new Scanner(System.in);
    private HuespedController huespedController;


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


        Huesped huesped = new Huesped(idHuesped, nombre, aPaterno, aMaterno, numDocumento, telefono);
        boolean huespedInserted = this.huespedController.insertHuesped(huesped);

        if (huespedInserted) {
            System.out.println("Usuario insertado! " + huesped.toString());
        } else {
            System.out.println("Ocurrio un error al insertar el usuario");
        }



    }
}
