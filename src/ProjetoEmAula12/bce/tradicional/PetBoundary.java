package ProjetoEmAula12.bce.tradicional;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;



public class PetBoundary extends Application {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtRaca = new TextField();
    private TextField txtPeso = new TextField();
    private TextField txtNascimento = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");

    private PetControl control = new PetControl();

    @Override
    public void start(Stage stage) throws Exception{

        GridPane panPrincipal = new GridPane();

        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtRaca.textProperty(), control.raca);
        Bindings.bindBidirectional(txtPeso.textProperty(), control.peso, new NumberStringConverter());
        Bindings.bindBidirectional(txtNascimento.textProperty(), control.nascimento, new LocalDateStringConverter());

        panPrincipal.add(new Label("Id"), 0, 0);
        panPrincipal.add(txtId, 1, 0);

        panPrincipal.add(new Label("Nome"), 0, 1);
        panPrincipal.add(txtNome, 1, 1);

        panPrincipal.add(new Label("Raca"), 0, 2);
        panPrincipal.add(txtRaca, 1, 2);

        panPrincipal.add(new Label("Peso"), 0, 3);
        panPrincipal.add(txtPeso, 1, 3);

        panPrincipal.add(new Label("Nascimento"), 0, 4);
        panPrincipal.add(txtNascimento, 1, 4);

        panPrincipal.add(btnAdicionar, 0 , 5);
        panPrincipal.add(btnPesquisar, 1 , 5);

        

        btnAdicionar.setOnAction(e -> {
            control.adicionar();
            txtPeso.setText("");
            txtNascimento.setText("");
            txtRaca.setText("");
            txtNome.setText("");
            txtId.setText(String.valueOf(Integer.parseInt(txtId.getText()) +1));
        });
        
        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
        });

        Scene scn = new Scene(panPrincipal, 600, 400);

        stage.setScene(scn);
        stage.setTitle("Gest??o de Pets");
        stage.show();


    }

    public static void main(String[] args) {
        Application.launch(PetBoundary.class, args);
    }

}
