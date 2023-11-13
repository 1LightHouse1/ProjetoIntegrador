package com.example.gerenciadorDeProjetos;

import io.github.hugoperlin.navigatorfx.BaseAppNavigator;

public class App extends BaseAppNavigator{

    @Override
    public String getAppTitle() {
        return "Nexus App";
    }

    @Override
    public String getHome() {
        return "PRINCIPAL";
    }

    @Override
    public void registrarTelas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarTelas'");
    }

}