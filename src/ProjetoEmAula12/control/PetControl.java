package ProjetoEmAula12.control;

import ProjetoEmAula12.entity.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetControl {

    private List<Pet> listaPet = new ArrayList<>();

    public void adicionar(Pet p){
        listaPet.add(p);
        System.out.println(listaPet);
    }

    public Pet pesquisar(String nome){
        for (Pet p: listaPet) {
            if(p.getNome().contains(nome)){
                return p;
            }
        }
        return null;
    }

}
