package br.com.avanade.DesafioRpg.Batalha;

import br.com.avanade.DesafioRpg.Heroi.Heroi;
import br.com.avanade.DesafioRpg.Heroi.HeroiRepository;
import br.com.avanade.DesafioRpg.Heroi.HeroiService;
import br.com.avanade.DesafioRpg.Historico.Historico;
import br.com.avanade.DesafioRpg.Historico.HistoricoService;
import br.com.avanade.DesafioRpg.Monstro.Monstro;
import br.com.avanade.DesafioRpg.Monstro.MonstroRepository;
import br.com.avanade.DesafioRpg.Monstro.MonstroService;
import br.com.avanade.DesafioRpg.Turno.Turno;
import br.com.avanade.DesafioRpg.Turno.TurnoRepository;
import br.com.avanade.DesafioRpg.Turno.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("batalha")
public class BatalhaController {
    @Autowired
    private BatalhaService service;

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private BatalhaRepository repository;

    @Autowired
    private HeroiRepository heroiRepository;

    @Autowired
    private MonstroRepository monstroRepository;;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private HeroiService heroiService;

    @Autowired
    private MonstroService monstroService;

    @Autowired
    private HistoricoService historicoService;


    private boolean turnoHeroi;

    @GetMapping
    public List<Batalha> listarBatalha(){
        return repository.findAll();
    }

    @PostMapping("/iniciar")
    public ResponseEntity<String> iniciarBatalha(@RequestBody Batalha batalha) {

        if(repository.count() > 0){
            return ResponseEntity.ok("Já existe uma batalha ativa");
        }
        Heroi heroi = new Heroi();
        Monstro monstro = new Monstro();

        heroi = (Heroi) heroiService.buscarHeroiPorId(batalha.getHeroi_id());
        monstro = (Monstro) monstroService.buscarMonstroPorId(batalha.getMonstro_id());

        if(monstro == null || monstro.getVida() == 0){
            monstro = monstroService.gerarMonstroAleatório((int) ((Math.random() * (4 - 1)) + 1));
        }

        if(heroi == null){
            return ResponseEntity.ok("Não existe nenhum héroi na aventura!");
        }
        if(heroi.getVida() == 0){
            return ResponseEntity.ok("Seu herói está morto");
        }
        if(service.calcularIniciativa()){
            turnoHeroi = true;
        }else{
            turnoHeroi = false;
        }
        service.criarBatalha(batalha);
        return ResponseEntity.ok("Uma batalha entre " + heroi.getClasse() + " e " + monstro.getClasse() + " foi iniciada!");
    }

    @PostMapping("/turno")
    public ResponseEntity<String> realizarTurno(@RequestBody Batalha batalha) {

        if(repository.count() == 0){
            return ResponseEntity.ok("Não existe nenhuma batalha ativa");
        }

        Turno turno = new Turno();
        Heroi heroi = (Heroi) heroiService.buscarHeroiPorId(batalha.getHeroi_id());
        Monstro monstro = (Monstro) monstroService.buscarMonstroPorId(batalha.getMonstro_id());
        String message;


        if(service.batalhaTerminou(heroi, monstro)){
            repository.deleteAll();
            message = ("A batalha terminou!");
            historicoService.logarHistorico(message);
            return ResponseEntity.ok(message);
        };
        int dano = 0;
        if(turnoHeroi){
            turno.setAtacante(heroi);
            turno.setDefensor(monstro);
        }else{
            turno.setDefensor(heroi);
            turno.setAtacante(monstro);
        }

        turnoHeroi = !turnoHeroi;

        if(service.calcularAtaque(turno.getAtacante()) > service.calcularDefesa(turno.getDefensor())){
            dano = service.calcularDano(turno.getAtacante());
            turno.getDefensor().setVida(turno.getDefensor().getVida() - dano);

            monstroService.zerarVida(monstro);
            heroiService.zerarVida(heroi);

            turnoService.criarTurno(turno);
            if(service.batalhaTerminou(heroi, monstro)){
                message = ("O " + turno.getAtacante().getClasse() + " derrotou o " + turno.getDefensor().getClasse());

                historicoService.logarHistorico(message);
                repository.deleteAll();

                return ResponseEntity.ok(message);
            };
            message = ("O " + turno.getAtacante().getClasse() + " atacou o " + turno.getDefensor().getClasse() + " causando " + dano + " de dano! Agora ele tem " + turno.getDefensor().getVida() + " de vida!");
            historicoService.logarHistorico(message);

            return ResponseEntity.ok(message);
        }

        turnoService.criarTurno(turno);
        message = ("O ataque errou!");
        historicoService.logarHistorico(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<Historico>> consultarHistorico() {
        return ResponseEntity.ok(historicoService.listarHistorico());
    }

    @PostMapping("/reiniciar")
    public ResponseEntity<String> reiniciarAventura(){
        turnoRepository.deleteAll();
        monstroRepository.deleteAll();
        heroiRepository.deleteAll();
        repository.deleteAll();

        return ResponseEntity.ok("Aventura reiniciada");
    }
}
