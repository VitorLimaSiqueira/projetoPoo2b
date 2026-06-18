package br.edu.ifpr.projetoPoo;

import br.edu.ifpr.projetoPoo.model.*;
import br.edu.ifpr.projetoPoo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;

@SpringBootApplication
public class ProjetoPooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPooApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioRepository userRepo, ConteudoRepository conteudoRepo, AssinaturaRepository assinaRepo) {
		return (args) -> {
			Assinatura premium = new Assinatura(null, "Premium Ultra 4K");
			assinaRepo.save(premium);

			Usuario usuario = new Usuario(null, "Guilherme", "gui@email.com");
			usuario.setAssinatura(premium);
			userRepo.save(usuario);

			Filme filme = new Filme(null, "Inception");
			Serie serie = new Serie(null, "Stranger Things");
			
			conteudoRepo.save(filme);
			conteudoRepo.save(serie);

			System.out.println("=== Dados de teste inseridos com sucesso! ===");
		};
	}
}