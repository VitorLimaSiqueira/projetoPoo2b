package br.edu.ifpr.projetoPoo.model;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToMany(mappedBy = "categorias")
    private List<Conteudo> conteudos;

    public Categoria() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
