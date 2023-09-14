package br.com.avanade.DesafioRpg.Heroi;

import br.com.avanade.DesafioRpg.Personagem.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroi")
public class HeroiController {
    @Autowired
    private HeroiService service;

    @GetMapping
    public List<Heroi> listarHerois(){
        return service.listarHerois();
    }

    @GetMapping("/{id}")
    public Heroi buscarHeroiId(@PathVariable Long id){
        return service.buscarHeroiPorId(id);
    }

    @PostMapping
    public ResponseEntity<String> criarHeroi(@RequestBody Heroi heroi){
        if(service.criarHeroi(heroi) == null){
            return ResponseEntity.ok("Já existe um heróio nesta aventura");
        }
        service.criarHeroi(heroi);
        return ResponseEntity.ok("O " + heroi.getClasse() + " se juntou à aventura");
    }

    @PutMapping("/{id}")
    public Personagem updateHeroi(@PathVariable Long id, @RequestBody Heroi heroi){
        return  service.updateHeroi(id, heroi);
    }

    @DeleteMapping("/{id}")
    public void deleteHeroi(@PathVariable Long id){
        service.deleteHeroi(id);
    }
}
