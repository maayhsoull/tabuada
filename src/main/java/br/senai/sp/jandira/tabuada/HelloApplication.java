package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //TABUADA
        //2 X 1 = 2
        //multiplicando X multiplicador = produto
        //no programa irá perguntar qual o mínimo e máximo multiplicador e informar o multiplicando

        //definir o tamanho da tela (stage)
        stage.setWidth(500);
        stage.setHeight(500);

        //componete principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);

        //cabeçalho
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #b835e6");

        //adicionar um label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white; -fx-font-size: 30; -fx-font-weight: bold");
        Label labelSubtitulo = new Label("Construa tabuadas sem limites!");
        labelSubtitulo.setStyle("-fx-text-fill: white; -fx-font-size: 14 ;");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubtitulo);

        //colocar o header no root
        root.getChildren().addAll(header);

        //criar o multiplicando
        HBox multiplicandoBox = new HBox();
        multiplicandoBox.setStyle("-fx-padding: 10;");
        Label labelMultiplicando = new Label("Multiplicando: ");
        TextField textFieldMultiplicando = new TextField();

        multiplicandoBox.getChildren().add(labelMultiplicando);
        multiplicandoBox.getChildren().add(textFieldMultiplicando);

        //Colocamos o multiplicandoBox no root
        root.getChildren().add(multiplicandoBox);











        stage.setScene(scene);
        //informação inicial
        stage.setTitle("Tabuada");
        //inicia o programa
        stage.show();

    }
}
