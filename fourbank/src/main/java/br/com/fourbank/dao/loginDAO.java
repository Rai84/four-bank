package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDAO {

    public boolean validarLogin(String cpf, String senha) {
        boolean isValid = false;

        String SQL = "SELECT * FROM cliente WHERE cpf = ? AND senha = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, senha);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isValid = true;  // Login bem-sucedido
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Trate a exceção conforme necessário
        }

        return isValid;
    }
}
