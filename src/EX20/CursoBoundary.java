package EX20;

import EX20.Curso;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import javafx.beans.binding.Bindings;
import java.util.ArrayList;
import java.util.List;

//Coded by Fabio F Silva

public class CursoBoundary extends Application {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtCodCurso = new TextField();
    private TextField txtnomeCord = new TextField();
    private TextField txtQtdAlunos = new TextField();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private CursoControl control = new CursoControl();

    private TableView<Curso> table = new TableView<>();


    private void criarTabela(){

        TableColumn<Curso, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Curso, String> col2 = new TableColumn<>("Nome do Curso");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Curso, Integer> col3 = new TableColumn<>("Cod. Curso");
        col3.setCellValueFactory(new PropertyValueFactory<>("codCurso"));

        TableColumn<Curso, String> col4 = new TableColumn<>("Nome do Coordenador");
        col4.setCellValueFactory(new PropertyValueFactory<>("nomeCord"));

        TableColumn<Curso, Integer> col5 = new TableColumn<>("Quantidade de Alunos");
        col5.setCellValueFactory(new PropertyValueFactory<>("qtdAlunos"));

        table.getColumns().addAll(col1, col2, col3, col4, col5);
        table.setItems(control.getListaCurso());

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    if(novo != null){
                        control.setEntity(novo);
                    }
                }
        );
    }


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtCodCurso.textProperty(), control.codCurso, new NumberStringConverter());
        Bindings.bindBidirectional(txtnomeCord.textProperty(), control.nomeCord);
        Bindings.bindBidirectional(txtQtdAlunos.textProperty(), control.qtdAlunos, new NumberStringConverter());


        panCampos.add(new Label("ID"), 0, 0);
        panCampos.add(txtId, 1, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1, 1);

        panCampos.add(new Label("Codigo do Curso"), 0, 2);
        panCampos.add(txtCodCurso, 1, 2);

        panCampos.add(new Label("Nome Cordenador"), 0, 3);
        panCampos.add(txtnomeCord, 1, 3);

        panCampos.add(new Label("Quantidade de Alunos"), 0, 4);
        panCampos.add(txtQtdAlunos, 1, 4);

        panCampos.add(btnSalvar, 0 , 5);
        panCampos.add(btnPesquisar,1, 5);

        btnSalvar.setOnAction(e -> {
            control.salvar();

        });

        btnPesquisar.setOnAction(e -> {
                control.pesquisar();
            });


        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();
        Scene scn = new Scene(panPrincipal, 800, 600);

        stage.setScene(scn);
        stage.setTitle("Gestao Cursos");
        stage.show();
    }



    public static void main(String[] args) {
        Application.launch(CursoBoundary.class, args);
    }
}
