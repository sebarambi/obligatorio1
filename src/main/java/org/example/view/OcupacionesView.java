package org.example.view;

import org.example.controller.OcupacionesController;
import org.example.model.Ocupaciones;

import java.util.Scanner;

public class OcupacionesView {
    private Scanner scanner = new Scanner(System.in);
    private OcupacionesController ocupaciones = new OcupacionesController();

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // Mostrar el menú
            System.out.println("***************************************");
            System.out.println("        BIENVENIDOS AL CHECK-IN");
            System.out.println("          DEL HOTEL XYZ");
            System.out.println("***************************************");
            System.out.println("¡Hola y bienvenidos!");
            System.out.println("Nos complace tenerlos con nosotros. A continuación, por favor elija una de las opciones:");
            System.out.println("1. Realizar check-in");
            System.out.println("5. Salir");
            System.out.print("Por favor, ingrese el número de la opción deseada: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    break;
                case 2:
                    System.out.println("Mostrando información de la reserva...");
                    break;
                case 3:
                    System.out.println("Solicitando información sobre los servicios...");
                    break;
                case 4:
                    System.out.println("Conectando con el personal...");
                    break;
                case 5:
                    System.out.println("Gracias por su visita. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida, por favor ingrese una opción correcta.");
                    break;
            }

        } while (opcion != 5); // El menú se repite hasta que el usuario elige la opción 5

        scanner.close(); // Cerrar el scanner
    }
}
