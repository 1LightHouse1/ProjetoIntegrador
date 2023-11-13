package com.example.gerenciadorDeProjetos.model.entities;

import java.util.Date;

public class Tarefa {
    private int idTarefa;
    private Projeto projeto;
    private String nomeTarefa;
    private String descricao;
    private String status;
    private Date dataInicio;
    private Date dataTermino;
    private Date dataPrevistaTermino;

    public Tarefa(int idTarefa, Projeto projeto, String nomeTarefa, String descricao, String status, Date dataInicio,
            Date dataTermino, Date dataPrevistaTermino) {
        this.idTarefa = idTarefa;
        this.projeto = projeto;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.dataPrevistaTermino = dataPrevistaTermino;
    }

    public Tarefa( Projeto projeto, String nomeTarefa, String descricao, String status, Date dataInicio,
            Date dataTermino, Date dataPrevistaTermino) {
        this.projeto = projeto;
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.dataPrevistaTermino = dataPrevistaTermino;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Projeto getProjeto() {
        return projeto;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Date getDataPrevistaTermino() {
        return dataPrevistaTermino;
    }

    public void setDataPrevistaTermino(Date dataPrevistaTermino) {
        this.dataPrevistaTermino = dataPrevistaTermino;
    }

    @Override
    public String toString() {
        return "Tarefa:" + nomeTarefa;
    }

    
}
