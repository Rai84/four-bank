package br.com.fourbank.dao;

import br.com.fourbank.model.Caixinha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaixinhaDAO {

    public boolean criarCaixinha(int clienteId) throws SQLException {
        String SQL = "INSERT INTO caixinha (cliente_id, saldoCaixinha) VALUES (?, 0)"; // Inicializa com saldo 0
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, clienteId);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Retorna true se inseriu com sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propaga a exceção caso ocorra erro
        }
    }
    

    public boolean transferirParaCaixinha(int clienteId, double valor) {
        // Aqui, o valor será adicionado à caixinha
        String SQL = "UPDATE Caixinha SET saldoCaixinha = saldoCaixinha + ? WHERE cliente_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, clienteId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Caixinha obterCaixinhaPorCliente(int clienteId) {
        Caixinha caixinha = null;
        String SQL = "SELECT * FROM Caixinha WHERE cliente_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                caixinha = new Caixinha();
                caixinha.setCaixinhaId(resultSet.getInt("caixinha_id"));
                caixinha.setSaldoCaixinha(resultSet.getDouble("saldoCaixinha"));
                caixinha.setClienteId(resultSet.getInt("cliente_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return caixinha;
    }

    public boolean atualizarSaldoCaixinha(Caixinha caixinha) {
        String SQL = "UPDATE Caixinha SET saldoCaixinha = ? WHERE caixinha_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setDouble(1, caixinha.getSaldoCaixinha());
            preparedStatement.setInt(2, caixinha.getCaixinhaId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
