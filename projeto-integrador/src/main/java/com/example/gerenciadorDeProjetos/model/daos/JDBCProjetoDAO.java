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
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.utils.DBUtils;
import com.github.hugoperlin.results.Resultado;

public class JDBCProjetoDAO implements ProjetoDAO {
    private FabricaConexoes fabrica;

    public JDBCProjetoDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Projeto projeto, int id) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO Projeto(nomeProjeto, descricao, status, dataInicio, dataTermino, ativo) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS );
            PreparedStatement pstmProjetoFuncionario = con.prepareStatement("INSERT INTO ProjetoFuncionario(idProjeto, idFuncionario) VALUES (?,?)");

            pstm.setString(1, projeto.getNomeProjeto());
            pstm.setString(2, projeto.getDescricao());
            pstm.setString(3, projeto.getStatus());
            pstm.setDate(4, Date.valueOf(projeto.getDataInicio()));
            pstm.setDate(5, Date.valueOf(projeto.getDataTermino()));
            pstm.setInt(6, 1);

            int ret = pstm.executeUpdate();

            if(ret==1){
                int idProjeto = DBUtils.getLastId(pstm);

                projeto.setIdProjeto(idProjeto);

                pstmProjetoFuncionario.setInt(1, projeto.getIdProjeto());
                pstmProjetoFuncionario.setInt(2, id);

                ret = pstmProjetoFuncionario.executeUpdate();
                
                return Resultado.sucesso("Projeto Cadastrado", projeto);
            }            
            
            return Resultado.erro("Erro inesperado");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Projeto WHERE ativo = 1");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Projeto> lista = new ArrayList<>();
            
            while(rs.next()){
                int id = rs.getInt("idProjeto");
                String nome = rs.getString("nomeProjeto");
                String descricao = rs.getString("descricao");
                String status = rs.getString("status");
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();

                Projeto projeto = new Projeto(id, nome, status, descricao, dataInicio, dataTermino);

                lista.add(projeto);
            }

            return Resultado.sucesso("Lista de Projetos", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Projeto novoProjeto) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE Projeto SET nomeProjeto = ?, descricao = ?, status = ?, dataInicio = ?, dataTermino = ? WHERE idProjeto = ?");

            pstm.setString(1,novoProjeto.getNomeProjeto());
            pstm.setString(2,novoProjeto.getDescricao());
            pstm.setString(3,novoProjeto.getStatus());
            pstm.setDate(4, Date.valueOf(novoProjeto.getDataInicio()));
            pstm.setDate(5, Date.valueOf(novoProjeto.getDataTermino()));
            pstm.setInt(6, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                novoProjeto.setIdProjeto(id);
                return Resultado.sucesso("Projeto alterado", novoProjeto);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE Projeto SET ativo = 0 WHERE idProjeto = ?");

            pstm.setInt(1, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                return Resultado.sucesso("Projeto Excluido", pstm);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarProjetoTarefa(int idTarefa) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Projeto JOIN ProjetoTarefa pt ON Projeto.idProjeto = pt.idProjeto where pt.idTarefa = ?");

            pstm.setInt(1, idTarefa);

            ResultSet rs = pstm.executeQuery();

             if(rs.next()){
                int id = rs.getInt("idProjeto");
                String nome = rs.getString("nomeProjeto");
                String descricao = rs.getString("descricao");
                String status = rs.getString("status");
                LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();

                Projeto projeto = new Projeto(id, nome, status, descricao, dataInicio, dataTermino);

                return Resultado.sucesso("Ok", projeto);
            }

            return Resultado.erro("Erro Inesperado");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
