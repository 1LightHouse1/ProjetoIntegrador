package com.example.gerenciadorDeProjetos.controller;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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


    @FXML
    void abrirdocumentos(ActionEvent event) {

    }

    @FXML
    void abrirprojetos(ActionEvent event) {

        App.pushScreen("CADASTRARPROJETO");

    }

    @FXML
    void abrirtarefa(ActionEvent event) {
        App.pushScreen("CADASTRARTAREFA");
    }

    @FXML
    void menuUsuario(ActionEvent event) {
        App.pushScreen("TELAMENU");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("LOGIN");
    }

}
