package org.example;

import java.util.Scanner;
import org.example.view.HuespedView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("========================================");
            System.out.println("          Bienvenido al Hotel          ");
            System.out.println("========================================");
            System.out.println("1. Ingresar huéspedes");
            System.out.println("2. Administracion de Huespedes");
            System.out.println("3. Consultar reservas");
            System.out.println("4. Cancelar una reserva");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Ingresar Hotel");

                    break;
                case 2:
                    HuespedView huespedView = new HuespedView();
                    huespedView.menuHuesped();
                    break;
                case 3:
                    System.out.println("Consultando reservas...");
                    // Aquí llamas a la función correspondiente para consultar reservas
                    break;
                case 4:
                    System.out.println("Cancelando una reserva...");
                    // Aquí llamas a la función correspondiente para cancelar una reserva
                    break;
                case 5:
                    System.out.println("Gracias por utilizar el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
                    break;
            }

            System.out.println();

        } while (option != 5);

        scanner.close();
    }

}
