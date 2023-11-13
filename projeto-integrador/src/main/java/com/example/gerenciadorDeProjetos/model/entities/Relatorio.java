package com.example.gerenciadorDeProjetos.model.entities;

import java.io.File;

public class Relatorio {
    private int idRelatorio;
    private Tarefa tarefa;
    private String nome;
    private String descicao;
    private File documento;

    public Relatorio(int idRelatorio, Tarefa tarefa, String nome, String descicao, File documento) {
        this.idRelatorio = idRelatorio;
        this.tarefa = tarefa;
        this.nome = nome;
        this.descicao = descicao;
        this.documento = documento;
    }

    public Relatorio(Tarefa tarefa, String nome, String descicao, File documento) {
        this.tarefa = tarefa;
        this.nome = nome;
        this.descicao = descicao;
        this.documento = documento;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public File getDocumento() {
        return documento;
    }

    public void setDocumento(File documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Relatório: " + nome;
    }

    
}
