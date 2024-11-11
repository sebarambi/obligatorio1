package org.example.DAO;

import org.example.model.Ocupaciones;

public class OcupacionesDAO {
    private ConnectionDAO connectionDAO;

    public OcupacionesDAO() {

    }

    public boolean insertOcupacion(Ocupaciones ocupacion) {
        String query = "INSERT INTO Ocupaciones (idOcupacion, idHabitacion, idReserva, idHuesped, fecha_entrada, fecha_salida, reservada_por_sistema, precio) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return connectionDAO.executeUpdate(query,
                ocupacion.getIdOcupacion(),
                ocupacion.getHabitacion().getIdHabitacion(),
                ocupacion.getReservas() != null ? ocupacion.getReservas().getIdReserva() : null,
                ocupacion.getHuesped().getIdHuesped(),
                ocupacion.getFechaEntrada(),
                ocupacion.getFechaSalida(),
                ocupacion.isReservadoSistema(),
                ocupacion.getPrecio()
        );
    }


}
