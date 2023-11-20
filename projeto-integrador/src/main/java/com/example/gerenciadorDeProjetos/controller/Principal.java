package com.example.gerenciadorDeProjetos.controller;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Principal {

    @FXML
    private Button btdocumentos;

    @FXML
    private Button btmenu;

    @FXML
    private Button btprojetos;

    @FXML
    private Button bttarefa;

    @FXML
    private Button btvoltar;

    private Login login;

    public Principal(Login login){
        this.login = login;
    }

    @FXML
    void abrirdocumentos(ActionEvent event) {

    }

    @FXML
    void abrirprojetos(ActionEvent event) {

    }

    @FXML
    void abrirtarefa(ActionEvent event) {

    }

    @FXML
    void menuUsuario(ActionEvent event) {
        App.pushScreen("TELAMENU", o-> new TelaMenu(login));
    }

    @FXML
    void voltar(ActionEvent event) {

    }

}
