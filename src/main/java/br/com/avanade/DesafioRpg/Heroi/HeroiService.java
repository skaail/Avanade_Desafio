package br.com.avanade.DesafioRpg.Heroi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroiService {

    @Autowired
    private HeroiRepository repository;

    public List<Heroi> listarHerois(){
        return repository.findAll();
    }

    public Heroi buscarHeroiPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public Heroi criarHeroi(Heroi heroi){
        if(repository.count() > 0){
            return null;
        }
        return repository.save(gerarClasse(heroi));
    }

    public Heroi updateHeroi(Long id, Heroi heroi){
        if(repository.existsById(id)){
            heroi.setClasse(heroi.getClasse());
            return repository.save(gerarClasse(heroi));
        }
        return null;
    }


    public void zerarVida(Heroi heroi){
        if(heroi.getVida() <= 0) {
            heroi.setVida(0);
        }
    }

    public long contarPersonagens(){
        return repository.count();
    }

    public void deleteHeroi(Long id){
        repository.deleteById(id);
    }

    public Heroi gerarClasse(Heroi heroi){
        switch(heroi.getClasse().toLowerCase()){
            case "guerreiro":
                heroi.setVida(20);
                heroi.setForca(7);
                heroi.setDefesa(5);
                heroi.setAgilidade(6);
                heroi.setQtdDados(1);
                heroi.setFDado(12);
                heroi.setClasse("Guerreiro");
                break;
            case "bárbaro":
                heroi.setVida(21);
                heroi.setForca(10);
                heroi.setDefesa(2);
                heroi.setAgilidade(5);
                heroi.setQtdDados(2);
                heroi.setFDado(8);
                heroi.setClasse("Bárbaro");
                break;
            case "cavaleiro":
                heroi.setVida(26);
                heroi.setForca(6);
                heroi.setDefesa(8);
                heroi.setAgilidade(3);
                heroi.setQtdDados(2);
                heroi.setFDado(6);
                heroi.setClasse("Cavaleiro");
                break;
        }
        return heroi;
    }
}
