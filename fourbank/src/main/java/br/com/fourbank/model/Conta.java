package br.com.fourbank.model;

public class Conta {
    private int conta_id;
    private int numeroConta;
    private double saldo;
    private int clienteId;

    public Conta() {
    }

    public Conta(int conta_id, int numeroConta, double saldo, int clienteId) {
        this.conta_id = conta_id;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.clienteId = clienteId;
    }

    public int getConta_id() {
        return conta_id;
    }

    public void setConta_id(int conta_id) {
        this.conta_id = conta_id;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
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
}
