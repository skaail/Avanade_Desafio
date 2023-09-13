package br.com.avanade.DesafioRpg.Monstro;

import br.com.avanade.DesafioRpg.Personagem.Personagem;
import br.com.avanade.DesafioRpg.Personagem.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonstroService {
    @Autowired
    private MonstroRepository repository;

    public List<Monstro> listarMonstros(){
        return repository.findAll();
    }

    public Monstro criarMonstro(Monstro monstro){
        if(repository.count() > 0){
            return null;
        }
        return repository.save(gerarMonstro(monstro));
    }

    public Long countMonstro(){
        return repository.count();
    }

    public Monstro gerarMonstroAleatório(int rand){
        Monstro monstro = new Monstro();
        switch (rand){
            case 1:
                monstro.setVida(42);
                monstro.setForca(7);
                monstro.setDefesa(1);
                monstro.setAgilidade(2);
                monstro.setQtdDados(3);
                monstro.setFDado(4);
                monstro.setClasse("Orc");
                break;
            case 2:
                monstro.setVida(34);
                monstro.setForca(10);
                monstro.setDefesa(4);
                monstro.setAgilidade(4);
                monstro.setQtdDados(2);
                monstro.setFDado(4);
                monstro.setClasse("Gigante");
                break;
            case 3:
                monstro.setVida(34);
                monstro.setForca(7);
                monstro.setDefesa(4);
                monstro.setAgilidade(7);
                monstro.setQtdDados(2);
                monstro.setFDado(4);
                monstro.setClasse("Lobisomen");
                break;
        }
        repository.save(monstro);
        return monstro;
    }

    public void zerarVida(Monstro monstro){
        if(monstro.getVida() <= 0) {
            monstro.setVida(0);
        }
    }

    public void matarMonstro(Monstro monstro){
        if(monstro.getVida() <= 0){
            repository.deleteById(monstro.getId());
        }
    }

    public Monstro buscarMonstroPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public Monstro gerarMonstro(Monstro monstro){
        switch(monstro.getClasse().toLowerCase()){
            case "orc":
                monstro.setVida(42);
                monstro.setForca(7);
                monstro.setDefesa(1);
                monstro.setAgilidade(2);
                monstro.setQtdDados(3);
                monstro.setFDado(4);
                monstro.setClasse("Orc");
                break;
            case "gigante":
                monstro.setVida(34);
                monstro.setForca(10);
                monstro.setDefesa(4);
                monstro.setAgilidade(4);
                monstro.setQtdDados(2);
                monstro.setFDado(4);
                monstro.setClasse("Gigante");
                break;
            case "lobisomen":
                monstro.setVida(34);
                monstro.setForca(7);
                monstro.setDefesa(4);
                monstro.setAgilidade(7);
                monstro.setQtdDados(2);
                monstro.setFDado(4);
                monstro.setClasse("Lobisomen");
                break;
        }
        return monstro;
    }
}
