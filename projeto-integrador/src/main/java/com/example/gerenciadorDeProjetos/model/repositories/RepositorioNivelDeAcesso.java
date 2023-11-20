package com.example.gerenciadorDeProjetos.model.repositories;

import java.util.ArrayList;

import com.example.gerenciadorDeProjetos.model.daos.NivelDeAcessoDAO;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.github.hugoperlin.results.Resultado;

public class RepositorioNivelDeAcesso {

    private NivelDeAcessoDAO dao;

    public RepositorioNivelDeAcesso(NivelDeAcessoDAO dao){
        this.dao = dao;
    }

    public Resultado listarNivelDeAcesso(){
        return dao.listar();
    }
}
