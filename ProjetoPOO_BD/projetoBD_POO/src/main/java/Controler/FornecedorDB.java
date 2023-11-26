package Controler;

import java.sql.*;
import java.util.ArrayList;
import entidades.estabelecimento.Fornecedor;

public class FornecedorDB extends Database{
    public boolean insertFornecedor(Fornecedor fornecedor){
        connect();
        String sql = "INSERT INTO fornecedor(cnpj, endereco, nome, Caixa_id) VALUES (?,?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,fornecedor.getCnpj());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3,fornecedor.getNome());
            pst.setInt(4,fornecedor.Caixa_id);
            pst.execute();
            check = true;
            System.out.println("------------------------------------------------");
            System.out.println("FORNECEDOR CADASTRADO COM SUCESSO!");
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

    public int researchFornecedor(){
        connect();
        int aux = 0;
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * from fornecedor";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            System.out.println("------------- FORNECEDORES -------------");
            while(result.next()){
                Fornecedor fornecedorTemp = new Fornecedor(result.getString("cnpj"),result.getString("endereco"),result.getString("nome"));
                fornecedorTemp.setCnpj(result.getString("cnpj"));
                fornecedorTemp.Caixa_id = result.getInt("Caixa_id");
                System.out.println("Cnpj = " + fornecedorTemp.getCnpj());
                System.out.println("Endereço = " + fornecedorTemp.getEndereco());
                System.out.println("Nome = " + fornecedorTemp.getNome());
                if(fornecedorTemp.Caixa_id > 0)
                    System.out.println("Caixa_id = " + fornecedorTemp.Caixa_id);
                System.out.println("-------------------------------");
                fornecedores.add(fornecedorTemp);
                aux++;
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

        return aux;
    }

    public boolean updateFkFornecedor(Fornecedor fornecedor, String cnpj){
        connect();
        String sql = "UPDATE fornecedor SET endereco=?,nome=? WHERE cnpj=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, fornecedor.getEndereco());
            pst.setString(2, fornecedor.getNome());
            pst.setString(3, cnpj);
            pst.execute();
            check = true;
            System.out.println("------------------------------------------------");
            System.out.println("DADOS DO FORNECEDOR ALTERADO COM SUCESSO");
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

}
