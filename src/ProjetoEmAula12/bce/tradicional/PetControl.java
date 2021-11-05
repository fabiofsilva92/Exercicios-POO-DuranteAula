package ProjetoEmAula12.bce.tradicional;

import ProjetoEmAula12.entity.Pet;
import javafx.beans.property.*;

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

    private List<Pet> listaPet = new ArrayList<>();

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

    public void adicionar(){
        Pet p = getEntity();
        listaPet.add(p);
        System.out.println(listaPet);
    }

    public Pet pesquisar(){
        for (Pet p: listaPet) {
            if(p.getNome().contains(nome.get())){
                setEntity(p);
                break;
            }
        }
        return null;
    }

}
