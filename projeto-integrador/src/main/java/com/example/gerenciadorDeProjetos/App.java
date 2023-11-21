package com.example.gerenciadorDeProjetos;

import com.example.gerenciadorDeProjetos.controller.Principal;
import com.example.gerenciadorDeProjetos.controller.TelaAdicionarFuncionarioProjeto;
import com.example.gerenciadorDeProjetos.controller.TelaAdicionarFuncionarioTarefa;
import com.example.gerenciadorDeProjetos.controller.TelaCadastrarFuncionario;
import com.example.gerenciadorDeProjetos.controller.TelaCadastrarProjeto;
import com.example.gerenciadorDeProjetos.controller.TelaCadastrarTarefa;
import com.example.gerenciadorDeProjetos.controller.TelaListarProjeto;
import com.example.gerenciadorDeProjetos.controller.TelaListarTarefa;
import com.example.gerenciadorDeProjetos.controller.TelaLogin;
import com.example.gerenciadorDeProjetos.controller.TelaMenu;
import com.example.gerenciadorDeProjetos.model.daos.FabricaConexoes;
import com.example.gerenciadorDeProjetos.model.daos.FuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.daos.JDBCFuncionarioDAO;
import com.example.gerenciadorDeProjetos.model.daos.JDBCNivelDeAcessoDAO;
import com.example.gerenciadorDeProjetos.model.daos.JDBCProjetoDAO;
import com.example.gerenciadorDeProjetos.model.daos.JDBCTarefaDAO;
import com.example.gerenciadorDeProjetos.model.daos.NivelDeAcessoDAO;
import com.example.gerenciadorDeProjetos.model.daos.ProjetoDAO;
import com.example.gerenciadorDeProjetos.model.daos.TarefaDAO;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioNivelDeAcesso;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioTarefa;

import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

public class App extends BaseAppNavigator{

    private NivelDeAcessoDAO nivelDeAcessoDAO = new JDBCNivelDeAcessoDAO(FabricaConexoes.getInstance());
    private RepositorioNivelDeAcesso repositorioNivelDeAcesso = new RepositorioNivelDeAcesso(nivelDeAcessoDAO);

    private FuncionarioDAO funcionarioDAO = new JDBCFuncionarioDAO(FabricaConexoes.getInstance());
    private RepositorioFuncionario repositorioFuncionario = new RepositorioFuncionario(funcionarioDAO);

    private ProjetoDAO projetoDAO = new JDBCProjetoDAO(FabricaConexoes.getInstance());
    private RepositorioProjeto repositorioProjeto = new RepositorioProjeto(projetoDAO, funcionarioDAO);

    private TarefaDAO tarefaDAO = new JDBCTarefaDAO(FabricaConexoes.getInstance());
    private RepositorioTarefa repositorioTarefa = new RepositorioTarefa(tarefaDAO, funcionarioDAO, projetoDAO);

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
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));
        registraTela("TELAMENU", new ScreenRegistryFXML(App.class, "telamenu.fxml", o->new TelaMenu(repositorioFuncionario, repositorioNivelDeAcesso)));
        registraTela("CADASTRARPROJETO", new ScreenRegistryFXML(App.class, "cadastrarprojeto.fxml", o->new TelaCadastrarProjeto(repositorioFuncionario, repositorioProjeto)));
        registraTela("LISTARPROJETOS", new ScreenRegistryFXML(App.class, "listarprojeto.fxml", o->new TelaListarProjeto(repositorioProjeto, repositorioFuncionario)));
        registraTela("CADASTRARTAREFA", new ScreenRegistryFXML(App.class, "cadastrartarefa.fxml", o-> new TelaCadastrarTarefa(repositorioTarefa, repositorioProjeto, repositorioFuncionario)));
        registraTela("LISTARTAREFA", new ScreenRegistryFXML(App.class, "listartarefa.fxml", o-> new TelaListarTarefa(repositorioTarefa, repositorioFuncionario, repositorioProjeto)));
        registraTela("ADICIONARUSUARIOPROJETO", new ScreenRegistryFXML(App.class, "adicionarfuncionarioprojeto.fxml", o-> new TelaAdicionarFuncionarioProjeto( repositorioFuncionario, repositorioProjeto)));
        registraTela("ADICIONARUSUARIOTAREFA", new ScreenRegistryFXML(App.class, "adicionarfuncionariotarefa.fxml", o-> new TelaAdicionarFuncionarioTarefa( repositorioFuncionario, repositorioTarefa)));
    }

    

}