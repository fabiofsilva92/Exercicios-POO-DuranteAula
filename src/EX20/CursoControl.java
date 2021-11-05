package EX20;

import Aula13.tradicional.Pet;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CursoControl {

    LongProperty id = new SimpleLongProperty(0);
    StringProperty nome = new SimpleStringProperty("");
    IntegerProperty codCurso = new SimpleIntegerProperty(0);
    StringProperty nomeCord = new SimpleStringProperty("");
    IntegerProperty qtdAlunos = new SimpleIntegerProperty(0);

    private List<Curso> listaCurso = new ArrayList<>();

    private ObservableList<Curso> listaView = FXCollections.observableArrayList();


    public Curso getEntity(){
        Curso c = new Curso();
        c.setId(id.get());
        c.setNome(nome.get());
        c.setCodCurso(codCurso.get());
        c.setNomeCord(nomeCord.get());
        c.setQtdAlunos(qtdAlunos.get());
        return c;
    }

    public void setEntity(Curso c){
        id.set(c.getId());
        nome.set(c.getNome());
        codCurso.set(c.getCodCurso());
        nomeCord.set(c.getNomeCord());
        qtdAlunos.set(c.getQtdAlunos());
    }


    public void salvar() {

        Curso c = getEntity();
        listaCurso.add(c);
        atualizarListaView();


    }

    public void pesquisar() {
        listaView.clear();
        for(Curso c: listaCurso){
            if(c.getNome().contains(nome.get())){
                listaView.add(c);
            }
        }
    }

    public ObservableList<Curso> getListaCurso(){
        return listaView;
    }

    public void atualizarListaView(){
        listaView.clear();
        listaView.addAll(listaCurso);
    }
}
