package com.example.gerenciadorDeProjetos.model.entities;

public class Login {
    private static Funcionario funcionarioAtual;

    public static Funcionario getFuncionarioAtual() {
        return funcionarioAtual;
    }

    public static void setFuncionarioAtual(Funcionario funcionarioAtual) {
        Login.funcionarioAtual = funcionarioAtual;
    }

    public static boolean estaLogado() {
        return funcionarioAtual != null;
    }

}
