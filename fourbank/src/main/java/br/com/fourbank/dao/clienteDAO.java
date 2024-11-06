package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fourbank.model.Cliente;

public class ClienteDAO {

    public boolean criarCliente(Cliente cliente) {
        String SQL = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, EMAIL, DATA_NASCIMENTO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDataNascimento());
            preparedStatement.setString(7, cliente.getSenha());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // Obter o ID gerado
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1)); // Supondo que você tenha um método setId na classe Cliente
                    }
                }
                return true; // Cliente criado com sucesso
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
        }
        return false; // Falha na criação do cliente
    }

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
        return false; // Se ocorrer erro ou não houver resultados
    }
}
