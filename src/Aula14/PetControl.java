package Aula14;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetControl {

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
        boolean encontrado = false;

        for(int i = 0; i<listaPet.size(); i++){
            Pet pet = listaPet.get(i);
            if(pet.getId() == p.getId()){
                listaPet.set(i, p);
                encontrado = true;
                break;
            }
        }
        if(!encontrado){
            listaPet.add(p);
        }
        atualizarListaView();
    }

    public Pet pesquisar(){
        listaView.clear();
        for (Pet p: listaPet) {
            if(p.getNome().contains(nome.get())){
                listaView.add(p);
//                setEntity(p);
//                break;
            }
        }
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
