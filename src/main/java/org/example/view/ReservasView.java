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
            System.out.println("2. Modificar reservas.");
            System.out.println("3. Salir.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    insertarReserva();
                    break;
                case 2:
                    modificarReserva();
                    break;
                case 3:

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

            // Validar que la fecha de inicio no sea una fecha pasada
            Date fechaActual = new Date();
            if (fechaInicio.before(fechaActual)) {
                System.out.println("La fecha de inicio no puede ser una fecha pasada.");
                return;
            }

            listarHabitacionesDisponibles(fechaInicioStr, fechaFinStr);
            System.out.print("Ingresar el ID de la habitación que quieres reservar: ");
            int idSeleccionado = scanner.nextInt();

            // Obtener la habitación seleccionada y validar
            Habitacion habitacionSeleccionada = habitacionController.getHabitacionById(idSeleccionado);
            if (habitacionSeleccionada == null) {
                System.out.println("No se encontró la habitación con ID " + idSeleccionado);
                return;
            }

            System.out.print("Ingresar el ID del huésped responsable de la reserva: ");
            List<Huesped> huespedes = huespedController.listarHuespedes();
            for (Huesped huesped : huespedes) {
                huesped.mostrarInformacion();
            }

            int idHuespedSeleccionado = scanner.nextInt();
            Huesped huespedSeleccionado = huespedController.getHuespedById(idHuespedSeleccionado);
            if (huespedSeleccionado == null) {
                System.out.println("No se encontró el huésped con ID " + idHuespedSeleccionado);
                return;
            }

            System.out.print("Ingresar la cantidad de personas: ");
            int cantidadPersonas = scanner.nextInt();

            System.out.print("Ingresar la cantidad de personas: ");
            String descripciones = scanner.nextLine();

            // Validar que la cantidad de personas no exceda la capacidad de la habitación
            if (cantidadPersonas > habitacionSeleccionada.getCapacidadCamas()) {
                System.out.println("La cantidad de personas excede la capacidad de la habitación seleccionada.");
                return;
            } else if (cantidadPersonas <= 0) {
                System.out.println("La cantidad de personas debe ser mayor que cero.");
                return;
            }

            // Crear y registrar la reserva
            Reservas reserva = new Reservas(huespedSeleccionado, fechaInicio, fechaFin, cantidadPersonas, "");
            boolean reservaInserted = reservasController.insertarReserva(reserva);

            // Confirmar si la reserva fue ingresada correctamente
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

    public void modificarReserva() {
        // Paso 1: Solicitar el ID del huésped para ver sus reservas
        System.out.println("Ingrese el ID del huésped para ver sus reservas: ");
        int idHuesped = scanner.nextInt();

        // Validación de que el huésped existe
        Huesped huesped = huespedController.getHuespedById(idHuesped);
        if (huesped == null) {
            System.out.println("El huésped con ID " + idHuesped + " no existe.");
            return; // Si el huésped no existe, salir del método
        }

        // Paso 2: Obtener las reservas del huésped
        List<Reservas> reservas = reservasController.obtenerReservasPorIdHuesped(idHuesped);

        if (reservas.isEmpty()) {
            System.out.println("El huésped no tiene reservas.");
            return;
        }

        // Paso 3: Listar las reservas del huésped
        System.out.println("Reservas del huésped ID " + idHuesped + ":");
        for (Reservas reserva : reservas) {
            System.out.println("ID Reserva: " + reserva.getIdReserva() + ", Fecha Inicio: " + reserva.getFechaInicio() + ", Fecha Fin: " + reserva.getFechaFin() + ", Cantidad Personas: " + reserva.getCantidadPersonas());
        }

        // Paso 4: Solicitar al usuario que ingrese el ID de la reserva que desea modificar
        System.out.println("Ingrese el ID de la reserva que desea modificar: ");
        int idReservaAModificar = scanner.nextInt();

        // Paso 5: Buscar la reserva por ID
        Reservas reserva = reservasController.obtenerReservaPorId(idReservaAModificar);

        // Paso 6: Verificar si la reserva existe
        if (reserva != null) {
            System.out.println("Reserva encontrada: ");
            System.out.println("ID Reserva: " + reserva.getIdReserva() + ", Fecha Inicio: " + reserva.getFechaInicio() + ", Fecha Fin: " + reserva.getFechaFin() + ", Cantidad Personas: " + reserva.getCantidadPersonas());

            // Paso 7: Modificar la fecha de inicio de la reserva
            System.out.println("¿Desea modificar la fecha de inicio? (S/N): ");
            scanner.nextLine(); // Consumir salto de línea pendiente
            String respuestaInicio = scanner.nextLine();
            if (respuestaInicio.equalsIgnoreCase("S")) {
                System.out.println("Ingrese la nueva fecha de inicio (yyyy-mm-dd): ");
                String fechaInicioStr = scanner.nextLine();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date nuevaFechaInicio = dateFormat.parse(fechaInicioStr);
                    reserva.setFechaInicio(nuevaFechaInicio);
                } catch (ParseException e) {
                    System.out.println("Fecha inválida.");
                }
            }

            // Paso 8: Modificar la fecha de fin de la reserva
            System.out.println("¿Desea modificar la fecha de fin? (S/N): ");
            String respuestaFin = scanner.nextLine();
            if (respuestaFin.equalsIgnoreCase("S")) {
                System.out.println("Ingrese la nueva fecha de fin (yyyy-mm-dd): ");
                String fechaFinStr = scanner.nextLine();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date nuevaFechaFin = dateFormat.parse(fechaFinStr);
                    reserva.setFechaFin(nuevaFechaFin);
                } catch (ParseException e) {
                    System.out.println("Fecha inválida.");
                }
            }

            // Paso 9: Modificar la cantidad de personas de la reserva
            System.out.println("¿Desea modificar la cantidad de personas? (S/N): ");
            String respuestaCantidadPersonas = scanner.nextLine();
            if (respuestaCantidadPersonas.equalsIgnoreCase("S")) {
                System.out.println("Ingrese la nueva cantidad de personas: ");
                int nuevaCantidadPersonas = scanner.nextInt();
                reserva.setCantidadPersonas(nuevaCantidadPersonas);
            }

            // Paso 10: Modificar las observaciones de la reserva
            System.out.println("¿Desea modificar las observaciones? (S/N): ");
            scanner.nextLine(); // Consumir salto de línea pendiente
            String respuestaObservaciones = scanner.nextLine();
            if (respuestaObservaciones.equalsIgnoreCase("S")) {
                System.out.println("Ingrese las nuevas observaciones: ");
                String nuevasObservaciones = scanner.nextLine();
                reserva.setObservaciones(nuevasObservaciones);
            }

            // Paso 11: Una vez modificada la reserva, la pasamos al DAO para actualizarla en la base de datos
            boolean success = reservasController.modificarReserva(reserva);

            if (success) {
                System.out.println("Reserva modificada correctamente.");
            } else {
                System.out.println("Error al modificar la reserva.");
            }
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }


}
