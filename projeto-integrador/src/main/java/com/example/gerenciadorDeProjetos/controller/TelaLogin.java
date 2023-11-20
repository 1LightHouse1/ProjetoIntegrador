package com.example.gerenciadorDeProjetos.controller;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioNivelDeAcesso;
import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin {

    @FXML
    private Button btcadastrar;

    @FXML
    private Button btlogin;

    @FXML
    private PasswordField pfsenha;

    @FXML
    private TextField tflogin;

    private RepositorioFuncionario repositorioFuncionario;

    public TelaLogin(RepositorioFuncionario repositorioFuncionario){
        this.repositorioFuncionario = repositorioFuncionario;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        App.pushScreen("CADASTRARFUNCIONARIO");
    }

    @FXML
    void login(ActionEvent event) {
        String login = tflogin.getText();
        String senha = pfsenha.getText();

        String msg = "";
        
        Resultado rs = repositorioFuncionario.login(login, senha);

        Alert alert;
        
        msg = rs.getMsg();
        if(rs.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }else{
            Funcionario funcionario = (Funcionario)rs.comoSucesso().getObj();
            Login loginAtual = new Login(funcionario);
            App.pushScreen("PRINCIPAL", o-> new Principal(loginAtual));
        }

    }

}
