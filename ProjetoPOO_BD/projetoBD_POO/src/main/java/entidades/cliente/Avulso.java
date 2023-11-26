package entidades.cliente;

import Controler.AvulsoDB;

public class Avulso extends Cliente {
    private int idAvulso;
    public String Cliente_cpf;
    AvulsoDB avulsoDB = new AvulsoDB();

    public Avulso(String cpf, String nome, String telefone, String email, double gasto) {
        super(cpf, nome, telefone, email, gasto);
        Cliente_cpf = cpf;
    }

    public Avulso(){
        idAvulso = (avulsoDB.researchAvulso() + 1);
    }

    public int getIdAvulso() {
        return idAvulso;
    }

}
