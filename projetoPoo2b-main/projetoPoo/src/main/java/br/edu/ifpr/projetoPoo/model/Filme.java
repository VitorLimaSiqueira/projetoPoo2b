package br.edu.ifpr.projetoPoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "filme")
public class Filme extends Conteudo{

    public Filme (){}
    
    public Filme(Long id, String titulo) {
        super(id, titulo);
    }

}
