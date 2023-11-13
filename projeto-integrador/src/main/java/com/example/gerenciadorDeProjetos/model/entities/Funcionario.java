package com.example.gerenciadorDeProjetos.model.entities;

public class Funcionario {

    private String cpf;
    private String nome;
    private NivelDeAcesso cargo;
    private String login;
    private String senha;
    private String email;


    public Funcionario(String cpf, String nome, NivelDeAcesso cargo, String login, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NivelDeAcesso getCargo() {
        return cargo;
    }

    public void setCargo(NivelDeAcesso cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome;
    }
    
}
