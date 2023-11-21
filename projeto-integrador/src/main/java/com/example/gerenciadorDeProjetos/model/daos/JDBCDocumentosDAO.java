package com.example.gerenciadorDeProjetos.model.daos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.gerenciadorDeProjetos.model.entities.Documentos;
import com.example.gerenciadorDeProjetos.utils.DBUtils;
import com.github.hugoperlin.results.Resultado;
import com.mysql.cj.xdevapi.PreparableStatement;

public class JDBCDocumentosDAO implements DocumentosDAO{
    private FabricaConexoes fabrica;

    public JDBCDocumentosDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Documentos documento, String absolutePath, int idFuncionario) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO Documentos(idFuncionario, idProjeto,nome, descricao, documento) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            FileInputStream fileInputStream = new FileInputStream(absolutePath);

            pstm.setInt(1, idFuncionario);
            pstm.setInt(2, documento.getProjeto().getIdProjeto());
            pstm.setString(3, documento.getNome());
            pstm.setString(4, documento.getDescricao());
            pstm.setBinaryStream(5, fileInputStream);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                int id = DBUtils.getLastId(pstm);

                documento.setIdDocumento(id);

                return Resultado.sucesso("Documento cadastrado!", documento);
            }

            return Resultado.erro("Erro desconhecido!"); 
        } catch (SQLException | IOException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
