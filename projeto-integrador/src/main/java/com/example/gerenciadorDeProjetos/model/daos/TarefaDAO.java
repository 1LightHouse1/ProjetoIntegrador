package com.example.gerenciadorDeProjetos.model.daos;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.entities.Tarefa;
import com.github.hugoperlin.results.Resultado;

public interface TarefaDAO {
    Resultado criar(Tarefa tarefa, int id);

    Resultado listar();

    Resultado atualizar(int id, Funcionario funcionario);

    Resultado deletar(int id);
} 
