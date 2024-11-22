package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fourbank.model.Cliente;

public class ClienteDAO {

    // Método para criar um novo cliente
    public boolean criarCliente(Cliente cliente) {
        String SQL = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, EMAIL, DATA_NASCIMENTO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Define os parâmetros para o PreparedStatement
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getTelefone());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDataNascimento());
            preparedStatement.setString(7, cliente.getSenha());

            // Executa a inserção
            int affectedRows = preparedStatement.executeUpdate();

            // Verifica se a inserção foi bem-sucedida
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Obtém o ID gerado automaticamente
                        cliente.setId(generatedKeys.getInt(1));
                    }
                }
                return true; // Cliente criado com sucesso
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar cliente: " + e.getMessage());
            // Log ou tratamento de erro adicional pode ser feito aqui
        }
        return false; // Falha na criação do cliente
    }

    // Método para verificar se o CPF já existe
    public boolean verificarCpfExistente(String cpf) {
        String SQL = "SELECT COUNT(*) FROM cliente WHERE cpf = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Se o CPF já existir
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar CPF existente: " + e.getMessage());
            // Log ou tratamento de erro adicional pode ser feito aqui
        }
        return false; // Se ocorrer erro ou não houver CPF encontrado
    }
}
