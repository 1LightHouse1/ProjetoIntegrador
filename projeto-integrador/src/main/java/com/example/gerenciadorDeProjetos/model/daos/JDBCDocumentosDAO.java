package com.example.gerenciadorDeProjetos.model.daos;

public class JDBCDocumentosDAO implements DocumentosDAO{
    private FabricaConexoes fabrica;

    public JDBCDocumentosDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }
}
