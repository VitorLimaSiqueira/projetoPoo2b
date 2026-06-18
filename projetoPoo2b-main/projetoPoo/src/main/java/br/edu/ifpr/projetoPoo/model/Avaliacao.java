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
    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    @ManyToOne
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;

    public Avaliacao (){}
    
    public Avaliacao(Long id, Integer nota, String comentario, Perfil perfil, Conteudo conteudo) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.perfil = perfil;
        this.conteudo = conteudo;
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
    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Conteudo getConteudo() {
        return conteudo;
    }
    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

}
