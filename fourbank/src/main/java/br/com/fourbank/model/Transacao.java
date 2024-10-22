package br.com.fourbank.model;

public class Transacao {
    private int idDestino;
    private String tipo;
    private double valor;
    private String data;
    
    public int getIdDestino() {
        return idDestino;
    }
    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
