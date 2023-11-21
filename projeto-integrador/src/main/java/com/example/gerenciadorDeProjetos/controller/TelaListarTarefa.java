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

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class TelaListarTarefa implements Initializable {

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
    private ListView<Funcionario> lstFuncionarios;

    @FXML
    private TableView<Tarefa> tbTarefa;

    @FXML
    private TableColumn<Tarefa, String> tbcDataTermino;

    @FXML
    private TableColumn<Tarefa, String> tbcProjeto;

    @FXML
    private TableColumn<Tarefa, String> tbcStatus;

    @FXML
    private TableColumn<Tarefa, String> tbcTarefa;

    private RepositorioTarefa repositorioTarefa;
    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioProjeto repositorioProjeto;

    public TelaListarTarefa(RepositorioTarefa repositorioTarefa, RepositorioFuncionario repositorioFuncionario, RepositorioProjeto repositorioProjeto){
        this.repositorioTarefa = repositorioTarefa;
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioProjeto = repositorioProjeto;
    }

    @FXML
    void adicionarUsuario(ActionEvent event) {

    }

    @FXML
    void cadastrarTarefa(ActionEvent event) {
        App.pushScreen("CADASTRARTAREFA");
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void editarTarefa(ActionEvent event) {
        Tarefa tarefa = tbTarefa.getSelectionModel().getSelectedItem();
        
        if(tarefa != null){
            App.pushScreen("CADASTRARTAREFA", o-> new TelaCadastrarTarefa(repositorioTarefa, repositorioProjeto, repositorioFuncionario, tarefa));
        }
    }

    @FXML
    void excluirTarefa(ActionEvent event) {

    }

    @FXML
    void listarTarefa(ActionEvent event) {
        App.pushScreen("LISTARTAREFA");
    }

    @FXML
    void listarTarefas(MouseEvent event) {
        Tarefa tarefa = tbTarefa.getSelectionModel().getSelectedItem();
        lstFuncionarios.getItems().clear();
        Resultado resultado = repositorioFuncionario.buscarFuncionarioTarefa(tarefa.getIdTarefa());
        List<Funcionario> lista = (List<Funcionario>)resultado.comoSucesso().getObj();
        lstFuncionarios.getItems().addAll(lista);
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tbcTarefa.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNomeTarefa()+""));
        tbcProjeto.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getProjeto().getNomeProjeto()+""));
        tbcDataTermino.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getDataTermino()+""));
        tbcStatus.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getStatus()+""));


        Resultado rs = repositorioTarefa.listar();

        if(rs.foiErro()){
            Alert alert = new Alert(AlertType.ERROR, rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Tarefa> lista = (List)rs.comoSucesso().getObj();
        tbTarefa.getItems().addAll(lista);
    }

}
