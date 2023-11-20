package com.example.gerenciadorDeProjetos.model.daos;

public class JDBCProjetoDAO implements ProjetoDAO {
    private FabricaConexoes fabrica;

    public JDBCProjetoDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }
}
