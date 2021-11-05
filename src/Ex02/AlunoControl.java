package Ex02;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Coded by Fabio F. Silva

public class AlunoControl {

    LongProperty id = new SimpleLongProperty(0);
    IntegerProperty RA = new SimpleIntegerProperty(0);
    StringProperty nome = new SimpleStringProperty("");
    ObjectProperty nascimento = new SimpleObjectProperty(LocalDate.now());

//    private List<Aluno> listaAluno = new ArrayList<>();

    private ObservableList<Aluno> listaAluno = FXCollections.observableArrayList();

    public Aluno getEntity(){
        Aluno a = new Aluno();
        a.setId(id.get());
        a.setRA(RA.get());
        a.setNome(nome.get());
        a.setNascimento((LocalDate)nascimento.get());
        return a;
    }

    public void setEntity(Aluno a){
        id.set(a.getId());
        RA.set(a.getRA());
        nome.set(a.getNome());
        nascimento.set(a.getNascimento());
    }

    public void adicionarAluno(){
        Aluno a = getEntity();
        listaAluno.add(a);

    }

    public Aluno pesquisarAluno(){
        for (Aluno aluno: listaAluno){
            if(aluno.getNome().contains(nome.get())){
                setEntity(aluno);
                return aluno;
            }
        }
        return null;
    }

    public ObservableList<Aluno> getListaAluno(){
        return listaAluno;
    }

}
