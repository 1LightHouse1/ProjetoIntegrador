package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
import com.github.hugoperlin.results.Resultado;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class TelaListarProjeto implements Initializable {

    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btadicionar;

    @FXML
    private Button btcadastrar;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btlistar;

    @FXML
    private Button btvoltar;

    @FXML
    private TableView<Projeto> tbProjeto;

    @FXML
    private TableColumn<Projeto, String> tbcDataInicio;

    @FXML
    private TableColumn<Projeto, String> tbcDataTermino;

    @FXML
    private TableColumn<Projeto, String> tbcNome;

    @FXML
    private TableColumn<Projeto, String> tbcStatus;

    private RepositorioProjeto repositorioProjeto;

    public TelaListarProjeto(RepositorioProjeto repositorioProjeto){
        this.repositorioProjeto = repositorioProjeto;
    }

    @FXML
    void adicionarUsuario(ActionEvent event) {

    }

    @FXML
    void cadastrarProjeto(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void editarProjeto(ActionEvent event) {

    }

    @FXML
    void excluirProjeto(ActionEvent event) {

    }

    @FXML
    void listarProjeto(ActionEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tbcNome.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNomeProjeto()+""));
        tbcDataInicio.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getDataInicio()+""));
        tbcDataTermino.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getDataTermino()+""));
        tbcStatus.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getStatus()+""));

        Resultado rs = repositorioProjeto.listar();

        if(rs.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Projeto> lista = (List)rs.comoSucesso().getObj();

        tbProjeto.getItems().addAll(lista);
    }

}
