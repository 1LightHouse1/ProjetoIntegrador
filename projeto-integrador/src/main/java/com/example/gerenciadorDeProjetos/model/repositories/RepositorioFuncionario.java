package com.example.gerenciadorDeProjetos.model.repositories;

import com.example.gerenciadorDeProjetos.model.daos.FuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.github.hugoperlin.results.Resultado;

public class RepositorioFuncionario {
    private FuncionarioDAO funcionarioDAO;

    public RepositorioFuncionario(FuncionarioDAO funcionarioDAO){
        this.funcionarioDAO = funcionarioDAO;
    }

    public Resultado cadastrarFuncionario(String nome, String login, String email, String senha, String cpf, NivelDeAcesso cargo){
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
        
        return funcionarioDAO.verificaLogin(login, senha);
    }
}
