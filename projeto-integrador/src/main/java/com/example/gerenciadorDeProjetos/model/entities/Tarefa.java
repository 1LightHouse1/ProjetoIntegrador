package com.example.gerenciadorDeProjetos.model.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Tarefa {
    private int idTarefa;
    private Projeto projeto;
    private String nomeTarefa;
    private String descricao;
    private String status;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private List<Funcionario> funcionarios;

    public Tarefa(int idTarefa, Projeto projeto, String nomeTarefa, String descricao, String status, LocalDate dataInicio,
            LocalDate dataTermino) {
        this.idTarefa = idTarefa;
        this.projeto = projeto;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        
    }

    public Tarefa(int idTarefa, String nomeTarefa, String descricao, String status, LocalDate dataInicio,
            LocalDate dataTermino) {
        this.idTarefa = idTarefa;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        
    }

    public Tarefa( String nomeTarefa, String descricao, String status, LocalDate dataInicio,
            LocalDate dataTermino) {
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        
    }

    public Tarefa( Projeto projeto, String nomeTarefa, String descricao, String status, LocalDate dataInicio,
            LocalDate dataTermino) {
        this.projeto = projeto;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    @Override
    public String toString() {
        return "Tarefa:" + nomeTarefa;
    }

    
}
