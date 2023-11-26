package entidades.cliente;

public abstract class Cliente {
    protected String cpf;
    protected String nome;
    protected String telefone;
    protected String email;
    protected double gasto = 0;

    public Cliente(String cpf, String nome, String telefone, String email, double gasto) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.gasto = gasto;
    }

    public Cliente(){}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGasto() {
        return gasto;
    }

}
