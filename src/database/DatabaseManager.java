package database;
// conectar ao banco de dados

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                // Carregar o driver manualmente (necessário em algumas versões)
                Class.forName("com.mysql.cj.jdbc.Driver");

                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/rai2", "root", "root");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver JDBC não encontrado!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco de dados!");
                e.printStackTrace();
            }
        }
        return conexao;
    }
}
