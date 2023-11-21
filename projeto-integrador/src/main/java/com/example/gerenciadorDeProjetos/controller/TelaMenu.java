package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioNivelDeAcesso;
import com.github.hugoperlin.results.Resultado;
import com.mysql.cj.log.Log; 

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    private RepositorioNivelDeAcesso repositorioNivelDeAcesso;
    private RepositorioFuncionario repositorioFuncionario;

    public TelaMenu(RepositorioFuncionario repositorioFuncionario, RepositorioNivelDeAcesso repositorioNivelDeAcesso){
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioNivelDeAcesso = repositorioNivelDeAcesso;
    }

    @FXML
    void alterarConta(ActionEvent event) {
        Funcionario funcionario = Login.getFuncionarioAtual();
        
        if(funcionario != null){
            App.pushScreen("CADASTRARFUNCIONARIO", o-> new TelaCadastrarFuncionario(repositorioFuncionario, repositorioNivelDeAcesso, funcionario));
        }
    }

    @FXML
    void excluirConta(ActionEvent event) {
            Funcionario funcionario = Login.getFuncionarioAtual();

            Resultado rs2 = repositorioFuncionario.excluirFuncionario(funcionario.getId());

            String msg = "";
            Alert alert;

            msg = rs2.getMsg();

            alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();

            App.pushScreen("LOGIN");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Funcionario funcionario = Login.getFuncionarioAtual();
        
        lbNome.setText(funcionario.getNome());
        lbEmail.setText(funcionario.getEmail());
        lbCargo.setText(funcionario.getCargo().getTipoDeAcesso());
    }

}
