package com.example.gerenciadorDeProjetos.model.entities;

public class Login {
    private Funcionario funcionarioAtual;

    public Login(Funcionario funcionarioAtual){
        this.funcionarioAtual = funcionarioAtual;
    }

    public Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }

    public void setFuncionarioAtual(Funcionario funcionarioAtual) {
        funcionarioAtual = funcionarioAtual;
    }

}
