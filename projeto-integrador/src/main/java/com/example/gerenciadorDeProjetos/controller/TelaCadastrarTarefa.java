package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaCadastrarTarefa implements Initializable {

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
    private ComboBox<Projeto> cbProjeto;

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


    private RepositorioTarefa repositorioTarefa;
    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioProjeto repositorioProjeto;
    private Tarefa tarefa;

    public TelaCadastrarTarefa(RepositorioTarefa repositorioTarefa, RepositorioProjeto repositorioProjeto, RepositorioFuncionario repositorioFuncionario){
        this.repositorioTarefa = repositorioTarefa;
        this.repositorioProjeto = repositorioProjeto;
        this.repositorioFuncionario = repositorioFuncionario;
    }

    public TelaCadastrarTarefa(RepositorioTarefa repositorioTarefa, RepositorioProjeto repositorioProjeto, RepositorioFuncionario repositorioFuncionario, Tarefa tarefa){
        this.repositorioTarefa = repositorioTarefa;
        this.repositorioProjeto = repositorioProjeto;
        this.repositorioFuncionario = repositorioFuncionario;
        this.tarefa = tarefa;
    }

    @FXML
    void adicionarUsuario(ActionEvent event) {
        App.pushScreen("ADICIONARUSUARIO");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfnome.getText();
        String status = tfstatus.getText();
        String descricao = tadescricao.getText();
        LocalDate dataInicio = dpdatainicio.getValue();
        LocalDate dataTermino = dpdatatermino.getValue();
        Projeto projeto = cbProjeto.getValue();

        String msg = "";
        Alert alert;

        if(tarefa == null){
           
            Funcionario funcionario = repositorioFuncionario.funcionarioLogado();
    
            Resultado rs = repositorioTarefa.cadastrarTarefa(nome, status, descricao, dataInicio, dataTermino, projeto, funcionario.getId());
    
            msg = rs.getMsg();

            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
                alert.showAndWait();
            } else{
                alert = new Alert(AlertType.INFORMATION,msg);
                alert.showAndWait();
            }

        } else {
            Resultado rs = repositorioTarefa.alterarProjeto(nome, status, descricao, dataInicio, dataTermino, projeto, tarefa.getIdTarefa());
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
    void cadastrarTarefa(ActionEvent event) {
        App.pushScreen("CADASTRARTAREFA");
    }

    @FXML
    void cancelar(ActionEvent event) {
        tfnome.clear();
        tfstatus.clear();
        tadescricao.clear();
        dpdatainicio.setValue(null);
        dpdatatermino.setValue(null);
        cbProjeto.setValue(null);
    }

    @FXML
    void listarTarefa(ActionEvent event) {
        App.pushScreen("LISTARTAREFAS");
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado r1 = repositorioProjeto.listar();

        if(r1.foiSucesso()){
            List<Projeto> list = (List)r1.comoSucesso().getObj();
            cbProjeto.getItems().addAll(list);
        } else{
            Alert alert = new Alert(AlertType.ERROR, r1.getMsg());
            alert.showAndWait();
        }
    }

}
