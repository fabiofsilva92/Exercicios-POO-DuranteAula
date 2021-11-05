package ProjetoEmAula12.boundary;

import ProjetoEmAula12.control.PetControl;
import ProjetoEmAula12.entity.Pet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
//        Stage stage = new Stage();

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
            Pet p= this.boundaryToEntity();
            control.adicionar(p);
            txtPeso.setText("");
            txtNascimento.setText("");
            txtRaca.setText("");
            txtNome.setText("");
            txtId.setText(String.valueOf(Integer.parseInt(txtId.getText()) +1));
        });
        
        btnPesquisar.setOnAction(e -> {
            boolean encontrado = false;
            Pet p = control.pesquisar(txtNome.getText());
            if(p == null){
                Alert a = new Alert((Alert.AlertType.INFORMATION), "entity.Pet não encontrado");
                a.showAndWait();
            } else{
                entityToBoundary(p);
            }
        });

        Scene scn = new Scene(panPrincipal, 600, 400);

        stage.setScene(scn);
        stage.setTitle("Gestão de Pets");
        stage.show();


    }

    public Pet boundaryToEntity(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Pet p = new Pet();

        try{
            p.setId(Long.parseLong(txtId.getText()));
            p.setNome(txtNome.getText());
            p.setRaca(txtRaca.getText());
            p.setPeso(Double.parseDouble(txtPeso.getText()));
            LocalDate dt = LocalDate.parse(txtNascimento.getText(), dtf);
            p.setNascimento(dt);
        } catch(Exception e){
            System.out.println("Error "+ e.getMessage());
        }
        return p;
    }

    public void entityToBoundary(Pet p){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(p != null){
            txtId.setText( String.valueOf(p.getId()));
            txtNome.setText(p.getNome());
            txtRaca.setText(p.getRaca());
            txtPeso.setText(String.valueOf(p.getPeso()));
            String strNasciment = dtf.format(p.getNascimento());
            txtNascimento.setText(strNasciment);
        }
    }

    public static void main(String[] args) {
        Application.launch(PetBoundary.class, args);
    }

}
