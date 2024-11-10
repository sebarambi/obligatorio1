package org.example.view;

import org.example.controller.CiudadController;
import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.controller.PaisController;
import org.example.model.Ciudad;
import org.example.model.Hotel;
import org.example.model.Pais;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsultasHotelView {
    private Scanner scanner = new Scanner(System.in);
    private HotelController hotelController;
    private CiudadController ciudadController;
    private PaisController paisController;

    public ConsultasHotelView() {
        this.hotelController = new HotelController();
        this.ciudadController = new CiudadController();
        this.paisController = new PaisController();
    }

    public static void mostrarMenuConsultas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Crear una instancia de ConsultasHotelView, fuera del bucle
        ConsultasHotelView consultasHotelView = new ConsultasHotelView();

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
                    consultasHotelView.listarCiudadesYSeleccionar();
                    break;
                case 2:
                    consultasHotelView.listarHotelesPorNombre();
                    break;
                case 3:
                    // Lógica para ver información de huéspedes
                    System.out.println("Mostrando información de huéspedes...");
                    break;
                case 4:
                    consultasHotelView.listarHotelesPorEstrellas();
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

    public void listarHotelesPorNombre() {
        // Obtener todos los hoteles desde el controller
        List<Hotel> hoteles = hotelController.obtenerHotelesPorNombre("");

        if (hoteles.isEmpty()) {
            System.out.println("No se encontraron hoteles.");
            return;
        }

        // Mostrar todos los hoteles
        System.out.println("Ingrese el nombre del hotel que desea buscar:");

        String nombreHotelSeleccionado = "";

        // Intentar obtener un nombre de hotel válido
        while (nombreHotelSeleccionado.isEmpty()) {
            try {
                nombreHotelSeleccionado = scanner.nextLine().trim();

                if (nombreHotelSeleccionado.isEmpty()) {
                    System.out.println("El nombre del hotel no puede estar vacío. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un nombre válido.");
            }
        }

        // Obtener los hoteles que coinciden con el nombre ingresado
        List<Hotel> hotelesPorNombre = hotelController.obtenerHotelesPorNombre(nombreHotelSeleccionado);

        // Verificamos si hay hoteles disponibles con el nombre proporcionado
        if (hotelesPorNombre.isEmpty()) {
            System.out.println("No se encontraron hoteles con el nombre: " + nombreHotelSeleccionado);
        } else {
            System.out.println("Los hoteles que coinciden con el nombre '" + nombreHotelSeleccionado + "' son:");

            // Recorremos la lista de hoteles y mostramos la información
            for (Hotel hotel : hotelesPorNombre) {
                System.out.println("Hotel: " + hotel.getNombreHotel());
                System.out.println("Dirección: " + hotel.getDireccion());
                System.out.println("Cantidad de estrellas: " + hotel.getCantidadEstrellas());
                System.out.println("-----------------------------");
            }
        }
    }
    public void listarHotelesPorEstrellas() {
        // Pedir la cantidad de estrellas al usuario
        int cantidadEstrellasSeleccionada = 0;
        boolean cantidadValida = false;

        // Bucle para asegurarse de que el usuario ingrese una cantidad válida de estrellas (entre 1 y 5)
        while (!cantidadValida) {
            try {
                System.out.println("Ingrese la cantidad de estrellas (1-5):");
                cantidadEstrellasSeleccionada = scanner.nextInt();

                if (cantidadEstrellasSeleccionada >= 1 && cantidadEstrellasSeleccionada <= 5) {
                    cantidadValida = true;  // Se ha ingresado una cantidad válida
                } else {
                    System.out.println("Por favor, ingrese un número de estrellas entre 1 y 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();  // Limpiar el buffer de entrada
            }
        }

        // Llamar al controlador para obtener los hoteles con la cantidad de estrellas seleccionada
        List<Hotel> hotelesPorEstrellas = hotelController.obtenerHotelesPorEstrellas(cantidadEstrellasSeleccionada);

        // Verificar si se encontraron hoteles
        if (hotelesPorEstrellas.isEmpty()) {
            System.out.println("No se encontraron hoteles con " + cantidadEstrellasSeleccionada + " estrellas.");
        } else {
            System.out.println("Los hoteles con " + cantidadEstrellasSeleccionada + " estrellas son:");

            // Recorrer la lista de hoteles y mostrar la información
            for (Hotel hotel : hotelesPorEstrellas) {
                System.out.println("Hotel: " + hotel.getNombreHotel());
                System.out.println("Dirección: " + hotel.getDireccion());
                System.out.println("Cantidad de estrellas: " + hotel.getCantidadEstrellas());
                System.out.println("-----------------------------");
            }
        }
    }




}
