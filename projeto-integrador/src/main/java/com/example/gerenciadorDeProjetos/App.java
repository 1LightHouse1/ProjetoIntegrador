package com.example.gerenciadorDeProjetos;

import com.example.gerenciadorDeProjetos.controller.Principal;
import com.example.gerenciadorDeProjetos.controller.TelaCadastrarFuncionario;
import com.example.gerenciadorDeProjetos.controller.TelaLogin;

import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    @Override
    public String getAppTitle() {
        return "Nexus App";
    }

    @Override
    public String getHome() {
        return "LOGIN";
    }

    @Override
    public void registrarTelas() {
        registraTela("LOGIN", new ScreenRegistryFXML(App.class, "telalogin.fxml", o->new TelaLogin()));
        registraTela("CADASTRARFUNCIONARIO", new ScreenRegistryFXML(App.class, "cadastrarfuncionario.fxml", o->new TelaCadastrarFuncionario()));
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));
    }

}