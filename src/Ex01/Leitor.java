package Ex01;

public class Leitor implements Observer{


    @Override
    public void update(Revista r) {
        System.out.println("Lendo artigo + "+r.artigo());
    }
}
