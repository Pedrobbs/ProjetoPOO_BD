package Controler;
import entidades.estabelecimento.Funcionario;
import entidades.estabelecimento.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.zip.DeflaterInputStream;

public class ProdutoDB extends Database{
    public boolean insertProduto(Produto produto){
        connect();
        String sql = "INSERT INTO produto(nome, custo,preco,idProduto, Caixa_id, CLiente_cpf) VALUES (?,?,?,?,null ,null)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setDouble(2, produto.getCusto());
            pst.setDouble(3,produto.getPreco());
            pst.setInt(4,produto.getIdProduto());
            pst.execute();
            check = true;
            System.out.println("------------------------------------------------");
            System.out.println("PRODUTO CADASTRADO COM SUCESSO!");
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

    public double custoTotal(){
        connect();
        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * from produto";
        double custoTotal = 0;

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                Produto produtoTemp = new Produto(result.getString("nome"),result.getDouble("custo"),result.getDouble("preco"));
                custoTotal += produtoTemp.getCusto();
                produtos.add(produtoTemp);
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
        return custoTotal;
    }

    public boolean updateFkProduto(int idProduto, int Caixa_id, String Cliente_cpf){
        connect();
        String sql = "UPDATE produto SET Caixa_id=?, Cliente_cpf=? WHERE idProduto=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1, Caixa_id);
            pst.setString(2, Cliente_cpf);
            pst.setInt(3,idProduto);
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

    public double researchPrecoProduto(int idProduto){
        connect();

        Produto produto = new Produto();
        String sql = "SELECT * from produto where idProduto=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,idProduto);
            pst.execute();
            result = pst.executeQuery();

            while(result.next()){
                produto.setPreco(result.getDouble("preco"));
            }

        }
        catch(SQLException e){
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

        return produto.getPreco();
    }

    public void researchProduto(){
        connect();
        ArrayList<Produto> vendidos = new ArrayList<>();
        ArrayList<Produto> disponiveis = new ArrayList<>();
        String sql = "SELECT * from produto";

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Produto produtoTemp = new Produto(result.getInt("idProduto"), result.getString("nome"), result.getDouble("custo"),
                        result.getDouble("preco"), result.getInt("Caixa_id"), result.getString("Cliente_cpf"));
                if (produtoTemp.Caixa_id == 0)
                    disponiveis.add(produtoTemp);
                else
                    vendidos.add(produtoTemp);
            }
            if(disponiveis.size() != 0){
                System.out.println("---------- PRODUTOS DISPONÍVEIS ----------");
                for (int i = 0; i < disponiveis.size(); i++) {
                    System.out.println("Nome: " + disponiveis.get(i).getNome());
                    System.out.println("IdProduto: " + disponiveis.get(i).getIdProduto());
                    System.out.println("Custo Bruto: " + disponiveis.get(i).getCusto());
                    System.out.println("Preço: " + disponiveis.get(i).getPreco());
                    System.out.println("-------------------------------");
                }
                System.out.print("\n");
            }
            else {
                System.out.println("----------------------------------------");
                System.out.println("Você não possui produtos disponíveis.");
                System.out.println("----------------------------------------");

            }
            if(vendidos.size() != 0){
                System.out.println("---------- PRODUTOS VENDIDOS ----------");
                for (int i = 0; i < vendidos.size(); i++) {
                    System.out.println("Nome: " + vendidos.get(i).getNome());
                    System.out.println("IdProduto: " + vendidos.get(i).getIdProduto());
                    System.out.println("Custo Bruto: " + vendidos.get(i).getCusto());
                    System.out.println("Preço: " + vendidos.get(i).getPreco());
                    System.out.println("Caixa_id: " + vendidos.get(i).Caixa_id);
                    System.out.println("Cliente_cpf: " + vendidos.get(i).Cliente_cpf);
                    System.out.println("-------------------------------");
                }
                System.out.print("\n");
            }
            else {
                System.out.println("----------------------------------------");
                System.out.println("Você não possui produtos vendidos.");
                System.out.println("----------------------------------------");
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
    }
}
