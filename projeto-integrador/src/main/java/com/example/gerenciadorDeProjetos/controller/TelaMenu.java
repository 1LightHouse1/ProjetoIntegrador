package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.mysql.cj.log.Log;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TelaMenu implements Initializable {

    @FXML
    private Button btalterar;

    @FXML
    private Button btexcluir;

    @FXML
    private Button btvoltar;

    @FXML
    private Label lbCargo;

    @FXML
    private Label lbEmail;

    @FXML
    private Label lbNome;

    private Login login;

    public TelaMenu(Login login){
        this.login = login;

    }

    @FXML
    void alterarConta(ActionEvent event) {

    }

    @FXML
    void excluirConta(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        lbNome.setText(login.getFuncionarioAtual().getNome());
        lbEmail.setText(login.getFuncionarioAtual().getEmail());
        lbCargo.setText(login.getFuncionarioAtual().getCargo().getTipoDeAcesso());
    }

}
