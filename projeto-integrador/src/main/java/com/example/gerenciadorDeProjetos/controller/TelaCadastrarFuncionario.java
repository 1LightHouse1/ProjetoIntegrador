package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
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
    private Funcionario funcionario;

    public TelaCadastrarFuncionario(RepositorioFuncionario repositorioFuncionario, RepositorioNivelDeAcesso repositorioNivelDeAcesso){
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioNivelDeAcesso = repositorioNivelDeAcesso;
    }

    public TelaCadastrarFuncionario(RepositorioFuncionario repositorioFuncionario, RepositorioNivelDeAcesso repositorioNivelDeAcesso, Funcionario funcionario){
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioNivelDeAcesso = repositorioNivelDeAcesso;
        this.funcionario = funcionario;
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
        Alert alert;

        if(funcionario == null){
            Resultado rs = repositorioFuncionario.cadastrarFuncionarios(nome, login, email, senha, cpf, cargo);

            msg = rs.getMsg();
            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
            }else{
                alert = new Alert(AlertType.INFORMATION,msg);
                
            }

            alert.showAndWait();
        } else{
            Resultado rs = repositorioFuncionario.alterarFuncionario(nome, login, email, senha, cpf, cargo, funcionario.getId());

            msg = rs.getMsg();

            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
                alert.showAndWait();
            } else{
                alert = new Alert(AlertType.INFORMATION,msg);
                alert.showAndWait();
            }
        }
        
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("LOGIN");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        if(funcionario != null){
            tfnome.setText(funcionario.getNome());
            tflogin.setText(funcionario.getLogin());
            tfemail.setText(funcionario.getEmail());
            pfsenha.setText(funcionario.getSenha());
            tfcpf.setText(funcionario.getCpf());
            cbNivelDeAcesso.setValue(funcionario.getCargo());

            Resultado r1 = repositorioNivelDeAcesso.listarNivelDeAcesso();

            if(r1.foiSucesso()){
            List<NivelDeAcesso> list = (List)r1.comoSucesso().getObj();
            cbNivelDeAcesso.getItems().addAll(list);
            } else{
                Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
                alert.showAndWait();
            }
        }else{
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

}
