package br.edu.ifpr.projetoPoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "episodio")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    public Episodio() {
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

    public Episodio(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
