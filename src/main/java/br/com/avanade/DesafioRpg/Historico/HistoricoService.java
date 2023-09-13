package br.com.avanade.DesafioRpg.Historico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {
    @Autowired
    private HistoricoRepository repository;

    public List<Historico> listarHistorico(){
        return repository.findAll();
    }

    public Historico logarHistorico(String historico){
        Historico mensagem = new Historico();
        mensagem.setTurno(historico);
        return repository.save(mensagem);
    }
}
