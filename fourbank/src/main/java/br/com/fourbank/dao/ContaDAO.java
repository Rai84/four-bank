package br.com.fourbank.dao;

import br.com.fourbank.model.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContaDAO {

    public void createAccountForClient(int clienteId) {
        String SQL = "INSERT INTO CONTA (NUMEROCONTA, SALDO, CLIENTE_ID) VALUES (NEXT VALUE FOR conta_num_seq, 0.00, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                
            preparedStatement.setInt(1, clienteId);
            preparedStatement.executeUpdate();
            System.out.println("Conta criada com sucesso para o cliente ID: " + clienteId);
        } catch (SQLException e) {
            System.out.println("Erro ao criar conta para o cliente!");
            e.printStackTrace();
        }
    }
}