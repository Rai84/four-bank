package br.com.fourbank.dao;

import br.com.fourbank.model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmprestimoDAO {

    public boolean adicionarEmprestimo(Emprestimo emprestimo) {
        String SQL = "INSERT INTO emprestimo (cliente_id, valor, data_emprestimo, parcelas, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, emprestimo.getClienteId());
            preparedStatement.setDouble(2, emprestimo.getValor());
            preparedStatement.setDate(3, emprestimo.getDataEmprestimo());
            preparedStatement.setInt(4, emprestimo.getParcelas());
            preparedStatement.setString(5, "PENDENTE");

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Se foi inserido com sucesso
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
