package org.example.view;


import org.example.controller.HabitacionController;
import org.example.controller.HuespedController;
import org.example.controller.ReservasController;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.Huesped;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservasView {
    private Scanner scanner = new Scanner(System.in);
    private HuespedController huespedController;
    private HabitacionController habitacionController;

    public ReservasView() {
        this.huespedController = new HuespedController();
        this.habitacionController = new HabitacionController();
    }

    public void MenuReservas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("Menú de Reservas:");
            System.out.println("1. Reserva de habitaciones por un período de tiempo.");
            System.out.println("2. Filtrar habitaciones con y sin reserva.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    listarHabitacionesDisponibles();
                    break;
                case 2:
                    //filtrarHabitaciones();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
    }

    public void listarHabitacionesDisponibles() {
        // Solicitar fechas de inicio y fin
        System.out.print("Ingrese la fecha de inicio (yyyy-mm-dd): ");
        String fechaInicioStr = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (yyyy-mm-dd): ");
        String fechaFinStr = scanner.nextLine();

        // Crear un SimpleDateFormat para parsear las fechas
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = null;
        Date fechaFin = null;

        try {
            // Convertir las fechas de String a Date
            fechaInicio = formato.parse(fechaInicioStr);
            fechaFin = formato.parse(fechaFinStr);

            // Imprimir las fechas convertidas (opcional)
            System.out.println("Fecha de inicio: " + fechaInicio);
            System.out.println("Fecha de fin: " + fechaFin);

            // Llamar al método de controlador para obtener habitaciones disponibles
            List<Habitacion> habitaciones = habitacionController.listarHabitacionesDisponibles(fechaInicio, fechaFin);

            // Mostrar las habitaciones disponibles
            if (habitaciones.isEmpty()) {
                System.out.println("No hay habitaciones disponibles en esas fechas.");
            } else {
                System.out.println("Habitaciones disponibles:");
                for (Habitacion habitacion : habitaciones) {
                    habitacion.mostrarInformacion();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al procesar las fechas. Asegúrese de usar el formato correcto (yyyy-mm-dd).");
            e.printStackTrace();
        }
    }


}
