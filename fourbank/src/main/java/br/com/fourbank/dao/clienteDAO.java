package br.com.fourbank.dao;

import br.com.fourbank.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public void insert(Cliente c) {
        String SQL = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, EMAIL, DATA_NASCIMENTO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String generatedKeysSQL = "SELECT LAST_INSERT_ID()"; // Ajuste conforme necessário para obter o ID gerado
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                
            System.out.println("Conectado ao banco de dados com sucesso!");

            preparedStatement.setString(1, c.getNome());
            preparedStatement.setString(2, c.getCpf());
            preparedStatement.setString(3, c.getEndereco());
            preparedStatement.setString(4, c.getTelefone());
            preparedStatement.setString(5, c.getEmail());
            preparedStatement.setString(6, c.getDataNascimento());
            preparedStatement.setString(7, c.getSenha());

            System.out.println("Inserindo dados no banco de dados...");
            preparedStatement.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

            // Obter o ID gerado para o cliente
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int clienteId = generatedKeys.getInt(1);
                    // Criar conta automaticamente
                    new ContaDAO().createAccountForClient(clienteId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
}
