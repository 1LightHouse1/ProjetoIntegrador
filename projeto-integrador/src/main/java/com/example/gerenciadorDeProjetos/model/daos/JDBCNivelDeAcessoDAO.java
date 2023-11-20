package com.example.gerenciadorDeProjetos.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.github.hugoperlin.results.Resultado;

public class JDBCNivelDeAcessoDAO implements NivelDeAcessoDAO {
    private FabricaConexoes fabrica;

    public JDBCNivelDeAcessoDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado listar() {
        try {
            Connection con = fabrica.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM NivelDeAcesso");

            ResultSet rs = pstm.executeQuery();

            ArrayList<NivelDeAcesso> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("idNivelDeAcesso");
                String tipoDeAcesso = rs.getString("tipoDeAcesso");
                String descricao = rs.getString("descricao");
                String permissoes = rs.getString("permissoes");

                NivelDeAcesso nivelDeAcesso = new NivelDeAcesso(id, descricao, tipoDeAcesso, permissoes);

                lista.add(nivelDeAcesso);
            }
            rs.close();
            pstm.close();
            con.close();

            return Resultado.sucesso("Cargos listados!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }
}
