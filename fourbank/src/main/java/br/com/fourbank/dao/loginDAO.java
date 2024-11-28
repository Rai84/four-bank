package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fourbank.model.Cliente;
import br.com.fourbank.model.Conta;

public class LoginDAO {

    public boolean validarLogin(String cpf, String senha) {
        boolean isValid = false;
        String SQL = "SELECT cliente_id FROM cliente WHERE cpf = ? AND senha = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, senha);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int clienteId = resultSet.getInt("cliente_id");
                    System.out.println("Cliente ID encontrado: " + clienteId); 
                    isValid = true;

                    Conta conta = obterInformacoesConta(clienteId);
                    if (conta != null) {
                        System.out.println("Conta encontrada: " + conta.getNumeroConta());
                    }
                }
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
                    cliente.setId(resultSet.getInt("cliente_id")); 
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setCpf(resultSet.getString("cpf"));
                    cliente.setEndereco(resultSet.getString("endereco"));
                    cliente.setTelefone(resultSet.getString("telefone"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setDataNascimento(resultSet.getString("data_nascimento"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public Conta obterInformacoesConta(int clienteId) {
        Conta conta = null;
        String SQL = "SELECT * FROM conta WHERE cliente_id = ?";

        System.out.println("Iniciando obtenção de conta para cliente_id: " + clienteId); 

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    conta = new Conta(
                        resultSet.getInt("conta_id"),
                        resultSet.getInt("numeroConta"),
                        resultSet.getDouble("saldo"),
                        resultSet.getInt("cliente_id")); 
                    System.out.println("Conta encontrada para cliente_id: " + clienteId); 
                    System.out.println("Detalhes da conta: " + conta.getNumeroConta() + " | Saldo: " + conta.getSaldo());
                } else {
                    System.out.println("Conta não encontrada para o cliente_id: " + clienteId);
                    System.out.println("Erro aqui: Não há conta associada ao cliente_id: " + clienteId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter informações da conta: " + e.getMessage());
            e.printStackTrace();
        }

        return conta;
    }
}
