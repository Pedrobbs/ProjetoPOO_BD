package entidades.estabelecimento;

public class Funcionario {
    private String cpf;
    private String nome;
    private double salario;
    private String endereco;
    public int Caixa_id;
    public int Login_idLogin;

    public Funcionario(String cpf, String nome, double salario, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Funcionario(){}

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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
