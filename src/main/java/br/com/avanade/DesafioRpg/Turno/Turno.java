package br.com.avanade.DesafioRpg.Turno;

import br.com.avanade.DesafioRpg.Personagem.Personagem;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Personagem atacante;

    @ManyToOne
    private Personagem defensor;
}
