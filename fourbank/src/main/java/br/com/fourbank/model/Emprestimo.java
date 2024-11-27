package br.com.fourbank.model;

public class Emprestimo {
    private int clienteId;
    private double valor;
    private java.sql.Date dataEmprestimo;
    private java.sql.Date dataVencimento;
    private int parcelas;
    
    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
    
    private String status;

    // Getters e Setters
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public java.sql.Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(java.sql.Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public java.sql.Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(java.sql.Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
