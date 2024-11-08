package org.example.view;

import org.example.DAO.CiudadDAO;
import org.example.DAO.HotelDAO;
import org.example.DAO.PaisDAO;
import org.example.controller.HotelController;

import org.example.model.Ciudad;
import org.example.model.Hotel;

import org.example.model.Pais;


import java.util.List;
import java.util.Scanner;

public class HotelView {
    private Scanner scanner = new Scanner(System.in);
    private HotelController hotelController;

    PaisDAO paisDAO = new PaisDAO();
    List<Pais> paises = paisDAO.listarPaises();
    CiudadDAO ciudadDAO = new CiudadDAO();
    HotelDAO hotelDAO = new HotelDAO();
    List<Hotel> todosLosHoteles = hotelDAO.listarHoteles();

    public HotelView() {
        this.hotelController = new HotelController();
    }

    public void menuHotel() {
        int opcion;

        do {
            System.out.println("\n--- Menú de Gestión de Hoteles ---");
            System.out.println("1. Crear nuevo hotel");
            System.out.println("2. Listar hoteles existentes");
            System.out.println("3. Actualizar hotel");
            System.out.println("4. Eliminar hotel");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    insertHotel();
                    break;

                case 2:
                    listarHoteles(todosLosHoteles);
                    break;

                case 3:
                    modificarHotel();
                    break;

                case 4:
                    //deleteHotel();
                    break;

                case 5:
                    System.out.println("Saliendo del menú de gestión de hoteles...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
            }
        } while (opcion != 5);
    }

    //Metodos-------------------------------------------------------------------------------------------------

    public void insertHotel() {
        System.out.println("Ingrese los datos del hotel a continuación:");

        System.out.print("Ingrese el ID del hotel: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el nombre del hotel: ");
        String nombreHotel = scanner.nextLine();

        // Selección de País
        System.out.println("Seleccione el país:");
        for (Pais pais : paises) {
            System.out.println("ID: " + pais.getId() + ", Nombre: " + pais.getName());
        }
        int idPaisSeleccionado = scanner.nextInt();
        scanner.nextLine();
        Pais paisSeleccionado = null;
        for (Pais pais : paises) {
            if (pais.getId() == idPaisSeleccionado) {
                paisSeleccionado = pais;
                break;
            }
        }
        if (paisSeleccionado != null) {
            System.out.println("Seleccionaste el país: " + paisSeleccionado.getName());
        } else {
            System.out.println("ID no válido. No se encontró el país.");
            return;
        }

        List<Ciudad> ciudades = ciudadDAO.listarCiudadesPorIdPais(paisSeleccionado.getId());
        System.out.println("Seleccione la ciudad:");
        for (Ciudad ciudad : ciudades) {
            System.out.println("ID: " + ciudad.getIdCiudad() + ", Nombre: " + ciudad.getNombreCiudad());
        }
        int idCiudadSeleccionado = scanner.nextInt();
        scanner.nextLine();
        Ciudad ciudadSeleccionada = null;
        for (Ciudad ciudad : ciudades) {
            if (ciudad.getIdCiudad() == idCiudadSeleccionado) {
                ciudadSeleccionada = ciudad;
                break;
            }
        }
        if (ciudadSeleccionada != null) {
            System.out.println("Seleccionaste la ciudad: " + ciudadSeleccionada.getNombreCiudad());
        } else {
            System.out.println("ID no válido. No se encontró la ciudad.");
            return;
        }

        System.out.print("Ingrese la cantidad de estrellas del hotel (1-5): ");
        int cantidadEstrellas = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese la dirección del hotel: ");
        String direccion = scanner.nextLine();

        // Crear el objeto Hotel con los datos ingresados
        Hotel hotel = new Hotel(idHotel, nombreHotel, paisSeleccionado, ciudadSeleccionada, cantidadEstrellas, direccion);

        // Llamar al controlador para insertar el hotel
        boolean hotelInserted = this.hotelController.insertHotel(hotel);

        if (hotelInserted) {
            System.out.println("Hotel insertado exitosamente!");
        } else {
            System.out.println("Ocurrió un error al insertar el hotel.");
        }
    }
    public void listarHoteles(List<Hotel> lista) {
        System.out.println("Los huespedes que se encuentran registrados en el sistema son: ");
        for (Hotel hotel : lista) {
            System.out.println("-------------------------------------------------");
            hotel.mostrarInformacion();
            System.out.println("-------------------------------------------------");
        }

    }
    public void modificarHotel() {
        listarHoteles(todosLosHoteles);
        System.out.println("Ingrese el ID del Hotel que desea modificar: ");
        int idAModificar = scanner.nextInt();

        Hotel hotel = hotelDAO.getHotelById(idAModificar);

        // Si el hotel existe, procedemos con la modificación
        if (hotel != null) {
            System.out.println("Hotel encontrado: " + hotel.getNombreHotel());

            // Modificar nombre del hotel
            System.out.println("Ingrese nuevo nombre del hotel (deje en blanco para no modificar): ");
            scanner.nextLine();  // Consumir salto de línea pendiente
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                hotel.setNombreHotel(nuevoNombre);
            }

            // Modificar país
            System.out.println("¿Desea modificar el país? (S/N): ");
            String respuestaPais = scanner.nextLine();
            if (respuestaPais.equalsIgnoreCase("S")) {
                System.out.println("Seleccione el nuevo país:");
                for (Pais pais : paises) {
                    System.out.println("ID: " + pais.getId() + ", Nombre: " + pais.getName());
                }
                int idPaisSeleccionado = scanner.nextInt();
                scanner.nextLine();  // Consumir salto de línea pendiente
                Pais paisSeleccionado = null;

                for (Pais pais : paises) {
                    if (pais.getId() == idPaisSeleccionado) {
                        paisSeleccionado = pais;
                        break;
                    }
                }
                if (paisSeleccionado != null) {
                    hotel.setPais(paisSeleccionado);
                    System.out.println("País actualizado a: " + paisSeleccionado.getName());
                } else {
                    System.out.println("ID no válido. No se encontró el país.");
                }
            }

            // Modificar ciudad
            System.out.println("¿Desea modificar la ciudad? (S/N): ");
            String respuestaCiudad = scanner.nextLine();
            if (respuestaCiudad.equalsIgnoreCase("S")) {
                System.out.println("Seleccione la nueva ciudad:");
                List<Ciudad> ciudades = ciudadDAO.listarCiudadesPorIdPais(hotel.getPais().getId());
                for (Ciudad ciudad : ciudades) {
                    System.out.println("ID: " + ciudad.getIdCiudad() + ", Nombre: " + ciudad.getNombreCiudad());
                }
                int idCiudadSeleccionada = scanner.nextInt();
                scanner.nextLine();  // Consumir salto de línea pendiente
                Ciudad ciudadSeleccionada = null;

                for (Ciudad ciudad : ciudades) {
                    if (ciudad.getIdCiudad() == idCiudadSeleccionada) {
                        ciudadSeleccionada = ciudad;
                        break;
                    }
                }
                if (ciudadSeleccionada != null) {
                    hotel.setCiudad(ciudadSeleccionada);
                    System.out.println("Ciudad actualizada a: " + ciudadSeleccionada.getNombreCiudad());
                } else {
                    System.out.println("ID no válido. No se encontró la ciudad.");
                }
            }

            // Modificar cantidad de estrellas
            System.out.println("Ingrese nueva cantidad de estrellas (deje en blanco para no modificar): ");
            String estrellasInput = scanner.nextLine();
            if (!estrellasInput.isEmpty()) {
                int nuevaCantidadEstrellas = Integer.parseInt(estrellasInput);
                hotel.setCantidadEstrellas(nuevaCantidadEstrellas);
            }

            // Modificar dirección
            System.out.println("Ingrese nueva dirección (deje en blanco para no modificar): ");
            String nuevaDireccion = scanner.nextLine();
            if (!nuevaDireccion.isEmpty()) {
                hotel.setDireccion(nuevaDireccion);
            }

            // Una vez modificado el objeto, lo pasamos al DAO para actualizarlo en la base de datos
            boolean success = hotelDAO.modificarHotel(hotel);

            if (success) {
                System.out.println("Hotel modificado correctamente.");
            } else {
                System.out.println("Error al modificar el hotel.");
            }
        } else {
            System.out.println("Hotel no encontrado.");
        }
    }


}
