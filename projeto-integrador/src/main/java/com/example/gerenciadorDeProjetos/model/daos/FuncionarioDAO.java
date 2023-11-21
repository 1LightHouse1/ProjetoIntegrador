package com.example.gerenciadorDeProjetos.model.daos;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.github.hugoperlin.results.Resultado;

/**
 * FuncionarioDAO
 */
public interface FuncionarioDAO {

    Resultado criar(Funcionario funcionario);

    Resultado listar();
    Resultado verificaEstruturaEmail(String email);
    Resultado verificaPermissao(int id);

    Resultado atualizar(int id, Funcionario funcionario);

    Resultado deletar(int id);

    Resultado verificaLogin(String login, String senha);

    Resultado buscarFuncionarioProjeto(int idProjeto);

    Resultado buscarFuncionarioTarefa(int idTarefa);



}
