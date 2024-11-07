package org.example.view;

import org.example.DAO.CiudadDAO;
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
    CiudadDAO ciudadDAO = new CiudadDAO();
    List<Pais> paises = paisDAO.listarPaises();
    List<Ciudad> ciudades;

    public HotelView() {
        this.hotelController = new HotelController();
    }

    public void insertHotel() {
        System.out.println("Ingrese los datos del hotel a continuación:");

        // Solicitar el ID del hotel
        System.out.println("Ingrese el ID del hotel: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Solicitar el nombre del hotel
        System.out.println("Ingrese el nombre del hotel: ");
        String nombreHotel = scanner.nextLine();

        // Solicitar el país
        System.out.println("Seleccione el País:");
        for (Pais pais : paises) {
            System.out.println("ID: " + pais.getId() + ", Nombre: " + pais.getName());
        }
        int idPaisSeleccionado = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

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

        // Solicitar la ciudad correspondiente al país seleccionado
        ciudades = ciudadDAO.listarCiudadesPorPais(paisSeleccionado.getId());
        System.out.println("Seleccione la Ciudad:");
        for (Ciudad ciudad : ciudades) {
            System.out.println("ID: " + ciudad.getIdCiudad() + ", Nombre: " + ciudad.getNombreCiudad());
        }
        int idCiudadSeleccionada = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Ciudad ciudadSeleccionada = null;
        for (Ciudad ciudad : ciudades) {
            if (ciudad.getIdCiudad() == idCiudadSeleccionada) {
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

        // Solicitar la cantidad de estrellas del hotel
        System.out.println("Ingrese la cantidad de estrellas del hotel: ");
        int cantidadEstrellas = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Solicitar la dirección del hotel
        System.out.println("Ingrese la dirección del hotel: ");
        String direccion = scanner.nextLine();

        // Crear el objeto Hotel
        Hotel hotel = new Hotel(idHotel, nombreHotel, paisSeleccionado, ciudadSeleccionada, cantidadEstrellas, direccion);

        // Insertar el hotel utilizando el controlador
        boolean hotelInserted = this.hotelController.insertHotel(hotel);

        if (hotelInserted) {
            System.out.println("Hotel insertado exitosamente! " + hotel.toString());
        } else {
            System.out.println("Ocurrió un error al insertar el hotel.");
        }
    }
}
