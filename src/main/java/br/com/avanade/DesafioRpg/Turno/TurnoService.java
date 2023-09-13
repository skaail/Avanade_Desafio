package br.com.avanade.DesafioRpg.Turno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository repository;

    public List<Turno> listarTurnos(){
        return repository.findAll();
    }

    public Turno criarTurno(Turno turno){
        return repository.save(turno);
    }
}
