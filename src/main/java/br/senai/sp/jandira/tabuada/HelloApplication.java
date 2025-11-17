package br.senai.sp.jandira.tabuada;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldMaiorMultiplicador;
    TextField textFieldMenorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {
        //TABUADA
        //2 X 1 = 2
        //multiplicando X multiplicador = produto
        //no programa irá perguntar qual o mínimo e máximo multiplicador e informar o multiplicando

        //definir o tamanho da tela (stage)
//        stage.setWidth(500);
        stage.setHeight(600);
        //controlando o fechamento ao clicar no fechar da janela
        stage.setOnCloseRequest( e ->{
            fechar();
            e.consume(); //abandona a função
        });
        stage.setResizable(false); //bloquear o redimensionamento da janela

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

        //criar o multiplicando

        GridPane gridFormulario = new GridPane();
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);

        Label labelMultiplicando = new Label("Multiplicando: ");
        textFieldMultiplicando = new TextField();

        Label labelMenorMultiplicador = new Label("Menor Multiplicador: ");
        textFieldMenorMultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior Multiplicador: ");
        textFieldMaiorMultiplicador = new TextField();

        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);
        gridFormulario.add(labelMenorMultiplicador, 0, 1);
        gridFormulario.add(textFieldMenorMultiplicador, 1, 1);
        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        //Crir componentes de botoes
        HBox boxBotoes = new HBox();
        boxBotoes.setAlignment(Pos.CENTER_RIGHT);
        boxBotoes.setPadding(new Insets(0, 20, 20,20));
        boxBotoes.setSpacing(10);
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setPrefWidth(90);
        btnCalcular.setPrefHeight(40);

        //recebe como argumento uma outra função
        btnCalcular.setOnAction(e -> {
            calcularTabuada();

        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefWidth(70);
        btnLimpar.prefHeight(40);
        btnLimpar.setOnAction(e -> {
            limparFormulario();
        });
        Button btnSair = new Button("Sair");
        btnSair.setPrefWidth(70);
        btnSair.setPrefHeight(40);
        btnSair.setOnAction(e -> {
           fechar();
        });

        //Adicionar os botoes na boxBotoes
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        //adicionar um componente Listview
        VBox boxResultado = new VBox();
        boxResultado.setPadding(new Insets(0, 20, 20,20));
        Label labelResultado = new Label("Resultado: ");
        labelResultado.setStyle("-fx-text-fill: blue; -fx-font-size: 14; -fx-font-weight: bold");

        //adicionar o ListView
        listaTabuada = new ListView();

        //adicionar label ao box resultado
        boxResultado.getChildren().add(labelResultado);
        boxResultado.getChildren().add(listaTabuada);

        //adicionar componetes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(boxBotoes);
        root.getChildren().add(boxResultado);

        stage.setScene(scene);
        //informação inicial
        stage.setTitle("Tabuada");
        //inicia o programa
        stage.show();

    }

    public void limparFormulario(){
        textFieldMultiplicando.setText("");
        textFieldMenorMultiplicador.setText("");
        textFieldMaiorMultiplicador.setText("");
        listaTabuada.getItems().clear();
        textFieldMultiplicando.requestFocus(); //requisite o foco, peça o foco para você.
    }

    public void fechar(){
        Alert alertaFechar = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Confirma a saída do sistema?",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> resposta = alertaFechar.showAndWait(); //retorna o botão que o usuário pressionou, facilitando a criação de uma condicional.
        if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
            Platform.exit();
        }
    }

    public void calcularTabuada() {

        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
        int menorMultiplicador = Integer.parseInt(textFieldMenorMultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        //se criado para caso o usuário preencha os valores incorretamente, neste caso, se ele preencher o menor multiplicador menor que o maior multiplicador.

        if(menorMultiplicador>maiorMultiplicador){
            int auxiliar = menorMultiplicador;
            menorMultiplicador = maiorMultiplicador;
            maiorMultiplicador = auxiliar;
        }

        int tamanho = maiorMultiplicador - menorMultiplicador + 1;
        String[] tabuada = new String[tamanho];


        int contador = 0;
        while ( contador < tamanho ) {

            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + " X " + menorMultiplicador + " = " + produto;

            contador++;
            menorMultiplicador++;
        }

        listaTabuada.getItems().clear();
        listaTabuada.getItems().addAll(tabuada);
    }

}
