package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {

    public boolean verificarCpfExistente(String cpf) {
        String SQL = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            
            preparedStatement.setString(1, cpf);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createAccountForClient(String clienteCpf) {
    String SQL = "INSERT INTO conta (clienteCpf, saldo) VALUES (?, ?)"; // Ajuste os campos conforme necessário
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
         
        preparedStatement.setString(1, clienteCpf);
        preparedStatement.setDouble(2, 0.0); // Ou qualquer valor inicial que desejar
        
        return preparedStatement.executeUpdate() > 0; // Retorna true se uma linha foi inserida
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Retorna false em caso de erro
    }
}

}
