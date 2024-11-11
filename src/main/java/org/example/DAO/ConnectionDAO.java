package org.example.DAO;

import java.sql.*;

public class ConnectionDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/obligatorio_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "magnesium";

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean executeUpdate(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            int rowsAffected = statement.executeUpdate();
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException ex) {
            System.out.println("Ocurrió un problema al conectarse con la base de datos.");

        }
        return false;
    }

    public ResultSet executeQuery(String query, Object... params) {

        ResultSet resultSet = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException ex) {
            System.out.println("No se pudo obtener los datos.");
            ex.printStackTrace();
        }
        return resultSet;
    }
}