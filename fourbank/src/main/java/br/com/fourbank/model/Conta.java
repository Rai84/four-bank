package br.com.fourbank.model;

public class Conta {
    private int numeroConta;
    private double saldo;
    private int clienteId;

    public Conta(int numeroConta, double saldo, int clienteId) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.clienteId = clienteId;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getClienteId() {
        return clienteId;
    }
}