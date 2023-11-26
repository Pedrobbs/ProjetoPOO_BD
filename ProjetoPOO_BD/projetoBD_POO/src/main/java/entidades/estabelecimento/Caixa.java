package entidades.estabelecimento;

import Controler.CaixaDB;

public class Caixa {
    private int idCaixa = 0;
    private double lucro = 0;
    public String Funcionario_cpf;
    CaixaDB caixaDB = new CaixaDB();

    public Caixa(double lucro) {
        this.lucro = lucro;
    }

    public Caixa(String cpf){
        this.idCaixa = (caixaDB.researchCaixa() + 1);
        Funcionario_cpf = cpf;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public double getLucro() {
        return lucro;
    }

}
