package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cadastro;

public class CadastroRepository {
    private Connection conexao = DatabaseManager.getConexao();

    public void salvar(Cadastro cadastro) {
        String sql = "INSERT INTO cadastro (nome, CPF, endereco, telefone, email, data_nascimento, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cadastro.getNome());
            stmt.setString(2, cadastro.getCPF());
            stmt.setString(3, cadastro.getEndereco());
            stmt.setString(4, cadastro.getTelefone());
            stmt.setString(5, cadastro.getEmail());
            stmt.setString(6, cadastro.getData_nascimento());
            stmt.setString(7, cadastro.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cadastro buscarPorId(int id) {
        String sql = "SELECT * FROM cadastro WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cadastro(
                    rs.getString("nome"),
                    rs.getString("CPF"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("data_nascimento"),
                    rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
