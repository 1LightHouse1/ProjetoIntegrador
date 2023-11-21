package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

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

    @FXML
    private ListView<Funcionario> lstFuncionarios;

    private RepositorioProjeto repositorioProjeto;
    private RepositorioFuncionario repositorioFuncionario;

    public TelaListarProjeto(RepositorioProjeto repositorioProjeto, RepositorioFuncionario repositorioFuncionario){
        this.repositorioProjeto = repositorioProjeto;
        this.repositorioFuncionario = repositorioFuncionario;
    }

    @FXML
    void adicionarUsuario(ActionEvent event) {
        App.pushScreen("ADICIONARUSUARIO");
    }

    @FXML
    void cadastrarProjeto(ActionEvent event) {
        App.pushScreen("CADASTRARPROJETO");
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @FXML
    void editarProjeto(ActionEvent event) {
        Projeto projeto = tbProjeto.getSelectionModel().getSelectedItem();
        
        if(projeto != null){
            App.pushScreen("CADASTRARPROJETO", o-> new TelaCadastrarProjeto(repositorioFuncionario, repositorioProjeto, projeto));
        }
    }

    @FXML
    void excluirProjeto(ActionEvent event) {
        Resultado rs = repositorioFuncionario.temPermissao();

        String msg = "";
        Alert alert;

        if(rs.foiErro()){
            msg = rs.getMsg();
            alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        } else{
            Projeto projeto = tbProjeto.getSelectionModel().getSelectedItem();

            repositorioProjeto.excluirProjeto(projeto.getIdProjeto());

            App.pushScreen("LISTARPROJETOS");
        }

    }

    @FXML
    void listarProjeto(ActionEvent event) {
        App.pushScreen("LISTARPROJETOS");
    }

    @FXML
    void listarProjetos(MouseEvent event) {
        Projeto projetos = tbProjeto.getSelectionModel().getSelectedItem();
        lstFuncionarios.getItems().clear();
        Resultado resultado = repositorioFuncionario.buscarFuncionarioProjeto(projetos.getIdProjeto());
        List<Funcionario> lista = (List<Funcionario>)resultado.comoSucesso().getObj();
        lstFuncionarios.getItems().addAll(lista);
        
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
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
