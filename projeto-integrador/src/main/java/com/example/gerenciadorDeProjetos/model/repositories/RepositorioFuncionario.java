package com.example.gerenciadorDeProjetos.model.repositories;

import com.example.gerenciadorDeProjetos.model.daos.FuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.github.hugoperlin.results.Resultado;
import com.mysql.cj.log.Log;

public class RepositorioFuncionario {
    private FuncionarioDAO funcionarioDAO;

    public RepositorioFuncionario(FuncionarioDAO funcionarioDAO){
        this.funcionarioDAO = funcionarioDAO;
    }

    public Resultado cadastrarFuncionarios(String nome, String login, String email, String senha, String cpf, NivelDeAcesso cargo){
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome Inválido");
        }

        if(login.isBlank() || login.isEmpty()){
            return Resultado.erro("Login Inválido");
        }

        if(email.isBlank() || email.isEmpty()){
            return Resultado.erro("Email Inválido");
        }
        
        if(senha.isBlank() || senha.isEmpty()){
            return Resultado.erro("Senha Inválida");
        }

        if(cpf.isBlank() || cpf.isEmpty()){
            return Resultado.erro("CPF Inválido");
        }

        if(cargo == null){
            return Resultado.erro("Cargo Inválida");
        }

        Resultado resultado = funcionarioDAO.verificaEstruturaEmail(email);

        if(resultado.foiErro()){

            return resultado;
        }

        Funcionario funcionario = new Funcionario(cpf, nome, cargo, login, senha, email);

        return funcionarioDAO.criar(funcionario);
                

    }

    public Resultado login(String login, String senha){

        if(login.isBlank() || login.isEmpty()){
            return Resultado.erro("Campo login inválido");
        }

        if(senha.isBlank() || senha.isEmpty()){
            return Resultado.erro("Campo senha nválida");
        }

        Resultado rs = funcionarioDAO.verificaLogin(login, senha);

        if(rs.foiSucesso()){
            Funcionario funcionario = (Funcionario)rs.comoSucesso().getObj();
            Login.setFuncionarioAtual(funcionario);
        }
        
        return rs;
    }

    public Funcionario funcionarioLogado() {
        if (Login.estaLogado() == false) {
            return null;
        }
    
        Funcionario funcionarioLogado = Login.getFuncionarioAtual();
        return funcionarioLogado;
    }

    public Resultado temPermissao(){
        Funcionario funcionario = Login.getFuncionarioAtual();


        return  funcionarioDAO.verificaPermissao(funcionario.getId());
    }

    public Resultado buscarFuncionarioProjeto(int idProjeto) {
        return funcionarioDAO.buscarFuncionarioProjeto(idProjeto);
    }

    public Resultado buscarFuncionarioTarefa(int idTarefa) {
        return funcionarioDAO.buscarFuncionarioTarefa(idTarefa);
    }
    
    
}
