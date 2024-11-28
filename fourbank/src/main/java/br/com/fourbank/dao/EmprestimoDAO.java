package br.com.fourbank.dao;

import br.com.fourbank.model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
