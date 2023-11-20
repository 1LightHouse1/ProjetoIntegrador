package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastrarProjeto implements Initializable {

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
    private DatePicker dpdatainicio;

    @FXML
    private DatePicker dpdatatermino;

    @FXML
    private TextArea tadescricao;

    @FXML
    private TextField tfnome;

    @FXML
    private TextField tfstatus;

    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioProjeto repositorioProjeto;
    private Projeto projeto;

    public TelaCadastrarProjeto(RepositorioFuncionario repositorioFuncionario, RepositorioProjeto repositorioProjeto){
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioProjeto = repositorioProjeto;
    }

    public TelaCadastrarProjeto(RepositorioFuncionario repositorioFuncionario, RepositorioProjeto repositorioProjeto, Projeto projeto){
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioProjeto = repositorioProjeto;
        this.projeto = projeto;
    }

    @FXML
    void ListarProjeto(ActionEvent event) {
        App.pushScreen("LISTARPROJETOS");
    }

    @FXML
    void adicionarUsuario(ActionEvent event) {
        App.pushScreen("ADICIONARFUNCIONARIO");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfnome.getText();
        String status = tfstatus.getText();
        String descricao = tadescricao.getText();
        LocalDate dataInicio = dpdatainicio.getValue();
        LocalDate dataTermino = dpdatatermino.getValue();

        String msg = "";
        Alert alert;

        if(projeto == null){
            Resultado rs = repositorioFuncionario.temPermissao();
    
            msg = rs.getMsg();
    
            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
                alert.showAndWait();
            } else{
                Funcionario funcionario = repositorioFuncionario.funcionarioLogado();
    
                Resultado rs2 = repositorioProjeto.cadastrarProjeto(nome, status, descricao, dataInicio, dataTermino, funcionario.getId());
    
                msg = rs2.getMsg();
    
                alert = new Alert(AlertType.INFORMATION,msg);
                alert.showAndWait();
            }
        } else {
            Resultado rs = repositorioFuncionario.temPermissao();
            msg = rs.getMsg();

            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
                alert.showAndWait();
            } else{
                rs = repositorioProjeto.alterarProjeto(nome, status, descricao, dataInicio, dataTermino, projeto.getIdProjeto());
                msg = rs.getMsg();

                alert = new Alert(AlertType.INFORMATION,msg);
                alert.showAndWait();
            }
            
        }

    }

    @FXML
    void cadastrarProjeto(ActionEvent event) {
        App.pushScreen("CADASTRARPROJETO");
    }

    @FXML
    void cancelar(ActionEvent event) {
        tfnome.clear();
        tfstatus.clear();
        tadescricao.clear();
        dpdatainicio.setValue(null);
        dpdatatermino.setValue(null);
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(projeto != null){
            tfnome.setText(projeto.getNomeProjeto());
            tadescricao.setText(projeto.getDescricao());
            tfstatus.setText(projeto.getStatus());
            dpdatainicio.setValue(projeto.getDataInicio());
            dpdatatermino.setValue(projeto.getDataTermino());
        }
    }

}

