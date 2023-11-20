package com.example.gerenciadorDeProjetos.model.entities;

public class NivelDeAcesso {
    private int idNivelDeAcesso;
    private String descricao;
    private String tipoDeAcesso;
    private String permissoes;

    public NivelDeAcesso(int idNivelDeAcesso,String descricao, String tipoDeAcesso, String permissoes) {
        this.idNivelDeAcesso = idNivelDeAcesso;
        this.tipoDeAcesso = tipoDeAcesso;
        this.permissoes = permissoes;
        this.descricao = descricao;
    }

    public int getIdNivelDeAcesso() {
        return idNivelDeAcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdNivelDeAcesso(int idNivelDeAcesso) {
        this.idNivelDeAcesso = idNivelDeAcesso;
    }

    public String getTipoDeAcesso() {
        return tipoDeAcesso;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipoDeAcesso(String tipoDeAcesso) {
        this.tipoDeAcesso = tipoDeAcesso;
    }

    public String getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(String permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public String toString() {
        return this.tipoDeAcesso;
    }
}
