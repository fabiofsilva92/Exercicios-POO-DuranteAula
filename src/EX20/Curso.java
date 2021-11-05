package EX20;

public class Curso {

    private long id = 0;
    private String nome = "";
    private Integer codCurso = 0;
    private String nomeCord = "";
    private Integer qtdAlunos = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(Integer codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomeCord() {
        return nomeCord;
    }

    public void setNomeCord(String nomeCord) {
        this.nomeCord = nomeCord;
    }

    public Integer getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(Integer qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
