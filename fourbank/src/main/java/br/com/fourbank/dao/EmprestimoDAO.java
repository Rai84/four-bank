package br.com.fourbank.dao;

import br.com.fourbank.model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmprestimoDAO {

    public boolean adicionarEmprestimo(Emprestimo emprestimo) {
        String SQL = "INSERT INTO EMPRESTIMO (cliente_id, valor_emprestimo, data_emprestimo, data_vencimento, parcelas) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, emprestimo.getClienteId());
            preparedStatement.setDouble(2, emprestimo.getValor());
            preparedStatement.setDate(3, emprestimo.getDataEmprestimo());
            preparedStatement.setDate(4, emprestimo.getDataVencimento());
            preparedStatement.setInt(5, emprestimo.getParcelas());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Emprestimo obterEmprestimoPorCliente(int clienteId) {
        Emprestimo emprestimo = null;
        String SQL = "SELECT * FROM EMPRESTIMO WHERE cliente_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, clienteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                emprestimo = new Emprestimo();
                emprestimo.setClienteId(resultSet.getInt("cliente_id"));
                emprestimo.setValor(resultSet.getDouble("valor_emprestimo"));
                emprestimo.setDataEmprestimo(resultSet.getDate("data_emprestimo"));
                emprestimo.setDataVencimento(resultSet.getDate("data_vencimento"));
                emprestimo.setParcelas(resultSet.getInt("parcelas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimo;
    }

}
