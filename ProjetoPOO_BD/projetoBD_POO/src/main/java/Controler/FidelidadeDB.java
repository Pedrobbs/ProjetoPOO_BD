package Controler;

import entidades.cliente.Fidelidade;

import java.sql.*;

public class FidelidadeDB extends Database {
    public boolean insertFidelidade(Fidelidade fidelidade){
        connect();
        String sql = "INSERT INTO fidelidade(idFidelidade, Cliente_cpf) VALUES (?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,fidelidade.getIdFidelidade());
            pst.setString(2,fidelidade.getCpf());
            pst.execute();
            check = true;
            System.out.println("------------------------------------------------");
            System.out.println("CLIENTE FIDELIDADE CADASTRADO COM SUCESSO!");
            System.out.println("------------------------------------------------");
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

    public int researchFidelidade(){
        connect();
        String sql = "SELECT * from fidelidade order by idFidelidade";
        int id = 0;
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                id = result.getInt("idFidelidade");
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
        return id;
    }

    public boolean deleteFidelidade(String cpf){
        connect();
        String sql = "DELETE FROM fidelidade WHERE Cliente_cpf=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,cpf);
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
