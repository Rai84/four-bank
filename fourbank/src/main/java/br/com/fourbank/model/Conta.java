package br.com.fourbank.model;

public class Conta {
    private int numeroConta;
    private double saldo;
    private String cpf;

    public Conta(int numeroConta, double saldo, String cpf) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cpf = cpf; // Corrigido aqui, deve ser clienteCpf
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

    public String getClienteCpf() { // Corrigido o tipo para String
        return cpf;
    }

    public void setClienteCpf(String cpf) { // Corrigido o tipo para String
        this.cpf = cpf;
    }
}
