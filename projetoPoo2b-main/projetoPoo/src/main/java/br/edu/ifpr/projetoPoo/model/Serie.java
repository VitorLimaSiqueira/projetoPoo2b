package br.edu.ifpr.projetoPoo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "serie")
public class Serie extends Conteudo {
    @OneToMany(mappedBy = "serie")
    private List<Episodio> episodios;

    public Serie() {
    }

    public Serie(Long id, String titulo) {
        super(id, titulo);
    }

}
