package org.example.view;


import org.example.controller.HabitacionController;
import org.example.controller.HuespedController;
import org.example.controller.ReservasController;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.Huesped;
import org.example.model.Reservas;

import java.text.ParseException;
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
            System.out.println("1. Reserva de habitaciones.");
            System.out.println("3. Eliminar reservas.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertarReserva();
                    break;
                case 2:
                    eliminarReserva();
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
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicio = null;
        Date fechaFin = null;

        try {
            fechaInicio = formato.parse(fechaInicioStr);
            fechaFin = formato.parse(fechaFinStr);

            System.out.println("Las habitaciones disponibles entre las fechas seleccionadas son: ");
            List<Habitacion> habitaciones = habitacionController.listarHabitacionesDisponibles(fechaInicio, fechaFin);
            if (habitaciones.isEmpty()) {
                System.out.println("No hay habitaciones disponibles en esas fechas.");
            } else {
                for (Habitacion habitacion : habitaciones) {
                    habitacion.mostrarInformacion();
                    System.out.println("========================================================");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al procesar las fechas. Asegúrese de usar el formato correcto (yyyy-mm-dd).");
            e.printStackTrace();
        }
    }

    public void insertarReserva() {
        try {
            System.out.print("Ingrese la fecha de inicio (yyyy-mm-dd): ");
            String fechaInicioStr = scanner.nextLine();
            System.out.print("Ingrese la fecha de fin (yyyy-mm-dd): ");
            String fechaFinStr = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = dateFormat.parse(fechaInicioStr);
            Date fechaFin = dateFormat.parse(fechaFinStr);


            if (fechaInicio.after(fechaFin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }


            Date fechaActual = new Date();
            if (fechaInicio.before(fechaActual)) {
                System.out.println("La fecha de inicio no puede ser una fecha pasada o actual.");
                return;
            }

            listarHabitacionesDisponibles(fechaInicioStr, fechaFinStr);
            System.out.print("Ingresar el ID de la habitación que quieres reservar: ");
            int idSeleccionado = scanner.nextInt();

            Habitacion habitacionSeleccionada = habitacionController.getHabitacionById(idSeleccionado);
            if (habitacionSeleccionada == null) {
                System.out.println("No se encontró la habitación con ID " + idSeleccionado);
                return;
            }

            System.out.print("Ingresar el ID del huésped responsable de la reserva: ");
            List<Huesped> huespedes = huespedController.listarHuespedes();
            for (Huesped huesped : huespedes) {
                huesped.mostrarInformacion();
                System.out.println("==============================================");
            }

            int idHuespedSeleccionado = scanner.nextInt();
            Huesped huespedSeleccionado = huespedController.getHuespedById(idHuespedSeleccionado);
            if (huespedSeleccionado == null) {
                System.out.println("No se encontró el huésped con ID " + idHuespedSeleccionado);
                return;
            }

            System.out.println("Ingresar la cantidad de personas: ");
            int cantidadPersonas = scanner.nextInt();

            System.out.println("Ingresar observaciones para la reserva: ");
            String descripciones = scanner.nextLine();

            if (cantidadPersonas > habitacionSeleccionada.getCapacidadCamas()) {
                System.out.println("La cantidad de personas excede la capacidad de Camas de la habitación seleccionada.");
                return;
            } else if (cantidadPersonas <= 0) {
                System.out.println("La cantidad de personas debe ser mayor que cero.");
                return;
            }

            Reservas reserva = new Reservas(huespedSeleccionado, fechaInicio, fechaFin, cantidadPersonas, descripciones);
            boolean reservaInserted = reservasController.insertarReserva(reserva);

            if (reservaInserted) {
                System.out.println("¡Reserva ingresada exitosamente!");
            } else {
                System.out.println("Hubo un problema al intentar ingresar la reserva. Intente nuevamente.");
            }

        } catch (Exception e) {
            System.out.println("Hubo un error al procesar las fechas o la reserva. Por favor, intente nuevamente.");
            e.printStackTrace();
        }
    }

    public void eliminarReserva() {
        System.out.println("Ingrese el ID del huésped para ver sus reservas: ");
        int idHuesped = scanner.nextInt();

        Huesped huesped = huespedController.getHuespedById(idHuesped);
        if (huesped == null) {
            System.out.println("El huésped con ID " + idHuesped + " no existe.");
            return;
        }

        List<Reservas> reservas = reservasController.obtenerReservasPorIdHuesped(idHuesped);

        if (reservas.isEmpty()) {
            System.out.println("El huésped no tiene reservas.");
            return;
        }


        System.out.println("Las Reservas del huésped seleccionado son: ");
        for (Reservas reserva : reservas) {
            System.out.println("ID: " + reserva.getIdReserva() + ", Fecha Inicio: " + reserva.getFechaInicio() + ", Fecha Fin: " + reserva.getFechaFin() + ", Cantidad Personas: " + reserva.getCantidadPersonas());
            System.out.println("=====================================================================================================================================================================================");
        }
        System.out.println("Ingrese el ID de la reserva que desea eliminar: ");
        int idAEliminar = scanner.nextInt();

        Reservas reserva = reservasController.obtenerReservaPorId(idAEliminar);
        if (reserva != null) {
            System.out.println("Reserva encontrada: ID " + reserva.getIdReserva() + ", Huesped: " + reserva.getHuesped().getNombre());
            System.out.println("¿Está seguro de que desea eliminar esta reserva? (s/n): ");
            scanner.nextLine();
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                boolean eliminada = reservasController.eliminarReserva(idAEliminar);
                if (eliminada) {
                    System.out.println("Reserva eliminada exitosamente.");
                } else {
                    System.out.println("Ocurrió un error al eliminar la reserva.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }


}
