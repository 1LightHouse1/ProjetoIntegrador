package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioNivelDeAcesso;
import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastrarFuncionario implements Initializable {

    @FXML
    private Button btcadastrar;

    @FXML
    private Button btvoltar;

    @FXML
    private ComboBox<NivelDeAcesso> cbNivelDeAcesso;

    @FXML
    private PasswordField pfsenha;

    @FXML
    private TextField tfcpf;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tflogin;

    @FXML
    private TextField tfnome;

    
    private RepositorioNivelDeAcesso repositorioNivelDeAcesso;
    private RepositorioFuncionario repositorioFuncionario;

    public TelaCadastrarFuncionario(RepositorioFuncionario repositorioFuncionario, RepositorioNivelDeAcesso repositorioNivelDeAcesso){
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioNivelDeAcesso = repositorioNivelDeAcesso;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfnome.getText();
        String login = tflogin.getText();
        String senha = pfsenha.getText();
        String email = tfemail.getText();
        String cpf = tfcpf.getText();
        NivelDeAcesso cargo = cbNivelDeAcesso.getValue();

        String msg = "";
        
        Resultado rs = repositorioFuncionario.cadastrarFuncionario(nome, login, email, senha, cpf, cargo);

        Alert alert;
        msg = rs.getMsg();
        if(rs.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
        }else{
            alert = new Alert(AlertType.INFORMATION,msg);
            
        }

        alert.showAndWait();
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Resultado r1 = repositorioNivelDeAcesso.listarNivelDeAcesso();

        if(r1.foiSucesso()){
            List<NivelDeAcesso> list = (List)r1.comoSucesso().getObj();
            cbNivelDeAcesso.getItems().addAll(list);
        } else{
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }

}
