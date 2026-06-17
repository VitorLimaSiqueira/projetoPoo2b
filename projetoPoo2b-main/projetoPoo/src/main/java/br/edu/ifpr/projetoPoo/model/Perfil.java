package br.edu.ifpr.projetoPoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apelido;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Perfil() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Perfil(Long id, String apelido) {
        this.id = id;
        this.apelido = apelido;
    }
}
