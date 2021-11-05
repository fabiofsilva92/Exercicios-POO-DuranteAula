package Aula14;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PetControl {

    private static final String DBURL = "jdbc:mariadb://localhost/petdb";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";

//    private long id = 0;
//    private String nome = "";
//    private String raca = "";
//    private double peso = 0;
//    private LocalDate nascimento = LocalDate.now();

    LongProperty id = new SimpleLongProperty(0);
    StringProperty nome = new SimpleStringProperty("");
    StringProperty raca = new SimpleStringProperty("");
    DoubleProperty peso = new SimpleDoubleProperty(0d);
    ObjectProperty nascimento = new SimpleObjectProperty(LocalDate.now());

    private static long counter = 0;

    private List<Pet> listaPet = new ArrayList<>();

    private ObservableList<Pet> listaView = FXCollections.observableArrayList();


    public Pet getEntity(){
        Pet p = new Pet();
        p.setId(id.get());
        p.setNome(nome.get());
        p.setRaca(raca.get());
        p.setPeso(peso.get());
        p.setNascimento((LocalDate) nascimento.get());
        return p;
    }

    public void setEntity(Pet p){
        id.set(p.getId());
        nome.set(p.getNome());
        raca.set(p.getRaca());
        peso.set(p.getPeso());
        nascimento.set(p.getNascimento());
    }

    public void salvar(){
        Pet p = getEntity();
//        boolean encontrado = false;
//
//        for(int i = 0; i<listaPet.size(); i++){
//            Pet pet = listaPet.get(i);
//            if(pet.getId() == p.getId()){
//                listaPet.set(i, p);
//                encontrado = true;
//                break;
//            }
//        }
//        if(!encontrado){
//            listaPet.add(p);
//        }

        try{
            Connection con =DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = String.format(Locale.ROOT,
                    "INSERT INTO pet (id, nome, raca, peso, nascimento)" +
                            "VALUES (%d, '%s', '%s', %f, '%s')",
                    p.getId(),
                    p.getNome(),
                    p.getRaca(),
                    p.getPeso(),
                    p.getNascimento()
                    );

            System.out.println(sql);

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();

            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        atualizarListaView();
    }

    public Pet pesquisar(){
        listaView.clear();

        try{
            Connection con =DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String query = "SELECT * FROM pet WHERE nome like '%"+nome.get()+"%'";
            System.out.println(query);

            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Pet p = new Pet();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setRaca(rs.getString("raca"));
                p.setPeso(rs.getDouble("peso"));
                p.setNascimento(rs.getDate("nascimento").toLocalDate());

                listaView.add(p);
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }

//        for (Pet p: listaPet) {
//            if(p.getNome().contains(nome.get())){
//                listaView.add(p);
////                setEntity(p);
////                break;
//            }
//        }
        return null;
    }

    public void remover(long id) {
        for (Pet p: listaPet) {
            if(p.getId() == id){
                listaPet.remove(p);
                break;
            }
        }
        atualizarListaView();
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(listaPet);
    }

    public void novoPet() {
        Pet p = new Pet();
        p.setId(++counter);
        setEntity(p);
    }

    public ObservableList<Pet> getListaPet(){
        return listaView;
    }



}
