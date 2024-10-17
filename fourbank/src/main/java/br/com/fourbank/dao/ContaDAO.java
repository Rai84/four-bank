package br.com.fourbank.dao;

import br.com.fourbank.model.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaDAO {
    
    public void insert(Conta c) {
        String SQL = "INSERT INTO CONTA (NUMERO_CONTA, SALDO) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                
            System.out.println("Conectado ao banco de dados com sucesso!");

            preparedStatement.setString(1, c.getNumero_conta());
            preparedStatement.setDouble(2, c.getSaldo());
            // Se necessário, adicione o CLIENTE_ID aqui
            // preparedStatement.setInt(3, c.getClienteId());

            System.out.println("Inserindo dados no banco de dados...");
            preparedStatement.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
}

