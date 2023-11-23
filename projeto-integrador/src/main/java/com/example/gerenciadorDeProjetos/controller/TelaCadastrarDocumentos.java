package com.example.gerenciadorDeProjetos.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.Login;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioDocumentos;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
import com.github.hugoperlin.results.Resultado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class TelaCadastrarDocumentos implements Initializable {

    @FXML
    private Button btcadastrar;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btselecionardoc;

    @FXML
    private Button btvoltar;

    @FXML
    private ComboBox<Projeto> cbProjeto;

    @FXML
    private TextArea tadescricao;

    @FXML
    private TextField tfnome;

    private RepositorioDocumentos repositorioDocumentos;
    private RepositorioProjeto repositorioProjeto;
    private RepositorioFuncionario repositorioFuncionario;
    private File arquivoSelecionado = null;

    public TelaCadastrarDocumentos(RepositorioDocumentos repositorioDocumentos, RepositorioProjeto repositorioProjeto, RepositorioFuncionario repositorioFuncionario) {
        this.repositorioDocumentos = repositorioDocumentos;
        this.repositorioProjeto = repositorioProjeto;
        this.repositorioFuncionario = repositorioFuncionario;

    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfnome.getText();
        String descricao = tadescricao.getText();
        Projeto projeto = cbProjeto.getValue();
        Funcionario funcionario = Login.getFuncionarioAtual();
        Alert alert;
        String msg = "";

        if(arquivoSelecionado != null){
            Resultado rs = repositorioFuncionario.temPermissao();
            msg = rs.getMsg();

            if(rs.foiErro()){
                alert = new Alert(AlertType.ERROR,msg);
                alert.showAndWait();
            } else{
                Resultado rs2 = repositorioDocumentos.adicionarDocumento(nome, descricao, projeto, arquivoSelecionado.getAbsolutePath(), funcionario.getId());
                msg = rs2.getMsg();

                alert = new Alert(AlertType.INFORMATION,msg);
                alert.showAndWait();
            }
        }
            
        
    }

    @FXML
    void cadastrarDocumento(ActionEvent event) {
        App.pushScreen("CADASTRARDOCUMENTO");
    }

    @FXML
    void cancelar(ActionEvent event) {
        tfnome.clear();
        tadescricao.clear();
        cbProjeto.setValue(null);
    }

    @FXML
    void selecionardocumento(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        arquivoSelecionado = fileChooser.showOpenDialog(btselecionardoc.getScene().getWindow());
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("PRINCIPAL");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado r2 = repositorioProjeto.listarProjetos();

        if(r2.foiSucesso()){
            List<Projeto> list = (List)r2.comoSucesso().getObj();
            cbProjeto.getItems().addAll(list);
        } else{
            Alert alert = new Alert(AlertType.ERROR, r2.getMsg());
            alert.showAndWait();
        }
    }

}
