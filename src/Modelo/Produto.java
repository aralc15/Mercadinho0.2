package Modelo;


import Utils.utils;

public class Produto {

    // Criando um contador para adcinoar e listar produtos
    private static int count = 1;
    private int IDP;
    private String nome;
    private Double preco;

    public Produto(String nome, Double preco) {
        this.IDP = count;
        this.nome = nome;
        this.preco = preco;
        Produto.count += 1;
    }

    public int getId() {
        return IDP;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public String toString (){
        return "Id: " + this.getId() +
                "\nNome: " + this.getNome() +
                "\nPreço: " + utils.doubleToString(this.getPreco());
    }
}