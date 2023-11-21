package com.example.gerenciadorDeProjetos.model.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.example.gerenciadorDeProjetos.utils.DBUtils;
import com.github.hugoperlin.results.Resultado;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCFuncionarioDAO implements FuncionarioDAO {
    private FabricaConexoes fabrica;

    public JDBCFuncionarioDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Funcionario funcionario) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.
                        prepareStatement("INSERT INTO Funcionario(nome, login, email, senha, cpf, IdNivelDeAcesso ) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1,funcionario.getNome());
            pstm.setString(2,funcionario.getLogin());
            pstm.setString(3,funcionario.getEmail());
            pstm.setString(4,funcionario.getSenha());
            pstm.setString(5,funcionario.getCpf());
            pstm.setInt(6,funcionario.getCargo().getIdNivelDeAcesso());
        
            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                int id = DBUtils.getLastId(pstm);

                funcionario.setId(id);

                return Resultado.sucesso("Funcionário cadastrado!", funcionario);
            }
            return Resultado.erro("Erro desconhecido!"); 
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
    public Resultado verificaEstruturaEmail(String email) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT valida_email(?) as resultado");

            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();
            

            if(rs.next()){
                String resultado = rs.getString("resultado");

                if("Estrutura Válida".equals(resultado)){
                    return Resultado.sucesso("Email Válido", resultado);
                }
            }

            return Resultado.erro("Estrutura do email inválida");
            
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado verificaLogin(String login, String senha) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Funcionario WHERE login = (?) and senha = (?) and ativo = 1");

            pstm.setString(1, login);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            

            if(rs.next()){
                int id = rs.getInt("idFuncionario");
                String nome = rs.getString("nome");
                String loginFuncionario = rs.getString("login");
                String senhaFuncionario = rs.getString("senha");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                int cargo = rs.getInt("IdNivelDeAcesso");

                PreparedStatement pstm2 = con.prepareStatement("SELECT * FROM NivelDeAcesso WHERE idNivelDeAcesso = (?)");

                pstm2.setInt(1, cargo);

                ResultSet rs2 = pstm2.executeQuery();
                rs2.next();

                String tipoDeAcesso = rs2.getString("tipoDeAcesso");
                String descricao = rs2.getString("descricao");
                String permissoes = rs2.getString("permissoes");

                NivelDeAcesso nivelDeAcesso = new NivelDeAcesso(cargo, descricao, tipoDeAcesso, permissoes);

                Funcionario funcionario = new Funcionario(id, cpf, nome, nivelDeAcesso, login, senha, email);

                return Resultado.sucesso("Usuário Logado", funcionario);
            }

            return Resultado.erro("Usuário Inválido");
            
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Funcionario funcionario) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE Funcionario SET nome = ?, login = ?, email = ?, senha = ?, cpf = ?, IdNivelDeAcesso = ? WHERE idFuncionario = ?");

            pstm.setString(1,funcionario.getNome());
            pstm.setString(2,funcionario.getLogin());
            pstm.setString(3,funcionario.getEmail());
            pstm.setString(4,funcionario.getSenha());
            pstm.setString(5,funcionario.getCpf());
            pstm.setInt(6,funcionario.getCargo().getIdNivelDeAcesso());
            pstm.setInt(7, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                funcionario.setId(id);
                return Resultado.sucesso("Usuário alterado", funcionario);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado deletar(int id) {
        try (Connection con = fabrica.getConnection()){
            PreparedStatement pstm = con.prepareStatement("UPDATE Funcionario SET ativo = 0 WHERE idFuncionario = ?");

            pstm.setInt(1, id);

            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                return Resultado.sucesso("Usuário Excluido", pstm);
            }
            return Resultado.erro("Erro desconhecido!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado verificaPermissao(int id) {
        try(Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT IdNivelDeAcesso FROM Funcionario WHERE idFuncionario = (?)");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            

            if(rs.next()){
                int idNivelDeAcesso = rs.getInt("IdNivelDeAcesso");

                if(idNivelDeAcesso == 1){
                    return Resultado.sucesso("Usuário Permitido", idNivelDeAcesso);
                }
            }

            return Resultado.erro("Nivel de usuário não permitido para essas ação");
            
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarFuncionarioProjeto(int idProjeto) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Funcionario JOIN ProjetoFuncionario pf ON Funcionario.idFuncionario = pf.idFuncionario where pf.idProjeto = ?");
            ArrayList<Funcionario> lista = new ArrayList<>();

            pstm.setInt(1, idProjeto);

            ResultSet rs = pstm.executeQuery();

             while(rs.next()){
                int id = rs.getInt("idFuncionario");
                String nome = rs.getString("nome");
                String loginFuncionario = rs.getString("login");
                String senhaFuncionario = rs.getString("senha");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                int cargo = rs.getInt("IdNivelDeAcesso");

                PreparedStatement pstm2 = con.prepareStatement("SELECT * FROM NivelDeAcesso WHERE idNivelDeAcesso = (?)");

                pstm2.setInt(1, cargo);

                ResultSet rs2 = pstm2.executeQuery();
                rs2.next();

                String tipoDeAcesso = rs2.getString("tipoDeAcesso");
                String descricao = rs2.getString("descricao");
                String permissoes = rs2.getString("permissoes");

                NivelDeAcesso nivelDeAcesso = new NivelDeAcesso(cargo, descricao, tipoDeAcesso, permissoes);

                Funcionario funcionario = new Funcionario(id, cpf, nome, nivelDeAcesso, loginFuncionario, senhaFuncionario, email);

                lista.add(funcionario);
            }

            return Resultado.sucesso("ok", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado buscarFuncionarioTarefa(int idTarefa) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Funcionario JOIN TarefaFuncionario tf ON Funcionario.idFuncionario = tf.idFuncionario where Tarefa_idTarefa = ?");
            
            ArrayList<Funcionario> lista = new ArrayList<>();

            pstm.setInt(1, idTarefa);

            ResultSet rs = pstm.executeQuery();

             while(rs.next()){
                int id = rs.getInt("idFuncionario");
                String nome = rs.getString("nome");
                String loginFuncionario = rs.getString("login");
                String senhaFuncionario = rs.getString("senha");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                int cargo = rs.getInt("IdNivelDeAcesso");

                PreparedStatement pstm2 = con.prepareStatement("SELECT * FROM NivelDeAcesso WHERE idNivelDeAcesso = (?)");

                pstm2.setInt(1, cargo);

                ResultSet rs2 = pstm2.executeQuery();
                rs2.next();

                String tipoDeAcesso = rs2.getString("tipoDeAcesso");
                String descricao = rs2.getString("descricao");
                String permissoes = rs2.getString("permissoes");

                NivelDeAcesso nivelDeAcesso = new NivelDeAcesso(cargo, descricao, tipoDeAcesso, permissoes);

                Funcionario funcionario = new Funcionario(id, cpf, nome, nivelDeAcesso, loginFuncionario, senhaFuncionario, email);

                lista.add(funcionario);
            }

            return Resultado.sucesso("ok", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    


}
