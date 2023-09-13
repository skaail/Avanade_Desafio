package br.com.avanade.DesafioRpg.Monstro;

import br.com.avanade.DesafioRpg.Personagem.Personagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue(value = "monstro")
public class Monstro extends Personagem {

}
