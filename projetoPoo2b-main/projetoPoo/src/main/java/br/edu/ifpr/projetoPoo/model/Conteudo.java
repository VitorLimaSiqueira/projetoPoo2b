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
    @OneToMany(mappedBy = "conteudo")
    private List<Avaliacao> avaliacoes;

    @ManyToMany
    @JoinTable(name = "conteudo_categoria", 
            joinColumns = @JoinColumn(name = "conteudo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
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

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

}
