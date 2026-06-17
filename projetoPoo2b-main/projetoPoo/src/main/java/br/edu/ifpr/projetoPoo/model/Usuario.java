package br.edu.ifpr.projetoPoo.model;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_usuario")
    private String nome;
    private String email;

    @OneToOne
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    @OneToMany(mappedBy = "usuario")
    private List<Perfil> perfis;

    public Usuario() {
    }

    public Usuario(Long idLong, String name, String email) {
        this.id = idLong;
        this.nome = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idLong) {
        this.id = idLong;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
