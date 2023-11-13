package com.example.gerenciadorDeProjetos.model.entities;

import java.util.ArrayList;
import java.util.Date;

public class Projeto {
    private int idProjeto;
    private ArrayList<Funcionario> funcionarios;
    private String nomeProjeto;
    private String descricao;
    private Date dataInicio;
    private Date dataTermino;
    private Date dataPrevistaTermino;
    private String status;

    public Projeto(int idProjeto, ArrayList<Funcionario> funcionarios, String nomeProjeto, String descricao,
            Date dataInicio, Date dataTermino, Date dataPrevistaTermino, String status) {
        this.idProjeto = idProjeto;
        this.funcionarios = funcionarios;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.dataPrevistaTermino = dataPrevistaTermino;
        this.status = status;
    }

    public Projeto(ArrayList<Funcionario> funcionarios, String nomeProjeto, String descricao,
            Date dataInicio, Date dataTermino, Date dataPrevistaTermino, String status) {
        this.funcionarios = funcionarios;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.dataPrevistaTermino = dataPrevistaTermino;
        this.status = status;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Projeto: " + nomeProjeto;
    }


    

    
}
