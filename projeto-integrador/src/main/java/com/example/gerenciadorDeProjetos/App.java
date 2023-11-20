package com.example.gerenciadorDeProjetos;

import com.example.gerenciadorDeProjetos.controller.Principal;
import com.example.gerenciadorDeProjetos.controller.TelaCadastrarFuncionario;
import com.example.gerenciadorDeProjetos.controller.TelaLogin;
import com.example.gerenciadorDeProjetos.controller.TelaMenu;
import com.example.gerenciadorDeProjetos.model.daos.FabricaConexoes;
import com.example.gerenciadorDeProjetos.model.daos.FuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.daos.JDBCFuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.daos.JDBCNivelDeAcessoDAO;
import com.example.gerenciadorDeProjetos.model.daos.NivelDeAcessoDAO;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioNivelDeAcesso;

import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    private NivelDeAcessoDAO nivelDeAcessoDAO = new JDBCNivelDeAcessoDAO(FabricaConexoes.getInstance());
    private RepositorioNivelDeAcesso repositorioNivelDeAcesso = new RepositorioNivelDeAcesso(nivelDeAcessoDAO);

    private FuncionarioDAO funcionarioDAO = new JDBCFuncionarioDAO(FabricaConexoes.getInstance());
    private RepositorioFuncionario repositorioFuncionario = new RepositorioFuncionario(funcionarioDAO);

    private Login login;

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
        registraTela("LOGIN", new ScreenRegistryFXML(App.class, "telalogin.fxml", o->new TelaLogin(repositorioFuncionario)));
        registraTela("CADASTRARFUNCIONARIO", new ScreenRegistryFXML(App.class, "cadastrarfuncionario.fxml", o->new TelaCadastrarFuncionario( repositorioFuncionario, repositorioNivelDeAcesso)));
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal(login)));
        registraTela("TELAMENU", new ScreenRegistryFXML(App.class, "telamenu.fxml", o->new TelaMenu(login)));
    }

}