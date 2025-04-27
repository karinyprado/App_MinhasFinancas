package com.example.minhasfinancas.model;

public class Gasto {
    private int id;
    private String descricao;
    private double valor;
    private String categoria;
    private String data;

    public Gasto() {}

    public Gasto(String descricao, double valor, String categoria, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
