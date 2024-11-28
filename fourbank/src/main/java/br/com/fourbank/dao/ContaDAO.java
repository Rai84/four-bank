package br.com.fourbank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fourbank.model.Conta;

public class ContaDAO {

    public Conta obterContaPorCliente(int clienteId) {
        Conta conta = null;
        String SQL = "SELECT * FROM conta WHERE cliente_id = ?";

        System.out.println("Procurando conta para o cliente_id: " + clienteId);

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                conta = new Conta();
                conta.setConta_id(resultSet.getInt("conta_id"));
                conta.setSaldo(resultSet.getDouble("saldo"));
                conta.setClienteId(resultSet.getInt("cliente_id"));

                System.out.println("Conta encontrada: conta_id = " + conta.getConta_id() + ", saldo = " + conta.getSaldo());
            } else {
                System.out.println("Nenhuma conta encontrada para o cliente_id: " + clienteId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conta;
    }

    public boolean atualizarSaldo(Conta conta) {
        String SQL = "UPDATE Conta SET saldo = ? WHERE conta_id = ?";

        System.out.println("Atualizando saldo para a conta_id: " + conta.getConta_id());
        System.out.println("Novo saldo: " + conta.getSaldo()); 

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setDouble(1, conta.getSaldo());
            preparedStatement.setInt(2, conta.getConta_id());

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Saldo atualizado com sucesso para a conta: " + conta.getConta_id());
            } else {
                System.out.println("Nenhuma linha atualizada para a conta: " + conta.getConta_id());
            }

            return rowsUpdated > 0; 
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false; 
        }
    }
}
