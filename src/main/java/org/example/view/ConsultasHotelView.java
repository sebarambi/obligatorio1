package org.example.view;

import org.example.controller.CiudadController;
import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.model.Ciudad;
import org.example.model.Hotel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsultasHotelView {
    private Scanner scanner = new Scanner(System.in);
    private HotelController hotelController;
    private CiudadController ciudadController;

    public ConsultasHotelView() {
        this.hotelController = new HotelController();
        this.ciudadController = new CiudadController();
    }

    public static void mostrarMenuConsultas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("Menú de Consultas:");
            System.out.println("1. Filtrar por Ciudad");
            System.out.println("2. Filtrar por Nombre");
            System.out.println("3. Filtrar por fecha");
            System.out.println("4. Filtrar por cantidad de estrellas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ConsultasHotelView consultasHotelView = new ConsultasHotelView();
                    consultasHotelView.listarCiudadesYSeleccionar();
                    break;
                case 2:

                    System.out.println("Mostrando tarifas por tipo de habitación...");

                    break;
                case 3:
                    // Lógica para ver información de huéspedes
                    System.out.println("Mostrando información de huéspedes...");
                    // Aquí puedes implementar la lógica para consultar la información de los huéspedes
                    break;
                case 4:
                    // Lógica para ver el estado de una habitación
                    System.out.println("Mostrando el estado de la habitación...");
                    // Aquí puedes implementar la lógica para consultar el estado de la habitación
                    break;
                case 5:
                    System.out.println("Saliendo del menú de consultas...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
                    break;
            }

        } while (opcion != 5);  // El bucle continuará hasta que el usuario elija la opción 5

        scanner.close();
    }

    public void listarCiudadesYSeleccionar() {
        // Obtener todas las ciudades desde el controller
        List<Ciudad> ciudades = ciudadController.obtenerTodasLasCiudades();

        if (ciudades.isEmpty()) {
            System.out.println("No se encontraron ciudades.");
            return;
        }

        // Mostrar todas las ciudades
        System.out.println("Seleccione una ciudad:");

        for (Ciudad ciudad : ciudades) {
            System.out.println("ID Ciudad: " + ciudad.getIdCiudad() + " - Nombre: " + ciudad.getNombreCiudad());
        }

        Ciudad ciudadElegida = null;
        int idSeleccionada = -1;

        // Intentar obtener un ID de ciudad válido
        while (ciudadElegida == null) {
            System.out.println("Ingrese el ID de la ciudad que desea seleccionar:");

            try {
                idSeleccionada = scanner.nextInt();

                // Obtener la ciudad por el ID
                ciudadElegida = ciudadController.obtenerCiudadPorId(idSeleccionada);

                if (ciudadElegida == null) {
                    System.out.println("El ID de la ciudad es incorrecto o no existe. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next(); // Limpiar el buffer
            }
        }

        // Si la ciudad existe, mostramos su nombre
        System.out.println("Ciudad seleccionada con ID: " + idSeleccionada);
        System.out.println("Ciudad: " + ciudadElegida.getNombreCiudad());

        // Obtener los hoteles en la ciudad seleccionada
        List<Hotel> hotelesPorCiudad = hotelController.obtenerHotelesPorCiudad(idSeleccionada);

        // Verificamos si hay hoteles disponibles en esa ciudad
        if (hotelesPorCiudad.isEmpty()) {
            System.out.println("No hay hoteles disponibles en la ciudad de " + ciudadElegida.getNombreCiudad());
        } else {
            System.out.println("Los hoteles en la ciudad de " + ciudadElegida.getNombreCiudad() + " son:");

            // Recorremos la lista de hoteles y mostramos la información
            for (Hotel hotel : hotelesPorCiudad) {
                System.out.println("Hotel: " + hotel.getNombreHotel());
                System.out.println("Dirección: " + hotel.getDireccion());
                System.out.println("Cantidad de estrellas: " + hotel.getCantidadEstrellas());
                System.out.println("-----------------------------");
            }
        }
    }



}
