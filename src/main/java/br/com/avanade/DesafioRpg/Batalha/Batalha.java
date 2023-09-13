package br.com.avanade.DesafioRpg.Batalha;

import br.com.avanade.DesafioRpg.Heroi.Heroi;
import br.com.avanade.DesafioRpg.Monstro.Monstro;
import br.com.avanade.DesafioRpg.Personagem.Personagem;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long heroi_id;

    private Long monstro_id;
}
