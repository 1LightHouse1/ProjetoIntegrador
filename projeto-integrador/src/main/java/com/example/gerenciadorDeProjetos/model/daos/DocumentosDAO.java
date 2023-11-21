package com.example.gerenciadorDeProjetos.model.daos;

import com.example.gerenciadorDeProjetos.model.entities.Documentos;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.github.hugoperlin.results.Resultado;

/**
 * DocumentosDAO
 */
public interface DocumentosDAO {

    Resultado criar(Documentos documento, String absolutePath, int idFuncionario);
}
