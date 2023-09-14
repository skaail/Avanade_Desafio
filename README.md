# Desafio Avanade REST API AD&D

## Descrição do desafio
Criar uma API Rest usando Sava SpringBoot com um banco de dados Postgres para controlar um jogo em turnos baseado em AD&D

### Teconologias ultilizadas

* SpringBoot
* Gradle
* Postgres
* Docker



## Como rodar o projeto

1. Clonar este repositório
```
$ git clone https://github.com/skaail/Avanade_Desafio.git
```

2. Verificar se o Docker está instalado na máquina
```
$ docker info
```

3. Rodar o projeto dentro do Eclipse ou InteliJ

# Documentação API

Como requisitado no desafio, foi-se desenvolvida uma api rest para controlar o andamento do jogo com o seguinte fluxo de dados.

1. Criar um Heroi 
1. Criar um Monstro (Opcional)
1. Iniciar um combate
    1. Rolar iniciativa
    1. Calcular acerto do ataque
    1. calcular dano
1. Salvar turnos

Para realizar o fluxo requisitado use os seguintes endpoints

### Endpoint padrão - "localhost:8080/api"

### Heroi

<details>
 <summary><code>POST</code> <code><b>/heroi</b></code> <code>(Criar um heroi)</code></summary>

##### Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Classe      |  required | String   | Com base na lista de classes disponívels  |

##### Response
```
Heroi criado com sucesso
```


</details>

<details>
 <summary><code>GET</code> <code><b>/heroi</b></code> <code>(Buscar todos os herois)</code></summary>
 

##### Response
 ```
{
	"id": 1,
	"classe": "Guerreiro",
	"vida": 20,
	"forca": 7,
	"defesa": 1,
	"agilidade": 2,
	"qtdDados": 3,
	"fdado": 4
}
```
</details>

<details>
 <summary><code>GET</code> <code><b>/heroi/{id}</b></code> <code>(Heroi por id)</code></summary>
 

##### Response
 ```
{
	"id": 1,
	"classe": "Guerreiro",
	"vida": 20,
	"forca": 7,
	"defesa": 1,
	"agilidade": 2,
	"qtdDados": 3,
	"fdado": 4
}
```
</details>

 <details>
 <summary><code>PUT</code> <code><b>/heroi/{id}</b></code> <code>(Atualizar um herói)</code></summary>

##### Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Classe      |  required | String   | Com base na lista de classes disponívels  |

##### Response
 ```
{
	"id": 1,
	"classe": "Guerreiro",
	"vida": 20,
	"forca": 7,
	"defesa": 1,
	"agilidade": 2,
	"qtdDados": 3,
	"fdado": 4
}
```


</details>

<details>
 <summary><code>DELETE</code> <code><b>/heroi/{id}</b></code> <code>(Deletar um herói por id)</code></summary>


##### Response
```
Heroi deletado com sucesso
```


</details>


### Monstro

<details>
 <summary><code>POST</code> <code><b>/monstro</b></code> <code>(Criar um monstro)</code></summary>

##### Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | Classe      |  required | String   | Com base na lista de classes disponívels  |

##### Response
```
Monstro criado com sucesso
```

</details>

<details>
 <summary><code>GET</code> <code><b>/monstro</b></code> <code>(Buscar todos os monstros)</code></summary>
 

##### Response
 ```
{
	"id": 1,
	"classe": "Orc",
	"vida": 42,
	"forca": 7,
	"defesa": 1,
	"agilidade": 2,
	"qtdDados": 3,
	"fdado": 4
}
```
</details>

### Batalha

<details>
 <summary><code>POST</code> <code><b>/batalha/iniciar</b></code> <code>(Criar uma batalha)</code></summary>

##### Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | heroi_id      |  required | Long   | Passar o ID do herói cadastrado  |
> | monstro_id      |  optional | Long   | Caso não existir será gerado um monstro aleatório  |

##### Response
```
Uma batalha entre Guerreiro e Lobisomen foi iniciada!
```

</details>

<details>
 <summary><code>POST</code> <code><b>/batalha/turno</b></code> <code>(Roda o novo turno)</code></summary>

##### Parameters

> | name      |  type     | data type               | description                                                           |
> |-----------|-----------|-------------------------|-----------------------------------------------------------------------|
> | heroi_id      |  required | Long   | Passar o ID do herói cadastrado  |
> | monstro_id      |  required | Long   | Passar o ID do mosntro gerado  |

##### Response
```
O Gigante atacou o Guerreiro causando 4 de dano! Agora ele tem 16 de vida!
```

</details>

<details>
 <summary><code>GET</code> <code><b>/batalha/historico</b></code> <code>(Monstra o histórico de turnos da batalha)</code></summary>
 

##### Response
 ```
[
	{
		"id": 1,
		"turno": "O Gigante atacou o Guerreiro causando 4 de dano! Agora ele tem 16 de vida!"
	},
	{
		"id": 2,
		"turno": "O Guerreiro atacou o Gigante causando 2 de dano! Agora ele tem 32 de vida!"
	}
]
```
</details>

<details>
 <summary><code>POST</code> <code><b>/batalha/reiniciar</b></code> <code>(Reinicia a aventura)</code></summary>

##### Response
```
Aventura reiniciada
```

</details>

## Regras do jogo

* Se um monstro morrer ao iniciar outra batalha deve se passar o id do novo monstro gerado
* Só pode ser criado um Heroi
* Caso o Heroi fique com 0 de vida o jogo é finalizado, para começar um novo faça um post na roda localhost>8080/api/batalha/reiniciar
