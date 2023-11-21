package com.example.gerenciadorDeProjetos.model.repositories;

import java.time.LocalDate;
import java.util.List;

import com.example.gerenciadorDeProjetos.model.daos.FuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.daos.ProjetoDAO;
import com.example.gerenciadorDeProjetos.model.daos.TarefaDAO;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.entities.Tarefa;
import com.github.hugoperlin.results.Resultado;

public class RepositorioTarefa {

    private TarefaDAO tarefaDAO;
    private FuncionarioDAO funcionarioDAO;
    private ProjetoDAO projetoDAO;

    public RepositorioTarefa(TarefaDAO tarefaDAO, FuncionarioDAO funcionarioDAO, ProjetoDAO projetoDAO) {
        this.tarefaDAO = tarefaDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.projetoDAO = projetoDAO;
    }

    public Resultado cadastrarTarefa(String nome, String status, String descricao, LocalDate dataInicio,
            LocalDate dataTermino, Projeto projeto, int id) {
        
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome Inválido");
        }

        if(status.isBlank() || status.isEmpty()){
            return Resultado.erro("Status Inválido");
        }

        if(descricao.isBlank() || descricao.isEmpty()){
            return Resultado.erro("descricao Inválida");
        }
        
        if(dataInicio.isBefore(LocalDate.now()) || dataTermino == null){
            return Resultado.erro("Data invalida");
        }

        if(dataTermino.isBefore(LocalDate.now()) || dataTermino == null){
            return Resultado.erro("Data invalida");
        }

        if(projeto == null){
            return Resultado.erro("Projeto Inválido");
        }

        Tarefa tarefa = new Tarefa(projeto, nome, descricao, status, dataInicio, dataTermino);

        return tarefaDAO.criar(tarefa, id);
    }

    public Resultado alterarProjeto(String nome, String status, String descricao, LocalDate dataInicio,
            LocalDate dataTermino, Projeto projeto, int idTarefa) {
        if(nome.isBlank() || nome.isEmpty()){
            return Resultado.erro("Nome Inválido");
        }

        if(status.isBlank() || status.isEmpty()){
            return Resultado.erro("Status Inválido");
        }

        if(descricao.isBlank() || descricao.isEmpty()){
            return Resultado.erro("descricao Inválida");
        }
        
        if(dataInicio.isBefore(LocalDate.now()) || dataTermino == null){
            return Resultado.erro("Data invalida");
        }

        if(dataTermino.isBefore(LocalDate.now()) || dataTermino == null){
            return Resultado.erro("Data invalida");
        }

        if(projeto == null){
            return Resultado.erro("Projeto Inválido");
        }

        Tarefa tarefa = new Tarefa(projeto, nome, descricao, status, dataInicio, dataTermino);

        return tarefaDAO.atualizar(idTarefa, tarefa);
    }

    public Resultado listar() {
        Resultado resultado = tarefaDAO.listar();

        if(resultado.foiSucesso()){
            List<Tarefa> lista = (List<Tarefa>)resultado.comoSucesso().getObj();

            for(Tarefa tarefa: lista){
                Resultado resultado2 = funcionarioDAO.buscarFuncionarioTarefa(tarefa.getIdTarefa());
                Resultado resultado3 = projetoDAO.buscarProjetoTarefa(tarefa.getIdTarefa());

                if(resultado2.foiErro()){
                    return resultado2;
                }
                if(resultado3.foiErro()){
                    return resultado3;
                }

                List<Funcionario> funcionarios = (List<Funcionario>)resultado2.comoSucesso().getObj();
                tarefa.setFuncionarios(funcionarios);

                Projeto projetos = (Projeto)resultado3.comoSucesso().getObj();
                tarefa.setProjeto(projetos);
            }
            
        }
        return resultado;
    }
    
}
