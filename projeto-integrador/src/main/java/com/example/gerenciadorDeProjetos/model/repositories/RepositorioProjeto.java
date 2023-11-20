package com.example.gerenciadorDeProjetos.model.repositories;

import java.time.LocalDate;

import com.example.gerenciadorDeProjetos.model.daos.ProjetoDAO;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.github.hugoperlin.results.Resultado;

public class RepositorioProjeto {
    
    private ProjetoDAO projetoDAO;

    public RepositorioProjeto(ProjetoDAO projetoDAO) {
        this.projetoDAO = projetoDAO;
    }

    public Resultado cadastrarProjeto(String nome, String status, String descricao, LocalDate dataInicio, LocalDate dataTermino, int idFuncionario){
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome Inválido");
        }

        if(status.isBlank() || status.isEmpty()){
            return Resultado.erro("Status Inválido");
        }

        if(descricao.isBlank() || descricao.isEmpty()){
            return Resultado.erro("descricao Inválida");
        }
        
        if(dataInicio.isBefore(LocalDate.now())){
            return Resultado.erro("Data invalida");
        }

        if(dataTermino.isBefore(LocalDate.now())){
            return Resultado.erro("Data invalida");
        }


        Projeto projeto = new Projeto(nome, status, descricao, dataInicio, dataTermino);

        return projetoDAO.criar(projeto, idFuncionario);
                

    }

    public Resultado listar(){
        Resultado resultado = projetoDAO.listar();

        return resultado;
    }

    public Resultado alterarProjeto(String nome, String status, String descricao, LocalDate dataInicio, LocalDate dataTermino, int id) {
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome Inválido");
        }

        if(status.isBlank() || status.isEmpty()){
            return Resultado.erro("Status Inválido");
        }

        if(descricao.isBlank() || descricao.isEmpty()){
            return Resultado.erro("descricao Inválida");
        }
        
        if(dataInicio.isBefore(LocalDate.now())){
            return Resultado.erro("Data invalida");
        }

        if(dataTermino.isBefore(LocalDate.now())){
            return Resultado.erro("Data invalida");
        }

        Projeto projeto = new Projeto(nome, status, descricao, dataInicio, dataTermino);

        return projetoDAO.atualizar(id, projeto);
    }

    public Resultado excluirProjeto(int idProjeto) {
        return projetoDAO.deletar(idProjeto);
    }
}
