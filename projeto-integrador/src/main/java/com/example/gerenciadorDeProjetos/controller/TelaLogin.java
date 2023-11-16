package com.example.gerenciadorDeProjetos.controller;

import com.example.gerenciadorDeProjetos.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    void cadastrar(ActionEvent event) {
        App.pushScreen("CADASTRARFUNCIONARIO");
    }

    @FXML
    void login(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

}
