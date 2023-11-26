package entidades.funcionario;

import Controler.LoginDB;

public class Login {
    private int idLogin = 0;
    private String email;
    private String senha;
    LoginDB loginDB = new LoginDB();

    public Login() {
        idLogin = (loginDB.researchLogin() + 1);
    }

    public int getIdLogin() {
        return idLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
