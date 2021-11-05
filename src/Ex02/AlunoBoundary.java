package Ex02;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Coded by Fabio F. Silva

public class AlunoBoundary extends Application {

    private TextField txtId = new TextField();
    private TextField txtRA = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtNascimento = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");

    private AlunoControl control = new AlunoControl();

    private TableView<Aluno> table = new TableView<>();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void criarTabela(){

        TableColumn<Aluno, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Aluno, Integer> col2 = new TableColumn<>("RA");
        col2.setCellValueFactory(new PropertyValueFactory<>("RA"));

        TableColumn<Aluno, String> col3 = new TableColumn<>("Nome");
        col3.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Aluno, String> col4 = new TableColumn<>("Nascimento");
        col4.setCellValueFactory( (AlunoProp) -> {
            LocalDate n = AlunoProp.getValue().getNascimento();
            String strData = n.format(this.dtf);
            return new ReadOnlyStringWrapper(strData);
        });

        table.getColumns().addAll(col1, col2, col3, col4);

        table.setItems(control.getListaAluno());

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

        GridPane panCampos = new GridPane();
        BorderPane panPrincipal = new BorderPane();

        Bindings.bindBidirectional(txtId.textProperty(), control.id,  new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtRA.textProperty(), control.RA, new NumberStringConverter());
        Bindings.bindBidirectional(txtNascimento.textProperty(), control.nascimento, new LocalDateStringConverter());

        panCampos.add(new Label("ID"), 0, 0);
        panCampos.add(txtId, 1, 0);

        panCampos.add(new Label("RA"), 0, 1);
        panCampos.add(txtRA, 1, 1);

        panCampos.add(new Label("Nome"), 0, 2);
        panCampos.add(txtNome, 1, 2);

        panCampos.add(new Label("Nascimento"), 0, 3);
        panCampos.add(txtNascimento, 1, 3);

        panCampos.add(btnAdicionar, 0 , 4);
        panCampos.add(btnPesquisar,1, 4);

        btnAdicionar.setOnAction(e -> {

            control.adicionarAluno();
        });

        btnPesquisar.setOnAction(e -> {

           control.pesquisarAluno();

        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();
        Scene scn = new Scene(panPrincipal, 600, 400);

        stage.setScene(scn);
        stage.setTitle("Gestão de alunos");
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(AlunoBoundary.class, args);
    }
}
