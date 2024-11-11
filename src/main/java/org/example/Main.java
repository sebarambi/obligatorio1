package org.example;

import java.util.Scanner;

import org.example.view.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("========================================");
            System.out.println("          Bienvenido al Hotel          ");
            System.out.println("========================================");
            System.out.println("1. Administracion de Hoteles");
            System.out.println("2. Administracion de Huespedes");
            System.out.println("3. Administracion de Habitaciones");
            System.out.println("4. Consultas sobre Hoteles");
            System.out.println("5. Reservas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    HotelView hotelView = new HotelView();
                    hotelView.menuHotel();
                    break;
                case 2:
                    HuespedView huespedView = new HuespedView();
                    huespedView.menuHuesped();
                    break;
                case 3:
                    HabitacionView habitacionView = new HabitacionView();
                    habitacionView.menuHabitacion();
                    break;
                case 4:
                    ConsultasHotelView consultasHotelView = new ConsultasHotelView();
                    consultasHotelView.mostrarMenuConsultas();
                    break;
                case 5:
                    ReservasView reservasView = new ReservasView();
                    reservasView.MenuReservas();
                    break;
                case 6:
                    System.out.println("Gracias por utilizar el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
                    break;
            }

            System.out.println();

        } while (option != 6);

        scanner.close();
    }

}
