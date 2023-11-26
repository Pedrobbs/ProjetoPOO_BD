import Controler.*;
import entidades.cliente.Avulso;
import entidades.cliente.Fidelidade;
import entidades.estabelecimento.Caixa;
import entidades.estabelecimento.Fornecedor;
import entidades.estabelecimento.Funcionario;
import entidades.estabelecimento.Produto;
import entidades.funcionario.Login;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int idCaixaAux = 0; //Guarda qual caixa está logado.

        while (flag) {
            System.out.println("------------------------------------------------");
            System.out.println("                 Tela de login: ");
            System.out.println("------------------------------------------------");
            System.out.println("1 - Entrar;");
            System.out.println("2 - Novo cadastro;");
            System.out.println("------------------------------------------------");
            System.out.print("Digite sua opção: ");
            int op = sc.nextInt();
            System.out.println("------------------------------------------------");

            switch (op) {
                case 1:
                    try {
                        sc.nextLine();
                        LoginDB lBD = new LoginDB();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Senha: ");
                        String senha = sc.nextLine();
                        if (lBD.researchLogin(email, senha) != 0) {
                            FuncionarioDB funcionarioDB = new FuncionarioDB();
                            idCaixaAux = funcionarioDB.researchFuncionarioIdCaixaComLogin(lBD.researchLogin(email, senha));
                            flag = false;
                            System.out.println("------------------------------------------------\n");
                        } else {
                            System.out.println("------------------------------------------------");
                            System.out.println("Senha ou email incorreto.");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Login não encontrado!");
                    }
                    break;
                case 2:
                    try {
                        sc.nextLine();
                        Login login = new Login();
                        LoginDB loginDB = new LoginDB();
                        System.out.print("Digite o email: ");
                        login.setEmail(sc.nextLine());
                        System.out.print("Digite uma senha: ");
                        login.setSenha(sc.nextLine());
                        loginDB.insertLogin(login);

                        Funcionario funcionario = new Funcionario();
                        FuncionarioDB funcionarioDB = new FuncionarioDB();
                        System.out.print("Digite o nome do novo funcionário: ");
                        funcionario.setNome(sc.nextLine());
                        System.out.print("Digite o cpf do novo funcionário: ");
                        funcionario.setCpf(sc.nextLine());
                        System.out.print("Digite o salário do novo funcionário: ");
                        funcionario.setSalario(sc.nextDouble());
                        sc.nextLine();
                        System.out.print("Digite o endereço do novo funcionário: ");
                        funcionario.setEndereco(sc.nextLine());
                        Caixa caixa = new Caixa(funcionario.getCpf());
                        CaixaDB caixaDB = new CaixaDB();
                        caixaDB.insertCaixa(caixa);
                        funcionario.Caixa_id = caixa.getIdCaixa();
                        funcionario.Login_idLogin = login.getIdLogin();
                        funcionarioDB.insertFuncionario(funcionario);
                    } catch (Exception e) {
                        System.out.println("Erro ao fazer o novo cadastro!" + e);
                    }
                    break;
                default:
                    System.out.println("Não existe essa opção, tente novamente!");
                    break;
            }
        }
        flag = true;

        while (flag) {
            System.out.println("\n------------------------------------------------");
            System.out.println("                  Bem vindo!");
            System.out.println("------------------------------------------------");
            System.out.println("O que você gostaria de fazer, hoje? ");
            System.out.println("1 - Adicionar novo cliente;");
            System.out.println("2 - Adicionar novo fornecedor;");
            System.out.println("3 - Adicionar novo produto;");
            System.out.println("4 - Compra feita por um cliente;");
            System.out.println("5 - Mostrar fornecedores;");
            System.out.println("6 - Mostrar lucro bruto ganho;");
            System.out.println("7- Mostrar total das despesas;");
            System.out.println("8 - Mostrar lucro líquido ganho;");
            System.out.println("9 - Mostrar todos os funcionários;");
            System.out.println("10 - Mostrar todos os cliente;");
            System.out.println("11 - Mostrar todos os produtos;");
            System.out.println("12 - Alterar cadastro do cliente;");
            System.out.println("13 - Alterar cliente avulso para fidelidade.");
            System.out.println("14 - Alterar cliente fidelidade para avulso.");
            System.out.println("15 - Alterar cadastro do fornecedor;");
            System.out.println("16 - Alterar cadastro do funcionário;");
            System.out.println("17 - Alterar login;");
            System.out.println("18 - Deletar funcionário;");
            System.out.println("19 - Sair;");
            System.out.println("------------------------------------------------");
            System.out.print("Opção: ");
            int op = sc.nextInt();
            System.out.println("------------------------------------------------");

            switch (op) {
                case 1: //Adicionar novo cliente
                    System.out.println(" O cliente será fidelidade ou avulso: ");
                    System.out.println("1 - Fidelidade");
                    System.out.println("2 - Avulso");
                    System.out.println("------------------------------------------------");
                    System.out.print("Opção: ");
                    int opAux1 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("------------------------------------------------");

                    if (opAux1 == 1) {
                        Fidelidade f = new Fidelidade();
                        ClienteDB clienteDB = new ClienteDB();
                        FidelidadeDB fidelidadeDB = new FidelidadeDB();

                        System.out.print("Digite o cpf do cliente: ");
                        f.setCpf(sc.nextLine());
                        System.out.print("Digite o nome do cliente: ");
                        f.setNome(sc.nextLine());
                        System.out.print("Digite o telefone do cliente:");
                        f.setTelefone(sc.nextLine());
                        System.out.print("Digite o email do cliente:");
                        f.setEmail(sc.nextLine());
                        clienteDB.insertClienteFidelidade(f);
                        fidelidadeDB.insertFidelidade(f);
                    } else if (opAux1 == 2) {
                        Avulso a = new Avulso();
                        AvulsoDB avulsoDB = new AvulsoDB();
                        ClienteDB clienteDB = new ClienteDB();

                        System.out.print("Digite o cpf do cliente: ");
                        a.setCpf(sc.nextLine());
                        System.out.print("Digite o nome do cliente: ");
                        a.setNome(sc.nextLine());
                        System.out.print("Digite o telefone do cliente:");
                        a.setTelefone(sc.nextLine());
                        System.out.print("Digite o email do cliente:");
                        a.setEmail(sc.nextLine());
                        clienteDB.insertClienteAvulso(a);
                        avulsoDB.insertAvulso(a);

                    } else {
                        System.out.println("Opção invalida");
                    }
                    break;
                case 2: //Adicionar novo fornecedor
                    sc.nextLine();
                    Fornecedor forn = new Fornecedor();
                    FornecedorDB fornDB = new FornecedorDB();
                    System.out.print("Digite o cnpj do novo fornecedor: ");
                    forn.setCnpj(sc.nextLine());
                    System.out.print("Digite o nome do fornecedor: ");
                    forn.setNome(sc.nextLine());
                    System.out.print("Digite o endereço do fornecedor: ");
                    forn.setEndereco(sc.nextLine());
                    forn.Caixa_id = idCaixaAux;
                    fornDB.insertFornecedor(forn);
                    break;
                case 3: //Adicionar novo produto
                    Produto p = new Produto();
                    ProdutoDB proDB = new ProdutoDB();
                    System.out.print("Digite o id do novo produto: ");
                    p.setIdProduto(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Digite o nome do produto: ");
                    p.setNome(sc.nextLine());
                    System.out.print("Digito o custo do produto bruto: ");
                    p.setCusto(sc.nextDouble());
                    System.out.print("Digite o preço do produto: ");
                    p.setPreco(sc.nextDouble());
                    proDB.insertProduto(p);
                    break;
                case 4: //Compra feita por um cliente
                    FuncionarioDB funcionarioDB1 = new FuncionarioDB();
                    try {
                        sc.nextLine();
                        System.out.print("Digite o cpf do cliente: ");
                        String cpf = sc.nextLine();
                        System.out.print("Digite o idProduto: ");
                        int idProduto = sc.nextInt();
                        funcionarioDB1.comprar(cpf, idProduto, idCaixaAux);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    break;
                case 5: //Mostrar fornecedores
                    FornecedorDB fornecedorDB = new FornecedorDB();
                    if (fornecedorDB.researchFornecedor() == 0)
                        System.out.println("A loja ainda não possui fornecedor.");
                    break;
                case 6: //Mostrar lucro bruto ganho
                    CaixaDB caixaDB1 = new CaixaDB();
                    double lucrototal = caixaDB1.calculoDoLucro();
                    try {
                        if (lucrototal == 0)
                            System.out.println("A loja ainda não possui lucro");
                        else
                            System.out.println("Lucro total: " + lucrototal);
                        System.out.println("------------------------------------------------");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    break;
                case 7: //Mostrar total das despesas
                    CaixaDB caixaDB2 = new CaixaDB();
                    double despesas = caixaDB2.despesasTotais();
                    try {
                        if (despesas == 0)
                            System.out.println("A loja ainda não possui despesas");
                        else
                            System.out.println("Despesa total: " + despesas);
                        System.out.println("------------------------------------------------");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    break;
                case 8: //Mostrar lucro liquído ganho
                    CaixaDB caixaDB3 = new CaixaDB();
                    double lucroLiquido = caixaDB3.lucroliquido();
                    try {
                        if (lucroLiquido == 0)
                            System.out.println("A loja ainda não possui um lucro líquido");
                        else
                            System.out.println("Lucro total: " + lucroLiquido);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e);
                    }
                    break;
                case 9: //Mostrar todos os funcionários
                    FuncionarioDB funcionarioDB3 = new FuncionarioDB();
                    funcionarioDB3.researchFuncionario();
                    break;
                case 10: //Mostrar todos os clientes
                    ClienteDB clienteDB = new ClienteDB();
                    clienteDB.researchCliente();
                    break;
                case 11: //Mostrar todos os produtos
                    ProdutoDB produtoDB = new ProdutoDB();
                    produtoDB.researchProduto();
                    break;
                case 12: //Alterar cadastro do cliente
                    sc.nextLine();
                    ClienteDB clienteDB1 = new ClienteDB();

                    System.out.print("Digite o cpf do cliente: ");
                    String cpf = sc.nextLine();
                    System.out.print("Digite o nome do cliente: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite o telefone do cliente:");
                    String telefone = sc.nextLine();
                    System.out.print("Digite o email do cliente:");
                    String email = sc.nextLine();
                    System.out.println("------------------------------------------------");
                    clienteDB1.updateCliente(cpf, nome, telefone, email);
                    break;
                case 13: //Alterar cliente avulso para fidelidade
                    sc.nextLine();
                    AvulsoDB avulsoDB = new AvulsoDB();
                    FidelidadeDB fidelidadeDB = new FidelidadeDB();
                    Fidelidade paraFidelidade = new Fidelidade();
                    System.out.print("Digite o cpf do cliente: ");
                    String cpfAvu = sc.nextLine();
                    avulsoDB.deleteAvulso(cpfAvu);
                    paraFidelidade.setCpf(cpfAvu);
                    fidelidadeDB.insertFidelidade(paraFidelidade);
                    break;
                case 14: //Alterar cliente fidelidade para avulso
                    sc.nextLine();
                    AvulsoDB avulsoDB1 = new AvulsoDB();
                    FidelidadeDB fidelidadeDB1 = new FidelidadeDB();
                    Avulso paraAvulso = new Avulso();
                    System.out.print("Digite o cpf do cliente: ");
                    String cpfFid = sc.nextLine();
                    fidelidadeDB1.deleteFidelidade(cpfFid);
                    paraAvulso.setCpf(cpfFid);
                    avulsoDB1.insertAvulso(paraAvulso);
                    break;
                case 15: //Alterar cadastro do fornecedor
                    sc.nextLine();
                    Fornecedor forne = new Fornecedor();
                    FornecedorDB forneDB = new FornecedorDB();
                    System.out.print("Digite o cnpj do fornecedor: ");
                    forne.setCnpj(sc.nextLine());
                    System.out.println("------- NOVOS DADOS -------");
                    System.out.print("Digite o nome do fornecedor: ");
                    forne.setNome(sc.nextLine());
                    System.out.print("Digite o endereço do fornecedor: ");
                    forne.setEndereco(sc.nextLine());
                    forneDB.updateFkFornecedor(forne, forne.getCnpj());
                    break;
                case 16: //Alterar cadastro do funcionário
                    sc.nextLine();
                    Funcionario funcionario = new Funcionario();
                    FuncionarioDB funcionarioDB4 = new FuncionarioDB();
                    System.out.print("Digite o cpf do funcionário: ");
                    funcionario.setCpf(sc.nextLine());
                    System.out.println("------- NOVOS DADOS -------");
                    System.out.print("Digite o nome do novo funcionário: ");
                    funcionario.setNome(sc.nextLine());
                    System.out.print("Digite o salário do novo funcionário: ");
                    funcionario.setSalario(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Digite o endereço do novo funcionário: ");
                    funcionario.setEndereco(sc.nextLine());
                    funcionarioDB4.updateFuncionario(funcionario, funcionario.getCpf());
                    break;
                case 17: //Alterar login
                    sc.nextLine();
                    Login login = new Login();
                    LoginDB loginDB = new LoginDB();
                    System.out.print("Digite o email atual: ");
                    String emailAntigo = sc.nextLine();
                    System.out.print("Digite a senha atual: ");
                    String senhaAntiga = sc.nextLine();

                    System.out.print("Digite o novo email: ");
                    login.setEmail(sc.nextLine());
                    System.out.print("Digite uma nova senha: ");
                    login.setSenha(sc.nextLine());
                    loginDB.updateLogin(login, loginDB.researchLogin(emailAntigo, senhaAntiga));
                    break;
                case 18: //Deletar funcionário
                    sc.nextLine();
                    FuncionarioDB funcionarioDB5 = new FuncionarioDB();
                    System.out.print("Digite o cpf: ");
                    String cpfFuncionario = sc.nextLine();

                    funcionarioDB5.updateFuncionarioDemitido(funcionarioDB5.researchFuncionarioIdLogin(cpfFuncionario), cpfFuncionario);
                    break;
                case 19: //Sair
                    flag = false;
                    System.out.println("VOCÊ SAIU, TENHA UM BOM DIA!");
                    System.out.println("------------------------------------------------");
                    break;
                default:
                    System.out.println("Não existe essa opção, tente novamente!");
                    System.out.println("------------------------------------------------");
                    break;
            }
        }
    }
}
