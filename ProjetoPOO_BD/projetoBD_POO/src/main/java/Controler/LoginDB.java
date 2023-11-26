package Controler;
import java.sql.*;
import java.util.ArrayList;

import entidades.funcionario.Login;

public class LoginDB extends Database {
    public boolean insertLogin(Login login){
        connect();
        String sql = "INSERT INTO login(email, senha, idLogin) VALUES (?,?,?);";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, login.getEmail());
            pst.setString(2,login.getSenha());
            pst.setInt(3,login.getIdLogin());
            pst.execute();
            check = true;
        }

        catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public int researchLogin(){
        connect();
        String sql = "SELECT idLogin from login";
        int idLogin = 0;

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                idLogin = result.getInt("idLogin");
            }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return idLogin;
    }

    public int researchLogin(String email, String senha){
        connect();
        String sql = "SELECT idLogin from login where email=? and senha=?";
        int idLogin = 0;

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,email);
            pst.setString(2, senha);
            pst.execute();
            result = pst.executeQuery();


            while(result.next()){

                idLogin = result.getInt("idLogin");

             }

        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                result.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return idLogin;
    }

    public boolean updateLogin(Login login, int idLogin){
        connect();
        String sql = "UPDATE login SET email=?, senha=? WHERE idLogin=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, login.getEmail());
            pst.setString(2, login.getSenha());
            pst.setInt(3, idLogin);
            pst.execute();
            check = true;
            System.out.println("------------------------------------------------");
            System.out.println("DADOS DO LOGIN ALTERADO COM SUCESSO");
            System.out.println("------------------------------------------------");
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean deleteLogin(int idLogin){
        connect();
        String sql = "DELETE FROM login WHERE idLogin=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,idLogin);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            }
            catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }
}
