package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nome);
}

// Aqui estamos utilizando o Spring Data, camada de persistencia, faz a integração com o banco de dados utilizando a JPA
