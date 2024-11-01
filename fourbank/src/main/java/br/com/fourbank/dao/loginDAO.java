package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fourbank.model.Cliente; // Importar sua classe Cliente
import br.com.fourbank.model.Conta;   // Importar sua classe Conta

public class loginDAO {

    public boolean validarLogin(String cpf, String senha) {
        boolean isValid = false;

        String SQL = "SELECT * FROM cliente WHERE cpf = ? AND senha = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, senha);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                isValid = resultSet.next();  // Login bem-sucedido
            }

        } catch (SQLException e) {
            e.printStackTrace();  
        }

        return isValid;
    }

    public Cliente obterInformacoesCliente(String cpf) {
        Cliente cliente = null;
        String SQL = "SELECT * FROM cliente WHERE cpf = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(resultSet.getString("cpf"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setDataNascimento(resultSet.getString("data_nascimento"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();  
        }

        return cliente;
    }

    public Conta obterInformacoesConta(String cpf) {
    Conta conta = null;
    String SQL = "SELECT * FROM conta WHERE cpf_cliente = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

        preparedStatement.setString(1, cpf);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                conta = new Conta(
                    resultSet.getInt("numeroConta"), 
                    resultSet.getDouble("saldo"), 
                    resultSet.getInt("clienteId")
                ); // Passando os valores do ResultSet para o construtor
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return conta;
}


}
