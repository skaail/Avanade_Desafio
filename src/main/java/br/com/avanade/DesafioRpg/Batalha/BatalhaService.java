package br.com.avanade.DesafioRpg.Batalha;

import br.com.avanade.DesafioRpg.Heroi.HeroiRepository;
import br.com.avanade.DesafioRpg.Monstro.MonstroRepository;
import br.com.avanade.DesafioRpg.Personagem.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatalhaService {

    @Autowired
    private BatalhaRepository repository;

    public int rolarDado(int lados){
        return (int) (Math.random() * lados) + 1;
    }

    public boolean calcularIniciativa(){
        int iniciativaHeroi = rolarDado(20);
        int iniciativaMonstro = rolarDado(20);

        return iniciativaHeroi > iniciativaMonstro;
    }

    public int calcularAtaque(Personagem personagem) {
        int dadoD12 = rolarDado(12);
        int ataque = dadoD12 + personagem.getForca() + personagem.getAgilidade();
        return ataque;
    }

    public int calcularDefesa(Personagem personagem) {
        int dadoD12 = rolarDado(12);
        int defesa = dadoD12 + personagem.getDefesa() + personagem.getAgilidade();
        return defesa;
    }

    public int calcularDano(Personagem atacante) {
        int dano = 0;

        for(int i = 0; i < atacante.getQtdDados(); i++){
            dano += rolarDado(atacante.getFDado());

        }
        return dano;
    }

    public boolean batalhaTerminou(Personagem heroi, Personagem monstro) {
        return heroi.getVida() <= 0 || monstro.getVida() <= 0;
    }

    public Batalha criarBatalha(Batalha batalha){
        return repository.save(batalha);
    }
}
