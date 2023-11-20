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
            PreparedStatement pstm = con.prepareStatement("INSERT INTO Projeto(nomeProjeto, descricao, status, dataInicio, dataTermino) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS );
            PreparedStatement pstmProjetoFuncionario = con.prepareStatement("INSERT INTO ProjetoFuncionario(idProjeto, idFuncionario) VALUES (?,?)");

            pstm.setString(1, projeto.getNomeProjeto());
            pstm.setString(2, projeto.getDescricao());
            pstm.setString(3, projeto.getStatus());
            pstm.setDate(4, Date.valueOf(projeto.getDataInicio()));
            pstm.setDate(5, Date.valueOf(projeto.getDataTermino()));

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
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Projeto");

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
