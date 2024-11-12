package org.example.view;

import org.example.controller.HuespedController;
import org.example.controller.PaisController;
import org.example.controller.TipoDocumentoController;
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
    private PaisController paisController;
    private TipoDocumentoController tipoDocumentoController;

    List<Pais> paises;
    List<TipoDocumento> tipoDocumentos;


    public HuespedView() {

        this.huespedController = new HuespedController();
        this.paisController = new PaisController();
        this.tipoDocumentoController = new TipoDocumentoController();

        // Cargar los datos necesarios después de inicializar los controladores
        this.paises = paisController.obtenerTodosLosPaises();
        this.tipoDocumentos = tipoDocumentoController.listarTiposDocumento();
    }

    public void menuHuesped() {
        int opcion;

        do {
            System.out.println("===== MENU DE HUESPEDES =====");
            System.out.println("Opción 1: Insertar Huespedes");
            System.out.println("Opción 2: Ver todos los Huespedes");
            System.out.println("Opción 3: Modificar Huesped por ID");
            System.out.println("Opción 4: Eliminar Huesped por ID");
            System.out.println("Opción 0: Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    insertHuesped();
                    break;
                case 2:
                    List<Huesped> huespedesAListar = huespedController.listarHuespedes();
                    listarHuespedes(huespedesAListar);
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
        } while (opcion != 0);
    }

    // Métodos ---------------------------------------------------------------------------------------------------

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
        List<Huesped> listaAModificar = huespedController.listarHuespedes();
        listarHuespedes(listaAModificar);
        System.out.println("Ingrese el ID del Huesped que desea modificar: ");
        int idAModificar = scanner.nextInt();

        Huesped huesped = huespedController.getHuespedById(idAModificar);

        if (huesped != null) {
            System.out.println("Huesped encontrado: " + huesped.getNombre());


            System.out.println("Ingrese nuevo nombre (deje en blanco para no modificar): ");
            scanner.nextLine();
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                huesped.setNombre(nuevoNombre);
            }


            System.out.println("Ingrese nuevo apellido paterno (deje en blanco para no modificar): ");
            String nuevoAPaterno = scanner.nextLine();
            if (!nuevoAPaterno.isEmpty()) {
                huesped.setaPaterno(nuevoAPaterno);
            }


            System.out.println("Ingrese nuevo apellido materno (deje en blanco para no modificar): ");
            String nuevoAMaterno = scanner.nextLine();
            if (!nuevoAMaterno.isEmpty()) {
                huesped.setaMaterno(nuevoAMaterno);
            }

            System.out.println("¿Desea modificar el tipo de documento? (S/N): ");
            String respuestaTipoDoc = scanner.nextLine();
            if (respuestaTipoDoc.equalsIgnoreCase("S")) {
                System.out.println("Seleccione el nuevo tipo de documento:");
                for (TipoDocumento tipo : tipoDocumentos) {
                    System.out.println("ID: " + tipo.getIdTipoDoc() + ", Nombre: " + tipo.getNombre());
                }
                int idTipoSeleccionado = scanner.nextInt();
                scanner.nextLine();
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

            System.out.println("Ingrese nuevo número de documento (deje en blanco para no modificar): ");
            String nuevoNumDocumento = scanner.nextLine();
            if (!nuevoNumDocumento.isEmpty()) {
                huesped.setNumDocumento(nuevoNumDocumento);
            }

            System.out.println("Ingrese nuevo teléfono (deje en blanco para no modificar): ");
            String nuevoTelefono = scanner.nextLine();
            if (!nuevoTelefono.isEmpty()) {
                huesped.setTelefono(nuevoTelefono);
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
                    huesped.setPais(paisSeleccionado);
                    System.out.println("País actualizado a: " + paisSeleccionado.getName());
                } else {
                    System.out.println("ID no válido. No se encontró el país.");
                }
            }


            boolean huespedUpdated = huespedController.modificarHuesped(huesped);

            if (huespedUpdated) {
                System.out.println("Huésped modificado exitosamente!");
            } else {
                System.out.println("Ocurrió un error al modificar el huésped.");
            }
        } else {
            System.out.println("No se encontró el huésped con el ID proporcionado.");
        }
    }

    public void eliminarHuesped() {
        List<Huesped> listaAEliminar = huespedController.listarHuespedes();
        listarHuespedes(listaAEliminar);
        System.out.println("Ingrese el ID del Huesped que desea eliminar: ");
        int idAEliminar = scanner.nextInt();

        boolean eliminado = huespedController.eliminarHuesped(idAEliminar);

        if (eliminado) {
            System.out.println("Huésped eliminado exitosamente.");
        } else {
            System.out.println("No se pudo eliminar el huésped con ID " + idAEliminar);
        }
    }
}
