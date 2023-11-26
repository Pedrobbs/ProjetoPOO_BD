package entidades.estabelecimento;

import Controler.ProdutoDB;

public class Produto {
    private int idProduto;
    private String nome;
    private double custo;
    private double preco;
    public int Caixa_id;
    public String Cliente_cpf;

    public Produto(String nome, double custo, double preco) {
        this.nome = nome;
        this.custo = custo;
        this.preco = preco;
    }

    public Produto(int idProduto, String nome, double custo, double preco, int caixa_id, String cliente_cpf) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.custo = custo;
        this.preco = preco;
        Caixa_id = caixa_id;
        Cliente_cpf = cliente_cpf;
    }

    public Produto(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
