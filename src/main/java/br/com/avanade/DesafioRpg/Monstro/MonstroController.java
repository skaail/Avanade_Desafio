package br.com.avanade.DesafioRpg.Monstro;

import br.com.avanade.DesafioRpg.Personagem.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monstro")
public class MonstroController {
    @Autowired
    private MonstroService service;

    @GetMapping
    public List<Monstro> listarMonstros(){
        return service.listarMonstros();
    }

    @PostMapping
    public ResponseEntity<String> criarMonstro(@RequestBody Monstro monstro){
        if(service.criarMonstro(monstro) == null){
            return ResponseEntity.ok("Já existe um monstro nesta aventura");
        }
        service.criarMonstro(monstro);
        return ResponseEntity.ok("O " + monstro.getClasse() + " se juntou à aventura");
    }
}
