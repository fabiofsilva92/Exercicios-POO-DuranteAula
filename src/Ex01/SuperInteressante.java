package Ex01;

import java.util.ArrayList;
import java.util.List;

public class SuperInteressante implements Revista, Subject {

    List<Observer> observerList = new ArrayList<>();

    public void publicarArtigo(String artigo){

        notificar();
    }

    @Override
    public String artigo() {
        return "Fatec ZL faz parceria com o MIT para desenvolvimento de novas tecnologias";
    }

    @Override
    public void notificar() {
        for (Observer o: observerList) {
            o.update(this);
        }
    }

    @Override
    public void registrar(Observer o) {
        observerList.add(o);
    }
}
