package entidades.cliente;

import Controler.FidelidadeDB;

public class Fidelidade extends Cliente {
    private int idFidelidade;   
    public String Cliente_cpf;
    FidelidadeDB fidelidadeDB = new FidelidadeDB();

    public Fidelidade(){
        idFidelidade = (fidelidadeDB.researchFidelidade() + 1);
    }

    public int getIdFidelidade() {
        return idFidelidade;
    }

}
