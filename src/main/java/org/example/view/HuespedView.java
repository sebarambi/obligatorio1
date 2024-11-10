package org.example.view;

import org.example.DAO.HuespedDAO;
import org.example.DAO.PaisDAO;
import org.example.DAO.TipoDocumentoDAO;
import org.example.controller.HuespedController;
import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDocumento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HuespedView {
    private Scanner scanner = new Scanner(System.in);
    private HuespedController huespedController;

    PaisDAO paisDAO = new PaisDAO();
    TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
    HuespedDAO huespedDAO = new HuespedDAO();
    List<Pais> paises = paisDAO.getAllPaises();
    List<TipoDocumento> tipoDocumentos = tipoDocumentoDAO.listarTiposDocumento();
    List<Huesped> todosLosHuespedes = huespedDAO.listarHuespedes();


    public HuespedView() {
        this.huespedController = new HuespedController();
    }

    public void menuHuesped() {
        int opcion;

        do {
            System.out.println("===== MENU DE HUESPEDES =====");
            System.out.println("Opción 1: Insertar Huespedes");
            System.out.println("Opción 2: Ver todos los Huespedes");
            System.out.println("Opción 3: Modificar Huesped por ID");
            System.out.println("Opción 4: Eliminar Huesped por ID");
            System.out.println("Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    insertHuesped();
                    break;
                case 2:
                    listarHuespedes(todosLosHuespedes);
                    break;
                case 3:
                    modificarHuesped();
                    break;
                case 4:
                    eliminarHuesped();
                    break;
                case 5:
                    System.out.println("Gracias por usar la gestion de Huespedes.");
                    break;
                default:
                    // Opción inválida
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    break;
            }
        } while (opcion != 5); // Repite el menú hasta que el usuario elija salir

        scanner.close();
    }

    //Metodos ---------------------------------------------------------------------------------------------------

    public void insertHuesped() {
        System.out.println("Ingrese los datos del huésped a continuación:");
        System.out.println("Ingrese el ID del huésped: ");
        int idHuesped = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido paterno: ");
        String aPaterno = scanner.nextLine();

        System.out.println("Ingrese el apellido materno: ");
        String aMaterno = scanner.nextLine();

        System.out.println("Ingrese el tipo de documento: ");
        for (TipoDocumento tipo : tipoDocumentos) {
            System.out.println("ID: " + tipo.getIdTipoDoc() + ", Nombre: " + tipo.getNombre());
        }
        int idSeleccionado = scanner.nextInt();
        scanner.nextLine();
        TipoDocumento tipoSeleccionado = null;
        for (TipoDocumento tipo : tipoDocumentos) {
            if (tipo.getIdTipoDoc() == idSeleccionado) {
                tipoSeleccionado = tipo;
                break;
            }
        }
        if (tipoSeleccionado != null) {
            System.out.println("Seleccionaste el tipo de documento: " + tipoSeleccionado.getNombre());
        } else {
            System.out.println("ID no válido. No se encontró el tipo de documento.");
            return;
        }

        System.out.println("Ingrese el número de documento: ");
        String numDocumento = scanner.nextLine();

        System.out.println("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
        String fechaIngresada = scanner.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaSeleccionada = null;
        try {
            fechaSeleccionada = formato.parse(fechaIngresada);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, ingrese la fecha en formato yyyy-MM-dd.");
            return;
        }

        System.out.print("Ingrese el teléfono: ");
        String telefono = scanner.nextLine();

        System.out.println("Seleccione el País:");
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

        Huesped huesped = new Huesped(idHuesped, nombre, aPaterno, aMaterno, tipoSeleccionado, numDocumento, fechaSeleccionada, telefono, paisSeleccionado);
        boolean huespedInserted = this.huespedController.insertHuesped(huesped);

        if (huespedInserted) {
            System.out.println("Huésped insertado exitosamente! " + huesped.toString());
        } else {
            System.out.println("Ocurrió un error al insertar el huésped.");
        }
    }

    public void listarHuespedes(List<Huesped> lista) {
        System.out.println("Los huespedes que se encuentran registrados en el sistema son: ");
        for (Huesped huesped : lista) {
            System.out.println("-------------------------------------------------");
            huesped.mostrarInformacion();
            System.out.println("-------------------------------------------------");
        }

    }

    public void modificarHuesped() {
        listarHuespedes(todosLosHuespedes);
        System.out.println("Ingrese el ID del Huesped que desea modificar: ");
        int idAModificar = scanner.nextInt();

        Huesped huesped = huespedController.getHuespedById(idAModificar);

        // Si el huésped existe, procedemos con la modificación
        if (huesped != null) {
            System.out.println("Huesped encontrado: " + huesped.getNombre());

            // Modificar nombre
            System.out.println("Ingrese nuevo nombre (deje en blanco para no modificar): ");
            scanner.nextLine();  // Consumir salto de línea pendiente
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                huesped.setNombre(nuevoNombre);
            }

            // Modificar apellido paterno
            System.out.println("Ingrese nuevo apellido paterno (deje en blanco para no modificar): ");
            String nuevoAPaterno = scanner.nextLine();
            if (!nuevoAPaterno.isEmpty()) {
                huesped.setaPaterno(nuevoAPaterno);
            }

            // Modificar apellido materno
            System.out.println("Ingrese nuevo apellido materno (deje en blanco para no modificar): ");
            String nuevoAMaterno = scanner.nextLine();
            if (!nuevoAMaterno.isEmpty()) {
                huesped.setaMaterno(nuevoAMaterno);
            }

            // Modificar tipo de documento
            System.out.println("¿Desea modificar el tipo de documento? (S/N): ");
            String respuestaTipoDoc = scanner.nextLine();
            if (respuestaTipoDoc.equalsIgnoreCase("S")) {
                System.out.println("Seleccione el nuevo tipo de documento:");
                for (TipoDocumento tipo : tipoDocumentos) {
                    System.out.println("ID: " + tipo.getIdTipoDoc() + ", Nombre: " + tipo.getNombre());
                }
                int idTipoSeleccionado = scanner.nextInt();
                scanner.nextLine();  // Consumir salto de línea pendiente
                TipoDocumento tipoSeleccionado = null;

                for (TipoDocumento tipo : tipoDocumentos) {
                    if (tipo.getIdTipoDoc() == idTipoSeleccionado) {
                        tipoSeleccionado = tipo;
                        break;
                    }
                }
                if (tipoSeleccionado != null) {
                    huesped.setTipoDocumento(tipoSeleccionado);
                    System.out.println("Tipo de documento actualizado a: " + tipoSeleccionado.getNombre());
                } else {
                    System.out.println("ID no válido. No se encontró el tipo de documento.");
                }
            }

            // Modificar número de documento
            System.out.println("Ingrese nuevo número de documento (deje en blanco para no modificar): ");
            String nuevoNumDocumento = scanner.nextLine();
            if (!nuevoNumDocumento.isEmpty()) {
                huesped.setNumDocumento(nuevoNumDocumento);
            }


            // Modificar teléfono
            System.out.println("Ingrese nuevo teléfono (deje en blanco para no modificar): ");
            String nuevoTelefono = scanner.nextLine();
            if (!nuevoTelefono.isEmpty()) {
                huesped.setTelefono(nuevoTelefono);
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
                    huesped.setPais(paisSeleccionado);
                    System.out.println("País actualizado a: " + paisSeleccionado.getName());
                } else {
                    System.out.println("ID no válido. No se encontró el país.");
                }
            }

            // Una vez modificado el objeto, lo pasamos al DAO para actualizarlo en la base de datos
            boolean success = huespedDAO.modificarHuesped(huesped);

            if (success) {
                System.out.println("Huesped modificado correctamente.");
            } else {
                System.out.println("Error al modificar el huésped.");
            }
        } else {
            System.out.println("Huesped no encontrado.");
        }
    }

    public void eliminarHuesped() {
        // Listar todos los huéspedes registrados
        listarHuespedes(todosLosHuespedes);

        System.out.println("Ingrese el ID del Huésped que desea eliminar: ");
        int idAEliminar = scanner.nextInt();

        // Buscar el huésped por ID
        Huesped huesped = huespedController.getHuespedById(idAEliminar);

        // Si el huésped existe, proceder a eliminarlo
        if (huesped != null) {
            // Confirmar eliminación
            System.out.println("Huésped encontrado: " + huesped.getNombre());
            System.out.println("¿Está seguro de que desea eliminar este huésped? (s/n): ");
            scanner.nextLine();  // Consumir el salto de línea pendiente
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                // Eliminar huésped
                boolean eliminado = huespedController.eliminarHuesped(idAEliminar);
                if (eliminado) {
                    System.out.println("Huésped eliminado exitosamente.");
                } else {
                    System.out.println("Ocurrió un error al eliminar al huésped.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }
        } else {
            System.out.println("Huésped no encontrado.");
        }
    }



}
