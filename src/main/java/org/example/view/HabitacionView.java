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
                    modificarHabitacion();
                    break;
                case 4:
                    eliminarHabitacion();
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

        Habitacion habitacion = new Habitacion(idHabitacion, capacidadCamas, camaDoble, tipoHabitacionSeleccionado, aireAcondicionado, balcon, hotelSeleccionado);
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

    public void modificarHabitacion() {
        List<Habitacion> habitacionesParaModificar = habitacionController.getAllHabitaciones();
        listarHabitaciones(habitacionesParaModificar);
        System.out.println("Ingrese el ID de la habitación que desea modificar: ");
        int idAModificar = scanner.nextInt();

        // Buscar la habitación por ID
        Habitacion habitacion = habitacionController.getHabitacionById(idAModificar);

        // Si la habitación existe, proceder con la modificación
        if (habitacion != null) {
            System.out.println("Habitación encontrada: ");
            habitacion.mostrarInformacion();

            // Modificar capacidad de camas
            System.out.println("Ingrese la nueva capacidad de camas (deje en blanco para no modificar): ");
            scanner.nextLine();  // Consumir salto de línea pendiente
            String nuevaCapacidadCamas = scanner.nextLine();
            if (!nuevaCapacidadCamas.isEmpty()) {
                try {
                    habitacion.setCapacidadCamas(Integer.parseInt(nuevaCapacidadCamas));
                } catch (NumberFormatException e) {
                    System.out.println("Capacidad inválida.");
                }
            }

            // Modificar cama doble
            System.out.println("¿Desea modificar la cama doble? (S/N): ");
            String respuestaCamaDoble = scanner.nextLine();
            if (respuestaCamaDoble.equalsIgnoreCase("S")) {
                System.out.println("¿La habitación tendrá cama doble? (S/N): ");
                String camaDobleRespuesta = scanner.nextLine();
                habitacion.setCamaDoble(camaDobleRespuesta.equalsIgnoreCase("S"));
            }

            // Modificar tipo de habitación
            System.out.println("¿Desea modificar el tipo de habitación? (S/N): ");
            String respuestaTipoHabitacion = scanner.nextLine();
            if (respuestaTipoHabitacion.equalsIgnoreCase("S")) {
                System.out.println("Seleccione el nuevo tipo de habitación:");
                for (TipoHabitacion tipo : tiposHabitacion) {
                    System.out.println("ID: " + tipo.getIdTipoHabitacion() + ", Nombre: " + tipo.getDescripcion());
                }
                int idTipoSeleccionado = scanner.nextInt();
                scanner.nextLine();  // Consumir salto de línea pendiente
                TipoHabitacion tipoSeleccionado = null;

                for (TipoHabitacion tipo : tiposHabitacion) {
                    if (tipo.getIdTipoHabitacion() == idTipoSeleccionado) {
                        tipoSeleccionado = tipo;
                        break;
                    }
                }
                if (tipoSeleccionado != null) {
                    habitacion.setTipoHabitacion(tipoSeleccionado);
                    System.out.println("Tipo de habitación actualizado a: " + tipoSeleccionado.getDescripcion());
                } else {
                    System.out.println("ID no válido. No se encontró el tipo de habitación.");
                }
            }


            // Modificar aire acondicionado
            System.out.println("¿Desea modificar el aire acondicionado? (S/N): ");
            String respuestaAire = scanner.nextLine();
            if (respuestaAire.equalsIgnoreCase("S")) {
                System.out.println("¿La habitación tendrá aire acondicionado? (S/N): ");
                String aireRespuesta = scanner.nextLine();
                habitacion.setAireAcondicionado(aireRespuesta.equalsIgnoreCase("S"));
            }

            // Modificar balcón
            System.out.println("¿Desea modificar el balcón? (S/N): ");
            String respuestaBalcon = scanner.nextLine();
            if (respuestaBalcon.equalsIgnoreCase("S")) {
                System.out.println("¿La habitación tendrá balcón? (S/N): ");
                String balconRespuesta = scanner.nextLine();
                habitacion.setBalcon(balconRespuesta.equalsIgnoreCase("S"));
            }

            // Una vez modificada la habitación, la pasamos al DAO para actualizarla en la base de datos
            boolean success = habitacionController.modificarHabitacion(habitacion);

            if (success) {
                System.out.println("Habitación modificada correctamente.");
            } else {
                System.out.println("Error al modificar la habitación.");
            }
        } else {
            System.out.println("Habitación no encontrada.");
        }
    }
    public void eliminarHabitacion() {
        // Listar todas las habitaciones registradas
        List<Habitacion> todasLasHabitaciones = habitacionController.getAllHabitaciones();
        listarHabitaciones(todasLasHabitaciones);

        System.out.println("Ingrese el ID de la habitación que desea eliminar: ");
        int idAEliminar = scanner.nextInt();

        // Buscar la habitación por ID
        Habitacion habitacion = habitacionController.getHabitacionById(idAEliminar);

        // Si la habitación existe, proceder a eliminarla
        if (habitacion != null) {
            // Confirmar eliminación
            System.out.println("Habitación encontrada: ID " + habitacion.getIdHabitacion() + ", Capacidad de camas: " + habitacion.getCapacidadCamas());
            System.out.println("¿Está seguro de que desea eliminar esta habitación? (s/n): ");
            scanner.nextLine();  // Consumir el salto de línea pendiente
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                // Eliminar habitación
                boolean eliminada = habitacionController.eliminarHabitacion(idAEliminar);
                if (eliminada) {
                    System.out.println("Habitación eliminada exitosamente.");
                } else {
                    System.out.println("Ocurrió un error al eliminar la habitación.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Habitación no encontrada.");
        }
    }


}


