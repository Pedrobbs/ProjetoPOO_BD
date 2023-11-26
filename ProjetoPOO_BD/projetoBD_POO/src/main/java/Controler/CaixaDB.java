package Controler;

import entidades.estabelecimento.Caixa;

import java.sql.*;
import java.util.ArrayList;

public class CaixaDB extends Database{
    public boolean insertCaixa(Caixa caixa){
        connect();
        String sql = "INSERT INTO caixa(id, lucro, Funcionario_cpf) VALUES (?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,caixa.getIdCaixa());
            pst.setDouble(2,caixa.getLucro());
            pst.setString(3,caixa.Funcionario_cpf);
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

    public int researchCaixa(){
        connect();
        String sql = "SELECT id from caixa";
        int idCaixa = 0;

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                idCaixa = result.getInt("id");
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
        return idCaixa;
    }

    public double researchGastoTotalCaixa(int id){
        connect();
        String sql = "SELECT * from caixa WHERE id=?";
        double gastoTotal = 0;

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            result = pst.executeQuery();

            while(result.next()){
                gastoTotal = result.getDouble("lucro");
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

        return gastoTotal;
    }

    public double calculoDoLucro(){
        connect();
        ArrayList<Caixa> caixas = new ArrayList<>();
        String sql = "SELECT * from caixa";
        double lucroTotal = 0;

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Caixa caixaTemp = new Caixa(result.getDouble("lucro"));
                lucroTotal += caixaTemp.getLucro();
                caixas.add(caixaTemp);
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
        return lucroTotal;
    }

    public double lucroliquido(){
        try {
            return (calculoDoLucro() - despesasTotais());
        }catch(NullPointerException e){
            System.out.println("Erro ao calcular o lucro líquido!");
            return 0;
        }
    }

    public double despesasTotais(){
        try {
            ProdutoDB produtoDB = new ProdutoDB();
            FuncionarioDB funcionarioDB = new FuncionarioDB();

            return (produtoDB.custoTotal() + funcionarioDB.custoSalario());
        }catch(NullPointerException e){
            System.out.println("Erro ao calcular as despesas!");
            return 0;
        }

    }

    public boolean updateFkCaixa(int id, double lucro){
        connect();
        String sql = "UPDATE caixa SET lucro=? WHERE id=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, lucro);
            pst.setInt(2, id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            System.out.println("Cliente não encontrado!");
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
