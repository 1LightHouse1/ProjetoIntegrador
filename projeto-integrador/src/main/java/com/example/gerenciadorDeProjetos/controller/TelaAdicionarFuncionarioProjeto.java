package com.example.gerenciadorDeProjetos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.gerenciadorDeProjetos.App;
import com.example.gerenciadorDeProjetos.model.entities.Funcionario;
import com.example.gerenciadorDeProjetos.model.entities.NivelDeAcesso;
import com.example.gerenciadorDeProjetos.model.entities.Projeto;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioFuncionario;
import com.example.gerenciadorDeProjetos.model.repositories.RepositorioProjeto;
import com.github.hugoperlin.results.Resultado;
import com.mysql.cj.x.protobuf.MysqlxCrud.Projection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class TelaAdicionarFuncionarioProjeto implements Initializable {

    @FXML
    private Button btcadastrar;

    @FXML
    private Button btcancelar;

    @FXML
    private Button btvoltar;

    @FXML
    private ComboBox<Funcionario> cbFuncionario;

    @FXML
    private ComboBox<Projeto> cbProjeto1;

    private RepositorioFuncionario repositorioFuncionario;
    private RepositorioProjeto repositorioProjeto;

    public TelaAdicionarFuncionarioProjeto(RepositorioFuncionario repositorioFuncionario, RepositorioProjeto repositorioProjeto) {
        this.repositorioFuncionario = repositorioFuncionario;
        this.repositorioProjeto = repositorioProjeto;
    }

    @FXML
    void adicionarFuncionario(ActionEvent event) {
        App.pushScreen("ADICIONARUSUARIOPROJETO");
    }

    @FXML
    void cadastrar(ActionEvent event) {
        Funcionario funcionario = cbFuncionario.getValue();
        Projeto projeto = cbProjeto1.getValue();

        String msg = "";
        Alert alert;

        Resultado rs = repositorioFuncionario.temPermissao();

        if(rs.foiErro()){
            msg = rs.getMsg();
            alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        } else{
            Resultado resultado = repositorioProjeto.adicionarFuncionarioProojeto(funcionario.getId(), projeto.getIdProjeto());

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

    }   

    @FXML
    void cancelar(ActionEvent event) {
        cbFuncionario.setValue(null);
        cbProjeto1.setValue(null);
    }

    @FXML
    void voltar(ActionEvent event) {
        App.pushScreen("CADASTRARPROJETO");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Resultado r2 = repositorioProjeto.listarProjetos();

        if(r2.foiSucesso()){
            List<Projeto> list = (List)r2.comoSucesso().getObj();
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
