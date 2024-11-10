package org.example.view;


import org.example.controller.HabitacionController;
import org.example.controller.HuespedController;
import org.example.controller.ReservasController;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.Huesped;
import org.example.model.Reservas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReservasView {
    private Scanner scanner = new Scanner(System.in);
    private HuespedController huespedController;
    private HabitacionController habitacionController;
    private ReservasController reservasController;

    public ReservasView() {
        this.huespedController = new HuespedController();
        this.habitacionController = new HabitacionController();
        this.reservasController = new ReservasController();
    }

    public void MenuReservas() {
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
                    insertarReserva();
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

    public void listarHabitacionesDisponibles(String fechaInicioStr, String fechaFinStr) {
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

    public void insertarReserva() {
        try {
            // Solicitar fechas de inicio y fin
            System.out.print("Ingrese la fecha de inicio (yyyy-mm-dd): ");
            String fechaInicioStr = scanner.nextLine();
            System.out.print("Ingrese la fecha de fin (yyyy-mm-dd): ");
            String fechaFinStr = scanner.nextLine();

            // Parsear las fechas de String a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFin = dateFormat.parse(fechaFinStr);

            // Validar que la fecha de inicio no sea posterior a la de fin
            if (fechaInicio.after(fechaFin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            listarHabitacionesDisponibles(fechaInicioStr, fechaFinStr);
            System.out.println("Ingresar el ID de la habitación que queres reservar: ");
            int idSeleccionado = scanner.nextInt();

            // Obtener la habitación seleccionada y validación
            Habitacion habitacionSeleccionada = habitacionController.getHabitacionById(idSeleccionado);
            if (habitacionSeleccionada == null) {
                System.out.println("No se encontró la habitación con ID " + idSeleccionado);
                return;
            }

            System.out.println("Ingresar el ID del huésped que va a ser el responsable de la reserva: ");
            List<Huesped> huespedes = huespedController.listarHuespedes();
            for (Huesped huesped : huespedes) {
                huesped.mostrarInformacion();
            }

            int idHuespedSeleccionado = scanner.nextInt();

            // Obtener el huésped seleccionado y validación
            Huesped huespedSeleccionado = huespedController.getHuespedById(idHuespedSeleccionado);
            if (huespedSeleccionado == null) {
                System.out.println("No se encontró el huésped con ID " + idHuespedSeleccionado);
                return;
            }

            Reservas reserva = new Reservas(huespedSeleccionado, fechaInicio, fechaFin);
            boolean reservaInserted = this.reservasController.insertarReserva(reserva);


        } catch (Exception e) {
            System.out.println("Hubo un error al procesar las fechas o la reserva. Por favor, intente nuevamente.");
            e.printStackTrace();
        }

    }
}
