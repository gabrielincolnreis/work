package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Caso eu não use o H2
@ActiveProfiles("test")
class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByNome() {
        String cursoName = "HTML 5";

        Curso html5 = new Curso();
        html5.setNome(cursoName);
        html5.setCategoria("Programacao");
        entityManager.persist(html5);

        Curso curso = repository.findByNome(cursoName);
        Assertions.assertNotNull(curso);
        Assertions.assertEquals(cursoName, curso.getNome());
    }

    @Test
    void notFoundFindByNome(){
        String cursoName = "JPA";
        Curso curso = repository.findByNome(cursoName);
        Assertions.assertNull(curso);
    }

    // Para os testes o Spring assume que você irá utilizar algum banco em memória, como é o caso do H2
}