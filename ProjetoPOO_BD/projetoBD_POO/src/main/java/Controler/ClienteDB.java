package Controler;

import entidades.cliente.Avulso;
import entidades.cliente.Cliente;
import entidades.cliente.Fidelidade;
import entidades.estabelecimento.Fornecedor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDB extends Database{
    public boolean insertClienteFidelidade(Fidelidade fidelidade){
        connect();
        String sql = "INSERT INTO cliente(cpf,nome,telefone,email, gasto) VALUES (?,?,?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,fidelidade.getCpf());
            pst.setString(2, fidelidade.getNome());
            pst.setString(3, fidelidade.getTelefone());
            pst.setString(4, fidelidade.getEmail());
            pst.setDouble(5, fidelidade.getGasto());
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

    public boolean insertClienteAvulso(Avulso avulso){
        connect();
        String sql = "INSERT INTO cliente(cpf,nome,telefone,email, gasto) VALUES (?,?,?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,avulso.getCpf());
            pst.setString(2, avulso.getNome());
            pst.setString(3, avulso.getTelefone());
            pst.setString(4, avulso.getEmail());
            pst.setDouble(5, avulso.getGasto());
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

    public double researchGastoCliente(String cpf){
        connect();

        double gasto = 0;
        String sql = "SELECT * from cliente where cpf=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,cpf);
            pst.execute();
            result = pst.executeQuery();

            while(result.next()){
                gasto = result.getDouble("gasto");
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

        return gasto;
    }

    public boolean updateGastoCliente(String cpf, double gasto){
        connect();
        String sql = "UPDATE cliente SET gasto=? WHERE cpf=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setDouble(1, gasto);
            pst.setString(2, cpf);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
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
        return check;
    }

    public boolean updateCliente(String cpf, String nome, String telefone, String email){
        connect();
        String sql = "UPDATE cliente SET nome=?, telefone=?, email=? WHERE cpf=?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, telefone);
            pst.setString(3, email);
            pst.setString(4, cpf);
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

    public void researchCliente(){
        connect();
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * from cliente";

        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while(result.next()){
                //Avulso auxiliar - o cliente pode ou não ser avulso
                Avulso avulsoTemp = new Avulso(result.getString("cpf"), result.getString("nome"),result.getString("telefone"),
                        result.getString("email"),result.getDouble("gasto"));
                clientes.add(avulsoTemp);
            }
            if(clientes.size() != 0){
                System.out.println("------------- CLIENTES -------------");
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println("Cpf = " + clientes.get(i).getCpf());
                    System.out.println("Nome = " + clientes.get(i).getNome());
                    System.out.println("Telefone = " + clientes.get(i).getTelefone());
                    System.out.println("Email = " + clientes.get(i).getEmail());
                    System.out.println("Gasto = " + clientes.get(i).getGasto());
                    System.out.println("-------------------------------");
                }
            }
            else{
                System.out.println("-------------------------------");
                System.out.println("Você não possui clientes.");
                System.out.println("-------------------------------");
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
