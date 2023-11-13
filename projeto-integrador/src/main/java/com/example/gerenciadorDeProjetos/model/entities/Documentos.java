package com.example.gerenciadorDeProjetos.model.entities;

import java.io.File;

public class Documentos {
    private int idDocumento;
    private Projeto projeto;
    private String nome;
    private String descricao;
    private File documento;

    public Documentos(Projeto projeto, String nome, String descricao, File documento) {
        this.projeto = projeto;
        this.nome = nome;
        this.descricao = descricao;
        this.documento = documento;
    }

    public Documentos(int idDocumento, Projeto projeto, String nome, String descricao, File documento) {
        this.idDocumento = idDocumento;
        this.projeto = projeto;
        this.nome = nome;
        this.descricao = descricao;
        this.documento = documento;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public File getDocumento() {
        return documento;
    }

    public void setDocumento(File documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }

}
