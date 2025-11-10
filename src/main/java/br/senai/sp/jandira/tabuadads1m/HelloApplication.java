package br.senai.sp.jandira.tabuadads1m;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    TextField textFieldMultiplicando;
    TextField textFieldmenormultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        // Definir o tamanho da tela
        stage.setWidth(500);
        stage.setHeight(500);

        // Componente principal da tela
        VBox root = new VBox();
        Scene scene = new Scene(root);

        // Cabeçalho da tela
        VBox header = new VBox();
        header.setStyle("-fx-padding: 10;-fx-background-color: #ba4129");

        // Adicionar um label ao header
        Label labelTitulo = new Label("Tabuada");
        labelTitulo.setStyle("-fx-text-fill: white;-fx-font-size: 22; -fx-font-weight: Bold");
        Label labelSubTitulo = new Label("Construa tabuadas sem limites");
        labelSubTitulo.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14");

        header.getChildren().add(labelTitulo);
        header.getChildren().add(labelSubTitulo);

        // Criar o multiplicando
        //HBox multiplicandoBox = new HBox(); cria uma coluna horizontal
        //multiplicandoBox.setStyle("-fx-padding: 10;");
        //multiplicandoBox.getChildren().add(labelMultiplicando);
        //multiplicandoBox.getChildren().add(textFieldMultiplicando);
        GridPane gridFormulario = new GridPane();

        Label labelMultiplicando = new Label("Multiplicando"); //adiciona a coluna de texto
        textFieldMultiplicando = new TextField(); //adiciona texto

        Label labelmenormultiplicador = new Label("Menor multiplicador");
         textFieldmenormultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior multiplicador");
         textFieldMaiorMultiplicador = new TextField();

        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);

        gridFormulario.add(labelmenormultiplicador, 0, 1);
        gridFormulario.add(textFieldmenormultiplicador, 1, 1);

        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        // Criar componentes de botões
        HBox boxBotoes = new HBox();
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setOnAction(e -> {
            calcularTabuada();
        });


        Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction(e -> {
            listaTabuada.getItems().clear();
        });

        Button btnSair = new Button("Sair");

        // Adicionar um componentes ao ListView
        VBox boxResultado = new VBox();
        Label labelResultado = new Label("Resultado");
        labelResultado.setStyle("-fx-text-fill: blue; -fx-font-size: 18; -fx-font-weight: Bold");

        // Adicionar o componente o ListView
        listaTabuada = new ListView();

        //Adicionar o label ao boxResultado
        boxResultado.getChildren().add(labelResultado);
        boxResultado.getChildren().add(listaTabuada);

        //Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(boxBotoes);
        root.getChildren().add(boxResultado);

        //Adicionar todos os Botoes com o addAll
        boxBotoes.getChildren().addAll(btnCalcular, btnLimpar, btnSair);

        stage.setScene(scene);
        stage.setTitle("Tabuada");
        stage.show();
    }
    public void calcularTabuada() {
        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
        int menorMultiplicador = Integer.parseInt(textFieldmenormultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        int tamanho = maiorMultiplicador-menorMultiplicador +1;
        String[] tabuada = new String[tamanho];

        if (menorMultiplicador > maiorMultiplicador) {
            int auxiliar = maiorMultiplicador;
            maiorMultiplicador = menorMultiplicador;
            menorMultiplicador = auxiliar;
        }

        int contador = 0;
        while (contador < tamanho) {
            double produto = multiplicando * menorMultiplicador;
            tabuada[contador] = multiplicando + " X " + menorMultiplicador + " = " + produto;
            contador++;
            menorMultiplicador++;
        }

        listaTabuada.getItems().clear();
        listaTabuada.getItems().addAll(tabuada);

    }
}
