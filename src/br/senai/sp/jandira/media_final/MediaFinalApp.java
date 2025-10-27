package br.senai.sp.jandira.media_final;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MediaFinalApp extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        // Determinar o tamanho do Stage
        stage.setWidth(600);
        stage.setHeight(400);

        // Determinar o titulo do stage (tela/janela)
        stage.setTitle("Média Final");

        // Painel Raiz (root)
        BorderPane root = new BorderPane();

        Label labelTitulo = new Label();
        labelTitulo.setText("Escola \"Prof. Vicente Amato\"");
        // Formatação do texto da Label
        labelTitulo.setStyle("-fx-text-fill: #c72020; -fx-font-size: 16; -fx-font-bold;");
        labelTitulo.setPadding(new Insets(10,0,10,10));

        // Painel de resultados - parte de baixo
        VBox painelResultado = new VBox();
        painelResultado.setPadding(new Insets(0,0,10,10));
        Label labelAluno = new Label("Nome do Aluno: ");
        Label labelMediaFinal = new Label("Media Final: ");
        Label labelSituacao = new Label("Situação: ");
        painelResultado.getChildren().addAll(labelAluno, labelMediaFinal, labelSituacao);

        // Painel de botões
        VBox painelDeBotoes = new VBox();
        painelDeBotoes.setPadding(new Insets(0,10, 10,10));
        painelDeBotoes.setSpacing(10);
        Button buttonCalcularMedia = new Button("Calcular Média");
        buttonCalcularMedia.setPrefWidth(150);
        buttonCalcularMedia.setPrefHeight(40);
        Button buttonLimpar = new Button("Limpar");
        buttonLimpar.setPrefWidth(150);
        buttonLimpar.setPrefHeight(40);
        Button buttonSair = new Button("Sair");
        buttonSair.setPrefWidth(150);
        buttonSair.setPrefHeight(40);
        painelDeBotoes.getChildren().addAll(buttonCalcularMedia, buttonLimpar, buttonSair);

        // Painel formulário
        VBox painelFormulario = new VBox();
        painelFormulario.setPadding(new Insets(0,0,10,10));
        Label labelNome = new Label("Nome do Aluno");
        Label labelNota1 = new Label("Nota 1:");
        Label labelNota2 = new Label("Nota 2:");
        Label labelNota3 = new Label("Nota 3:");
        Label labelNota4 = new Label("Nota 4:");
        TextField textFieldNome = new TextField();
        TextField textFieldNota1 = new TextField();
        TextField textFieldNota2 = new TextField();
        TextField textFieldNota3 = new TextField();
        TextField textFieldNota4 = new TextField();
        painelFormulario.getChildren().addAll(
                labelNome, textFieldNome,
                labelNota1, textFieldNota1,
                labelNota2, textFieldNota2,
                labelNota3, textFieldNota3,
                labelNota4, textFieldNota4
        );





        root.setTop(labelTitulo);
        root.setBottom(painelResultado);
        root.setRight(painelDeBotoes);
        root.setLeft(painelFormulario);

        Scene scene = new Scene(root);

        stage.setScene(scene);


        // Mostrar a stage (tela)
        stage.show();

        // Eventos de clique dos botões
        buttonCalcularMedia.setOnAction( click ->{
            System.out.println("Botão clicado!");
            String nomeDigitado = textFieldNome.getText();
            labelAluno.setText("Nome do Aluno: " + nomeDigitado);


            //Criar um vetor de notas
            double[] notas = new double[4];
            String[] notasStr = new String[4];

            //calcular média
            //obter as notas
            notasStr[0] = textFieldNota1.getText();
            notas[0] = Double.parseDouble(notasStr[0]);

            notasStr[1] = textFieldNota2.getText();
            notas[1] = Double.parseDouble(notasStr[1]);

            notasStr[2] = textFieldNota3.getText();
            notas[2] = Double.parseDouble(notasStr[2]);

            notasStr[3] = textFieldNota4.getText();
            notas[3] = Double.parseDouble(notasStr[3]);

            // Uso de Loop while (enquanto)
            double mediaFinal = 0.0;
            int i = 0;
            while (i < notas.length){
                mediaFinal = mediaFinal + notas[i];
                i = i + 1;

            }

            mediaFinal = mediaFinal / notas.length;

            String mediaFinalStr = String.format("%.1f",mediaFinal);

            System.out.println(mediaFinal);

            labelMediaFinal.setText("Media final: " + mediaFinalStr);

            // Status do Aluno
            if (mediaFinal < 4){
                System.out.println("Reprovado!");
                labelSituacao.setText("Situação: Reprovado!" );
            }else if(mediaFinal >= 6){
                System.out.println("Aprovado!");
                labelSituacao.setText("Situação: Aprovado!");
            }else {
                System.out.println("Recuperação!");
                labelSituacao.setText("Situação: Recuperação!");
            }

        });

        buttonLimpar.setOnAction(click ->{
            textFieldNome.clear();
            textFieldNota1.clear();
            textFieldNota2.clear();
            textFieldNota3.clear();
            textFieldNota4.clear();
            labelAluno.setText("Nome do Aluno: ");
            labelMediaFinal.setText("Média Final: ");
            labelSituacao.setText("Situação: ");
            textFieldNome.requestFocus();

        });

        buttonSair.setOnAction(click -> {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Deseja fechar o programa?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> botaoPressionado = alerta.showAndWait();
            if(botaoPressionado.get() == ButtonType.YES){
                System.exit(0);
            }

        });


    }
}
