package br.com.avanade.DesafioRpg.Personagem;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorColumn(name = "tipo")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classe;
    private int vida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int qtdDados;
    private int fDado;
}

