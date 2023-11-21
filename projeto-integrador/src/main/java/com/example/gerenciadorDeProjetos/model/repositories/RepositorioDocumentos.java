package com.example.gerenciadorDeProjetos.model.repositories;

import com.example.gerenciadorDeProjetos.model.daos.DocumentosDAO;
import com.example.gerenciadorDeProjetos.model.entities.Documentos;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.github.hugoperlin.results.Resultado;

public class RepositorioDocumentos {

    private DocumentosDAO documentosDAO;

    public RepositorioDocumentos(DocumentosDAO documentosDAO) {
        this.documentosDAO = documentosDAO;
    }

    public Resultado adicionarDocumento(String nome, String descricao, Projeto projeto, String absolutePath, int idFuncionario) {

        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Campo nome inválido");
        }

        if(descricao.isBlank() || descricao.isEmpty()){
            return Resultado.erro("Campo descrição inválida");
        }

        if(projeto == null){
            return Resultado.erro("Campo projeto inválida");
        }

        if(absolutePath.isBlank() || absolutePath.isEmpty()){
            return Resultado.erro("Campo descrição inválida");
        }

        Documentos documentos = new Documentos(projeto, nome, descricao, null);
        return documentosDAO.criar(documentos, absolutePath, idFuncionario);
    }
    
}
