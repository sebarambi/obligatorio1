package org.example.view;

import org.example.DAO.CiudadDAO;
import org.example.DAO.HabitacionDAO;
import org.example.DAO.HotelDAO;
import org.example.DAO.PaisDAO;
import org.example.controller.HotelController;

import org.example.model.Ciudad;
import org.example.model.Habitacion;
import org.example.model.Hotel;

import org.example.model.Pais;


import java.util.List;
import java.util.Scanner;

public class HotelView {
    private Scanner scanner = new Scanner(System.in);
    private HotelController hotelController;

    PaisDAO paisDAO = new PaisDAO();
    List<Pais> paises = paisDAO.getAllPaises();
    CiudadDAO ciudadDAO = new CiudadDAO();
    HabitacionDAO habitacionDAO = new HabitacionDAO();

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
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertHotel();
                    break;

                case 2:
                    List<Hotel> hotelesAListar = hotelController.listarHoteles();
                    listarHoteles(hotelesAListar);
                    break;

                case 3:
                    modificarHotel();
                    break;

                case 4:
                    eliminarHotel();
                    break;

                case 5:
                    System.out.println("Saliendo del menú de gestión de hoteles...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
            }
        } while (opcion != 5);
    }


    public void insertHotel() {
        System.out.println("Ingrese los datos del hotel a continuación:");

        System.out.print("Ingrese el ID del hotel: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el nombre del hotel: ");
        String nombreHotel = scanner.nextLine();

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

        Hotel hotel = new Hotel(idHotel, nombreHotel, paisSeleccionado, ciudadSeleccionada, cantidadEstrellas, direccion);

        boolean hotelInserted = this.hotelController.insertHotel(hotel);

        if (hotelInserted) {
            System.out.println("Hotel insertado exitosamente!");
        } else {
            System.out.println("Ocurrió un error al insertar el hotel.");
        }
    }

    public void listarHoteles(List<Hotel> lista) {
        System.out.println("Los Hoteles que se encuentran en el sistema son: ");
        for (Hotel hotel : lista) {
            System.out.println("-------------------------------------------------");
            hotel.mostrarInformacion();
            System.out.println("-------------------------------------------------");
        }

    }

    public void modificarHotel() {
        List<Hotel> hotelesAModificar = hotelController.listarHoteles();
        listarHoteles(hotelesAModificar);

        System.out.println("Ingrese el ID del Hotel que desea modificar: ");
        int idAModificar = scanner.nextInt();

        Hotel hotel = hotelController.getHotelById(idAModificar);


        if (hotel != null) {
            System.out.println("Hotel encontrado: " + hotel.getNombreHotel());


            System.out.println("Ingrese nuevo nombre del hotel (deje en blanco para no modificar): ");
            scanner.nextLine();  // Consumir salto de línea pendiente
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                hotel.setNombreHotel(nuevoNombre);
            }


            System.out.println("¿Desea modificar el país? (S/N): ");
            String respuestaPais = scanner.nextLine();
            if (respuestaPais.equalsIgnoreCase("S")) {
                System.out.println("Seleccione el nuevo país:");
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
                    hotel.setPais(paisSeleccionado);
                    System.out.println("País actualizado a: " + paisSeleccionado.getName());
                } else {
                    System.out.println("ID no válido. No se encontró el país.");
                }
            }


            System.out.println("¿Desea modificar la ciudad? (S/N): ");
            String respuestaCiudad = scanner.nextLine();
            if (respuestaCiudad.equalsIgnoreCase("S")) {
                System.out.println("Seleccione la nueva ciudad:");
                List<Ciudad> ciudades = ciudadDAO.listarCiudadesPorIdPais(hotel.getPais().getId());
                for (Ciudad ciudad : ciudades) {
                    System.out.println("ID: " + ciudad.getIdCiudad() + ", Nombre: " + ciudad.getNombreCiudad());
                }
                int idCiudadSeleccionada = scanner.nextInt();
                scanner.nextLine();
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


            System.out.println("Ingrese nueva cantidad de estrellas (deje en blanco para no modificar): ");
            String estrellasInput = scanner.nextLine();
            if (!estrellasInput.isEmpty()) {
                int nuevaCantidadEstrellas = Integer.parseInt(estrellasInput);
                hotel.setCantidadEstrellas(nuevaCantidadEstrellas);
            }


            System.out.println("Ingrese nueva dirección (deje en blanco para no modificar): ");
            String nuevaDireccion = scanner.nextLine();
            if (!nuevaDireccion.isEmpty()) {
                hotel.setDireccion(nuevaDireccion);
            }


            boolean success = hotelController.modificarHotel(hotel);

            if (success) {
                System.out.println("Hotel modificado correctamente.");
            } else {
                System.out.println("Error al modificar el hotel.");
            }
        } else {
            System.out.println("Hotel no encontrado.");
        }
    }

    public void eliminarHotel() {
        List<Hotel> hotelesAEliminar = hotelController.listarHoteles();
        listarHoteles(hotelesAEliminar);

        System.out.println("Ingrese el ID del Hotel que desea eliminar: ");
        int idAEliminar = scanner.nextInt();


        Hotel hotel = hotelController.getHotelById(idAEliminar);


        if (hotel != null) {
            System.out.println("Hotel encontrado: " + hotel.getNombreHotel());
            System.out.println("¿Está seguro de que desea eliminar este hotel? (s/n): ");
            scanner.nextLine();
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                List<Habitacion> habitacionesHotelAEliminar = habitacionDAO.listarHabitacionesPorIdHotel(hotel.getIdHotel());
                if (habitacionesHotelAEliminar.isEmpty()) {
                    boolean eliminado = hotelController.eliminarHotel(idAEliminar);
                    if (eliminado) {
                        System.out.println("Hotel eliminado exitosamente.");
                    } else {
                        System.out.println("Ocurrió un error al eliminar el hotel.");
                    }
                } else {
                    System.out.println("Eliminación cancelada. El hotel no debe tener habitaciones asignadas para ser eliminado");
                }
            } else {
                System.out.println("Hotel no encontrado.");
            }
        }

    }
}
