package br.com.fourbank.dao;

import br.com.fourbank.model.cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class clienteDAO {

    public void insert(cliente c) {
        String SQL = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, EMAIL, DATA_NASCIMENTO, SENHA) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                
            System.out.println("Conectado ao banco de dados com sucesso!");

            preparedStatement.setString(1, c.getName());
            preparedStatement.setString(2, c.getCpf());
            preparedStatement.setString(3, c.getEndereco());
            preparedStatement.setString(4, c.getTelefone());
            preparedStatement.setString(5, c.getEmail());
            preparedStatement.setString(6, c.getDataNascimento());
            preparedStatement.setString(7, c.getSenha());

            System.out.println("Inserindo dados no banco de dados...");
            preparedStatement.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }
    }
}
