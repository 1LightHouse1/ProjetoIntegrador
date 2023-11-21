package com.example.gerenciadorDeProjetos.model.daos;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.github.hugoperlin.results.Resultado;

/**
 * ProjetoDAO
 */
public interface ProjetoDAO {

    Resultado criar(Projeto projeto, int id);
    Resultado adicionarFuncionarioProojeto(int idFuncionario, int idProjeto);

    Resultado listar();

    Resultado buscarProjetoTarefa(int idTarefa);

    Resultado atualizar(int id, Projeto projeto);

    Resultado deletar(int id);

}
