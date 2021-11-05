package Ex02;

import java.time.LocalDate;

//Coded by Fabio F. Silva

public class Aluno {

    private long id = 0;
    private Integer RA = 0;
    private String nome = "";
    private LocalDate nascimento = LocalDate.now();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRA() {
        return RA;
    }

    public void setRA(Integer RA) {
        this.RA = RA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
