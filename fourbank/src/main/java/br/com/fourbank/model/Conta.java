package br.com.fourbank.model;

public class Conta {
    private int conta_id;
    private int numero_conta;
    private double saldo;
    private int clienteId;

    public Conta() {
    }

    public Conta(int conta_id, int numero_conta, double saldo, int clienteId) {
        this.conta_id = conta_id;
        this.numero_conta = numero_conta;
        this.saldo = saldo;
        this.clienteId = clienteId;
    }

    public int getConta_id() {
        return conta_id;
    }

    public void setConta_id(int conta_id) {
        this.conta_id = conta_id;
    }

    public int getNumero_conta() {
        return numero_conta;
    }

    public void setNumero_conta(int numero_conta) {
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
}
