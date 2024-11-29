package br.com.fourbank.model;

public class Caixinha {
    private int caixinhaId;
    private int clienteId;
    private double saldoCaixinha;

    // Construtor vazio (se necessário)
    public Caixinha() {
    }

    // Construtor com caixinhaId, clienteId e saldoCaixinha
    public Caixinha(int caixinhaId, int clienteId, double saldoCaixinha) {
        this.caixinhaId = caixinhaId;
        this.clienteId = clienteId;
        this.saldoCaixinha = saldoCaixinha;
    }

    // Construtor com clienteId e saldoCaixinha (sem caixinhaId)
    public Caixinha(int clienteId, double saldoCaixinha) {
        this.clienteId = clienteId;
        this.saldoCaixinha = saldoCaixinha;
    }

    // Getters e Setters
    public int getCaixinhaId() {
        return caixinhaId;
    }

    public void setCaixinhaId(int caixinhaId) {
        this.caixinhaId = caixinhaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getSaldoCaixinha() {
        return saldoCaixinha;
    }

    public void setSaldoCaixinha(double saldoCaixinha) {
        this.saldoCaixinha = saldoCaixinha;
    }
}
