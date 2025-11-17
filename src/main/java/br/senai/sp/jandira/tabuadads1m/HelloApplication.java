package br.senai.sp.jandira.tabuadads1m;

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
    TextField textFieldMenormultiplicador;
    TextField textFieldMaiorMultiplicador;
    ListView listaTabuada;

    @Override
    public void start(Stage stage) throws IOException {

        // Definir o tamanho da tela
        stage.setHeight(600);


        //Controlando o fechamento ao clicar no fechar da janela
        stage.setOnCloseRequest(
                event -> {
                    fechar();
                    event.consume();
                }
        );

        // Bloquear o redimensionamento da janela
        stage.setResizable(false);

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

        //multiplicandoBox.getChildren().add(textFieldMultiplicando);
        GridPane gridFormulario = new GridPane();
        gridFormulario.setPadding(new Insets(20));
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);

        Label labelMultiplicando = new Label("Multiplicando"); //adiciona a coluna de texto
        textFieldMultiplicando = new TextField(); //adiciona texto

        Label labelmenormultiplicador = new Label("Menor multiplicador");
         textFieldMenormultiplicador = new TextField();

        Label labelMaiorMultiplicador = new Label("Maior multiplicador");
         textFieldMaiorMultiplicador = new TextField();

        gridFormulario.add(labelMultiplicando, 0, 0);
        gridFormulario.add(textFieldMultiplicando, 1, 0);

        gridFormulario.add(labelmenormultiplicador, 0, 1);
        gridFormulario.add(textFieldMenormultiplicador, 1, 1);

        gridFormulario.add(labelMaiorMultiplicador, 0, 2);
        gridFormulario.add(textFieldMaiorMultiplicador, 1, 2);

        // Criar componentes de botões
        HBox boxBotoes = new HBox();
        boxBotoes.setAlignment(Pos.CENTER_LEFT);
        boxBotoes.setPadding(new Insets(0, 20, 20, 20));
        boxBotoes.setSpacing(10);
        Button btnCalcular = new Button("Calcular");
        btnCalcular.setPrefWidth(90);
        btnCalcular.setPrefHeight(150);
        btnCalcular.setOnAction(e -> {
            calcularTabuada();
        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefWidth(70);
        btnLimpar.setPrefHeight(150);
        btnLimpar.setOnAction(e -> {
            limparFormulario();
        });

        Button btnSair = new Button("Sair");
        btnSair.setPrefWidth(70);
        btnSair.prefHeight(150);
        btnSair.setOnAction(e -> {
           fechar();
        });

        // Adicionar um componentes ao ListView
        VBox boxResultado = new VBox();
        boxResultado.setPadding(new Insets(0, 20, 20, 20));

        Label labelResultado = new Label("Resultado");
        labelResultado.setStyle("-fx-text-fill: #ba4129; -fx-font-size: 18; -fx-font-weight: Bold");

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

    public void fechar() {
        Alert alertaFechar = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Confirma a saida do sistema?",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional <ButtonType> resposta = alertaFechar.showAndWait();
        if (resposta.isPresent() && resposta.get() == ButtonType.YES) {
            Platform.exit();
        }
    }

    public void limparFormulario(){
        textFieldMultiplicando.setText("");
        textFieldMenormultiplicador.setText("");
        textFieldMaiorMultiplicador.setText("");
        listaTabuada.getItems().clear();
        textFieldMultiplicando.requestFocus();
    }

    public void calcularTabuada() {
        int multiplicando = Integer.parseInt(textFieldMultiplicando.getText());
        int menorMultiplicador = Integer.parseInt(textFieldMenormultiplicador.getText());
        int maiorMultiplicador = Integer.parseInt(textFieldMaiorMultiplicador.getText());

        if (menorMultiplicador > maiorMultiplicador) {
            int auxiliar = menorMultiplicador;
            menorMultiplicador = maiorMultiplicador;
            maiorMultiplicador = auxiliar;
        }

        int tamanho = maiorMultiplicador - menorMultiplicador +1;
        String[] tabuada = new String[tamanho];

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
