package org.example.view;


import org.example.DAO.TipoHabitacionDAO;
import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.controller.HuespedController;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.TipoHabitacion;

import java.util.List;
import java.util.Scanner;

public class HabitacionView {
    private Scanner scanner = new Scanner(System.in);
    private HabitacionController habitacionController;
    private HotelController hotelController;

    TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();
    List<TipoHabitacion> tiposHabitacion = tipoHabitacionDAO.listarTipoHabitaciones();

    public HabitacionView() {
        this.habitacionController = new HabitacionController();
        this.hotelController = new HotelController();
    }

    public void menuHabitacion() {
        int opcion;

        do {
            System.out.println("===== MENU DE HABITACIONES =====");
            System.out.println("Opción 1: Insertar Habitación");
            System.out.println("Opción 2: Ver todas las Habitaciones");
            System.out.println("Opción 3: Modificar Habitación por ID");
            System.out.println("Opción 4: Eliminar Habitación por ID");
            System.out.println("Opción 5: Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertHabitacion();
                    break;
                case 2:
                    List<Habitacion> todasLasHabitaciones = habitacionController.getAllHabitaciones();
                    listarHabitaciones(todasLasHabitaciones);
                    break;
                case 3:
                    //modificarHabitacion();
                    break;
                case 4:
                    //eliminarHabitacion();
                    break;
                case 5:
                    System.out.println("Gracias por usar la gestión de Habitaciones.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        } while (opcion != 5); // Repite el menú hasta que el usuario elija salir
    }

    public void insertHabitacion() {
        System.out.println("Ingrese los datos de la habitación a continuación:");

        System.out.print("Ingrese el ID de la habitación: ");
        int idHabitacion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        System.out.print("Ingrese la capacidad de camas: ");
        int capacidadCamas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("¿La habitación está ocupada? (true/false): ");
        boolean ocupada = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("¿La habitación tiene cama doble? (true/false): ");
        boolean camaDoble = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("Seleccione el tipo de habitación:");
        for (TipoHabitacion tipo : tiposHabitacion) {
            System.out.println("ID: " + tipo.getIdTipoHabitacion() + ", Nombre: " + tipo.getDescripcion());
        }
        int idTipoHabitacion = scanner.nextInt();
        scanner.nextLine();
        TipoHabitacion tipoHabitacionSeleccionado = null;
        for (TipoHabitacion tipo : tiposHabitacion) {
            if (tipo.getIdTipoHabitacion() == idTipoHabitacion) {
                tipoHabitacionSeleccionado = tipo;
                break;
            }
        }
        if (tipoHabitacionSeleccionado != null) {
            System.out.println("Seleccionaste el tipo de habitación: " + tipoHabitacionSeleccionado.getDescripcion());
        } else {
            System.out.println("ID no válido. No se encontró el tipo de habitación.");
            return;
        }

        System.out.print("¿La habitación tiene aire acondicionado? (true/false): ");
        boolean aireAcondicionado = scanner.nextBoolean();
        scanner.nextLine();

        System.out.print("¿La habitación tiene balcón? (true/false): ");
        boolean balcon = scanner.nextBoolean();
        scanner.nextLine();

        List<Hotel> hoteles = hotelController.listarHoteles();
        System.out.println("Seleccione el hotel:");
        for (Hotel hotel : hoteles) {
            System.out.println("ID: " + hotel.getIdHotel() + ", Nombre: " + hotel.getNombreHotel());
        }
        int idHotelSeleccionado = scanner.nextInt();
        scanner.nextLine();
        Hotel hotelSeleccionado = null;
        for (Hotel hotel : hoteles) {
            if (hotel.getIdHotel() == idHotelSeleccionado) {
                hotelSeleccionado = hotel;
                break;
            }
        }
        if (hotelSeleccionado != null) {
            System.out.println("Seleccionaste el hotel: " + hotelSeleccionado.getNombreHotel());
        } else {
            System.out.println("ID no válido. No se encontró el hotel.");
            return;
        }

        Habitacion habitacion = new Habitacion(idHabitacion, capacidadCamas, ocupada, camaDoble, tipoHabitacionSeleccionado, aireAcondicionado, balcon, hotelSeleccionado);
        boolean habitacionInserted = this.habitacionController.insertHabitacion(habitacion);

        if (habitacionInserted) {
            System.out.println("Habitación insertada exitosamente! " + habitacion.toString());
        } else {
            System.out.println("Ocurrió un error al insertar la habitación.");
        }
    }
    public void listarHabitaciones(List<Habitacion> lista) {
        System.out.println("Las habitaciones son: ");
        for (Habitacion habitacion : lista) {
            System.out.println("-------------------------------------------------");
            habitacion.mostrarInformacion();
            System.out.println("-------------------------------------------------");
        }

    }

}
