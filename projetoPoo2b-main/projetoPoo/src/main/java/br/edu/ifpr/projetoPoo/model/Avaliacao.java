package br.edu.ifpr.projetoPoo.model;
import jakarta.persistence.*;
@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nota;
    private String comentario;

    public Avaliacao (){}
    
    public Avaliacao(Long id, Integer nota, String comentario) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
