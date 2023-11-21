package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.entities.Tarefa;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioTarefa;
import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class TelaAdicionarFuncionarioTarefa implements Initializable{

    @FXML
    private Button btcadastrar;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btvoltar;

    @FXML
    private ComboBox<Funcionario> cbFuncionario;

    @FXML
    private ComboBox<Tarefa> cbProjeto1;

    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioTarefa repositorioTarefa;

    public TelaAdicionarFuncionarioTarefa(RepositorioFuncionario repositorioFuncionario, RepositorioTarefa repositorioTarefa) {
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioTarefa = repositorioTarefa;
    }

    @FXML
    void adicionarFuncionario(ActionEvent event) {
        App.pushScreen("ADICIONARUSUARIOTAREFA");
    }   

    @FXML
    void cadastrar(ActionEvent event) {
        Funcionario funcionario = cbFuncionario.getValue();
        Tarefa tarefa = cbProjeto1.getValue();

        Resultado resultado = repositorioTarefa.adicionarFuncionarioTarefa(funcionario.getId(), tarefa.getIdTarefa());

        String msg = "";
        Alert alert;

        if(resultado.foiErro()){
            msg = resultado.getMsg();
            alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        } else{
            msg = resultado.getMsg();
            alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        cbFuncionario.setValue(null);
        cbProjeto1.setValue(null);
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("CADASTRARTAREFA");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado r2 = repositorioTarefa.listar();

        if(r2.foiSucesso()){
            List<Tarefa> list = (List)r2.comoSucesso().getObj();
            cbProjeto1.getItems().addAll(list);
        } else{
            Alert alert = new Alert(AlertType.ERROR, r2.getMsg());
            alert.showAndWait();
        }

        Resultado r1 = repositorioFuncionario.listarFuncionarios();

        if(r1.foiSucesso()){
            List<Funcionario> list = (List)r1.comoSucesso().getObj();
            cbFuncionario.getItems().addAll(list);
        } else{
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }

}
