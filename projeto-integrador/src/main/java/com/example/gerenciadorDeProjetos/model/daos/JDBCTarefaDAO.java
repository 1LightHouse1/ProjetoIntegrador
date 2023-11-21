package com.example.gerenciadorDeProjetos.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
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

            pstm.setString(1, tarefa.getNomeTarefa());
            pstm.setString(2, tarefa.getDescricao());
            pstm.setString(3, tarefa.getStatus());
            pstm.setDate(4, Date.valueOf(tarefa.getDataInicio()));
            pstm.setDate(5, Date.valueOf(tarefa.getDataTermino()));
            pstm.setInt(6, 1);

            int ret = pstm.executeUpdate();

            if(ret==1){
                int idTarefa = DBUtils.getLastId(pstm);

                tarefa.setIdTarefa(idTarefa);

                PreparedStatement pstmTarefaProjeto = con.prepareStatement("INSERT INTO ProjetoTarefa(idTarefa,idProjeto) VALUES (?,?)");

                pstmTarefaProjeto.setInt(1, idTarefa);
                pstmTarefaProjeto.setInt(2, tarefa.getProjeto().getIdProjeto());

                ret = pstmTarefaProjeto.executeUpdate();

                PreparedStatement pstmTarefaFuncionario= con.prepareStatement("INSERT INTO TarefaFuncionario(Tarefa_idTarefa, idFuncionario) VALUES (?,?)");

                pstmTarefaFuncionario.setInt(1, idTarefa);
                pstmTarefaFuncionario.setInt(2, id);

                ret = pstmTarefaFuncionario.executeUpdate();
                
                return Resultado.sucesso("Tarefa Cadastrada", tarefa);
            }            
            
            return Resultado.erro("Erro inesperado");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Tarefa WHERE ativo = 1");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Tarefa> lista = new ArrayList<>();
            
            while(rs.next()){
                int id = rs.getInt("idTarefa");
                String nome = rs.getString("nomeDaTarefa");
                String descricao = rs.getString("descricao");
                String status = rs.getString("status");
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();

                Tarefa tarefa = new Tarefa(id, nome, descricao, status, dataInicio, dataTermino);

                lista.add(tarefa);
            }

            return Resultado.sucesso("Lista de Tarefas", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Tarefa novaTarefa) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE Tarefa SET nomeDaTarefa = ?, descricao = ?, status = ?, dataInicio = ?, dataTermino = ? WHERE idTarefa = ?");

            pstm.setString(1,novaTarefa.getNomeTarefa());
            pstm.setString(2,novaTarefa.getDescricao());
            pstm.setString(3,novaTarefa.getStatus());
            pstm.setDate(4, Date.valueOf(novaTarefa.getDataInicio()));
            pstm.setDate(5, Date.valueOf(novaTarefa.getDataTermino()));
            pstm.setInt(6, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                novaTarefa.setIdTarefa(id);
                return Resultado.sucesso("Tarefa alterado", novaTarefa);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE Tarefa SET ativo = 0 WHERE idTarefa = ?");

            pstm.setInt(1, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                return Resultado.sucesso("Tarefa Excluida", pstm);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
