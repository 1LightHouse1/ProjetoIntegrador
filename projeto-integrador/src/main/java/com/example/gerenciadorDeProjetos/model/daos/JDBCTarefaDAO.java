package com.example.gerenciadorDeProjetos.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Tarefa;
import com.example.gerenciadorDeProjetos.utils.DBUtils;
import com.github.hugoperlin.results.Resultado;

public class JDBCTarefaDAO implements TarefaDAO {
    private FabricaConexoes fabrica;

    public JDBCTarefaDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Tarefa tarefa, int id) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO Tarefa(nomeDaTarefa, descricao, status, dataInicio, dataTermino, ativo) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS );
            PreparedStatement pstmTarefaProjeto = con.prepareStatement("INSERT INTO TarefaFuncionario(idTarefa, idFuncionario) VALUES (?,?)");
            PreparedStatement pstmTarefaFuncionario = con.prepareStatement("INSERT INTO ProjetoTarefa(idProjeto, idTarefa) VALUES (?,?)");

            pstm.setString(1, tarefa.getNomeTarefa());
            pstm.setString(2, tarefa.getDescricao());
            pstm.setString(3, tarefa.getStatus());
            pstm.setDate(4, Date.valueOf(tarefa.getDataInicio()));
            pstm.setDate(5, Date.valueOf(tarefa.getDataTermino()));
            pstm.setInt(6, 1);

            int ret = pstm.executeUpdate();

            if(ret==1){
                int idProjeto = DBUtils.getLastId(pstm);

                tarefa.setIdTarefa(idProjeto);;

                pstmTarefaFuncionario.setInt(1, tarefa.getIdTarefa());
                pstmTarefaFuncionario.setInt(2, id);

                ret = pstmTarefaFuncionario.executeUpdate();
                
                return Resultado.sucesso("Projeto Cadastrado", tarefa);
            }            
            
            return Resultado.erro("Erro inesperado");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Resultado atualizar(int id, Funcionario funcionario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
}
