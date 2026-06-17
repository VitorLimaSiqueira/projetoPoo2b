package br.edu.ifpr.projetoPoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assinatura")
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToOne(mappedBy = "assinatura")     
    private Usuario usuario;

    public Assinatura() {
    }

    public Assinatura(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
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
}
