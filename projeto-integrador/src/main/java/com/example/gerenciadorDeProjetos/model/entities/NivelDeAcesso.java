package com.example.gerenciadorDeProjetos.model.entities;

public class NivelDeAcesso {
    private int idNivelDeAcesso;
    private String tipoDeAcesso;
    private String permissoes;

    public NivelDeAcesso(int idNivelDeAcesso, String tipoDeAcesso, String permissoes) {
        this.idNivelDeAcesso = idNivelDeAcesso;
        this.tipoDeAcesso = tipoDeAcesso;
        this.permissoes = permissoes;
    }

    public int getIdNivelDeAcesso() {
        return idNivelDeAcesso;
    }

    public void setIdNivelDeAcesso(int idNivelDeAcesso) {
        this.idNivelDeAcesso = idNivelDeAcesso;
    }

    public String getTipoDeAcesso() {
        return tipoDeAcesso;
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
        return "Tipo de Acesso: " + this.tipoDeAcesso;
    }
}
