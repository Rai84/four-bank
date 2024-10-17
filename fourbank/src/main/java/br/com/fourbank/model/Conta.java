package br.com.fourbank.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conta { // Renomeado para Conta
    private String numero_conta;
    private double saldo;
    private int clienteId;

    // Getters e Setters

    public String getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(String numero_conta) {
        this.numero_conta = numero_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String gerarNumeroConta(Connection conn) throws SQLException {
        String sql = "SELECT MAX(CAST(numero_conta AS INT)) AS max_numero FROM conta";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int proximoNumero = (rs.getInt("max_numero") == 0) ? 1 : rs.getInt("max_numero") + 1;
                this.numero_conta = String.format("%04d", proximoNumero);
            }
        }
        return this.numero_conta;
    }

    public void salvar(Connection conn) throws SQLException {
        String sql = "INSERT INTO conta (numero_conta, saldo, cliente_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.numero_conta);
            stmt.setDouble(2, this.saldo);
            stmt.setInt(3, this.clienteId);
            stmt.executeUpdate();
        }
    }
}
