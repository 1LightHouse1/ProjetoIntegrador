package com.example.gerenciadorDeProjetos.model.repositories;

import java.time.LocalDate;

import com.example.gerenciadorDeProjetos.model.daos.TarefaDAO;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.entities.Tarefa;
import com.github.hugoperlin.results.Resultado;

public class RepositorioTarefa {

    private TarefaDAO tarefaDAO;

    public RepositorioTarefa(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }

    public Resultado cadastrarTarefa(String nome, String status, String descricao, LocalDate dataInicio,
            LocalDate dataTermino, Projeto projeto, int id) {
        
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

        if(projeto == null){
            return Resultado.erro("Projeto Inválido");
        }

        Tarefa tarefa = new Tarefa(projeto, nome, descricao, status, dataInicio, dataTermino);

        return tarefaDAO.criar(tarefa, id);
    }

    public Resultado alterarProjeto(String nome, String status, String descricao, LocalDate dataInicio,
            LocalDate dataTermino, Projeto projeto, int idTarefa) {
        return null;
    }
    
}
