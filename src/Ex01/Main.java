package Ex01;

public class Main {

    public static void main(String[] args) {
        SuperInteressante revista = new SuperInteressante();
        Leitor jose = new Leitor();

        revista.registrar(jose);
        revista.publicarArtigo("Fatec ZL faz parceria com o MIT para desenvolvimento de novas tecnologias");
    }

}
