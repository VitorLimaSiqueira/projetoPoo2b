package br.edu.ifpr.projetoPoo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "conteudo")
@Inheritance(strategy = InheritanceType.JOINED)

public class Conteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToMany
    @JoinTable(name = "conteudo_categoria", // Nome da tabela intermediária que o MySQL vai criar
            joinColumns = @JoinColumn(name = "conteudo_id"), // Chave primária de Conteudo
            inverseJoinColumns = @JoinColumn(name = "categoria_id") // Chave primária de Categoria
    )
    private List<Categoria> categorias;

    public Conteudo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Conteudo(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

}
