package com.example.gerenciadorDeProjetos.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private int idProjeto;
    private List<Funcionario> funcionarios;
    private String nomeProjeto;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private String status;

    public Projeto(int idProjeto, ArrayList<Funcionario> funcionarios, String nomeProjeto, String status, String descricao,
            LocalDate dataInicio, LocalDate dataTermino) {
        this.idProjeto = idProjeto;
        this.funcionarios = funcionarios;
        this.nomeProjeto = nomeProjeto;
        this.status = status;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Projeto(ArrayList<Funcionario> funcionarios, String nomeProjeto, String status, String descricao,
            LocalDate dataInicio, LocalDate dataTermino) {
        this.idProjeto = idProjeto;
        this.funcionarios = funcionarios;
        this.nomeProjeto = nomeProjeto;
        this.status = status;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Projeto(String nomeProjeto, String status, String descricao,
            LocalDate dataInicio, LocalDate dataTermino) {
        this.nomeProjeto = nomeProjeto;
        this.status = status;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Projeto(int idProjeto, String nomeProjeto, String status, String descricao,
            LocalDate dataInicio, LocalDate dataTermino) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.status = status;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
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
