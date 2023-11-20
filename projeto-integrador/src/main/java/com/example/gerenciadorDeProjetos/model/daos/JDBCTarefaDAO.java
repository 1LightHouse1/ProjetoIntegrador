package com.example.gerenciadorDeProjetos.model.daos;

public class JDBCTarefaDAO implements TarefaDAO {
    private FabricaConexoes fabrica;

    public JDBCTarefaDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }
}
