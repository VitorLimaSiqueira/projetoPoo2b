# Mapeamento Objeto-Relacional com Spring Data JPA

##  Componentes da Dupla
* Vitor de Lima
* Vito Zanata

## Tema do Projeto
**Sistema de Streaming de Vídeo (Estilo Netflix)** O projeto consiste no mapeamento completo do ecossistema de uma plataforma de streaming, gerenciando utilizadores, assinaturas, perfis independentes, catálogos de conteúdos (divididos de forma polimórfica entre Filmes e Séries), episódios, categorização por géneros e avaliações dos conteúdos de forma bidirecional.

---

## Estratégia de Herança Escolhida
A estratégia de herança utilizada para a classe pai `Conteudo` e as suas subclasses `Filme` e `Serie` foi a **`InheritanceType.JOINED`** (Tabela por Subclasse).

### Porquê esta escolha?
* **Normalização do Banco de Dados:** Evita colunas nulas no banco de dados (o que aconteceria na estratégia `SINGLE_TABLE`, onde os atributos específicos de `Serie` ficariam nulos para registos de `Filme`).
* **Integridade de Dados:** Permite a aplicação de restrições de integridade (`NOT NULL`, chaves estrangeiras) diretamente nas tabelas das subclasses.
* **Comportamento no Banco:** O Hibernate gera uma tabela base `conteudo` (armazenando o `id` e o `titulo`) e tabelas separadas `filme` e `serie` que partilham a mesma chave primária através de uma relação de chave estrangeira com a tabela pai.

---

## Explicação dos Relacionamentos Usados

1. **Relacionamento 1:1 (Um para Um) — `Usuario` ↔ `Assinatura`**
   * *Explicação:* Cada utilizador pode possuir apenas uma assinatura ativa no sistema, e cada assinatura está vinculada a exatamente um utilizador único.
   * *Mapeamento:* Configurado com `@OneToOne` em ambas as classes, utilizando `@JoinColumn(name = "assinatura_id")` na classe `Usuario` (dona da relação) e o atributo `mappedBy = "assinatura"` na classe `Assinatura` para torná-lo bidirecional.

2. **Relacionamento 1:N e N:1 (Um para Muitos) — `Usuario` ↔ `Perfil`**
   * *Explicação:* Um utilizador titular pode criar múltiplos perfis na sua conta (ex: perfil dos pais, perfil das crianças), mas cada perfil pertence obrigatoriamente a apenas um utilizador.
   * *Mapeamento:* `@OneToMany(mappedBy = "usuario")` em `Usuario` e `@ManyToOne` com `@JoinColumn(name = "usuario_id")` em `Perfil`.

3. **Relacionamento 1:N e N:1 — `Serie` ↔ `Episodio`**
   * *Explicação:* Uma série de televisão é composta por vários episódios, enquanto que um episódio específico pertence a uma única série.
   * *Mapeamento:* `@OneToMany(mappedBy = "serie")` na classe `Serie` e `@ManyToOne` com `@JoinColumn(name = "serie_id")` na classe `Episodio`.

4. **Relacionamento N:M (Muitos para Muitos) — `Conteudo` ↔ `Categoria`**
   * *Explicação:* Um conteúdo (Filme ou Série) pode pertencer a várias categorias ao mesmo tempo (ex: "A Origem" é Ficção Científica e Drama). Da mesma forma, uma categoria possui muitos conteúdos associados a ela.
   * *Mapeamento:* Configurado com `@ManyToMany`. Na classe `Conteudo`, foi gerada a tabela intermediária no banco de dados através da anotação `@JoinTable(name = "conteudo_categoria")`. A classe `Categoria` mapeia o lado inverso com `mappedBy = "categorias"`.

5. **Associação Bidirecional Obrigatória — `Perfil` ↔ `Avaliacao` ↔ `Conteudo`**
   * *Explicação:* Uma `Avaliacao` serve como a tabela de ligação enriquecida do sistema. Um `Perfil` realiza várias avaliações e um `Conteudo` recebe várias avaliações. 
   * *Funcionamento Bidirecional:* Garante que, ao aceder a um objeto `Conteudo`, o sistema consiga listar diretamente todas as notas e comentários que ele recebeu, sem a necessidade de queries manuais. Mapeado via `@ManyToOne` em `Avaliacao` e mapeado com `@OneToMany(mappedBy = "conteudo")` na classe `Conteudo`.

---

## Diagrama Mermaid do Projeto

Abaixo encontra-se a representação visual do mapeamento de entidades gerado pelo JPA para este projeto:

```mermaid
classDiagram
    direction OA
    
    class Usuario {
        +Long id
        +String nome
        +String email
        +Assinatura assinatura
        +List~Perfil~ perfis
    }
    
    class Assinatura {
        +Long id
        +String plano
        +Usuario usuario
    }
    
    class Perfil {
        +Long id
        +String apelido
        +Usuario usuario
        +List~Avaliacao~ avaliacoes
    }
    
    class Conteudo {
        <<Abstract/Parent>>
        +Long id
        +String titulo
        +List~Categoria~ categorias
        +List~Avaliacao~ avaliacoes
    }
    
    class Filme {
        +Long id
    }
    
    class Serie {
        +Long id
        +List~Episodio~ episodios
    }
    
    class Episodio {
        +Long id
        +String titulo
        +Serie serie
    }
    
    class Categoria {
        +Long id
        +String nome
        +List~Conteudo~ conteudos
    }
    
    class Avaliacao {
        +Long id
        +Integer nota
        +String comentario
        +Perfil perfil
        +Conteudo conteudo
    }

    %% Relacionamentos e Associações
    Usuario "1" -- "1" Assinatura : possui
    Usuario "1" -- "0..*" Perfil : gerencia
    Perfil "1" -- "0..*" Avaliacao : faz
    Conteudo "0..*" -- "0..*" Categoria : pertence_a
    Conteudo "1" -- "0..*" Avaliacao : recebe
    Serie "1" -- "0..*" Episodio : contem

    %% Herança (JOINED)
    Conteudo <|-- Filme : herda
    Conteudo <|-- Serie : herda