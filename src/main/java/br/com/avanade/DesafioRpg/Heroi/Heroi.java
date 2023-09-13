package br.com.avanade.DesafioRpg.Heroi;

import br.com.avanade.DesafioRpg.Personagem.Personagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue(value = "heroi")
public class Heroi extends Personagem {
}
