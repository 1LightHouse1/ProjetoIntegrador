package com.example.gerenciadorDeProjetos.model.daos;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.github.hugoperlin.results.Resultado;

/**
 * ProjetoDAO
 */
public interface ProjetoDAO {

    Resultado criar(Projeto projeto, int id);

    Resultado listar();

    Resultado atualizar(int id, Funcionario funcionario);

    Resultado deletar(int id);

}
