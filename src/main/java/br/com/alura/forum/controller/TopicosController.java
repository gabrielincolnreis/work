package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizarTopicosForm;
import br.com.alura.forum.controller.form.TopicosForm;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listadeTopicos")
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {

        if(nomeCurso != null){
            Page<Topico> topicos = topicosRepository.findByCursoNome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);

        }

        Page<Topico> topicos = topicosRepository.findAll(paginacao);
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listadeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicosForm form, UriComponentsBuilder uriBuilder){

        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){

        Optional<Topico> optional = topicosRepository.findById(id);
        return optional.map(topico -> ResponseEntity.ok(new DetalhesDoTopicoDto(topico))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listadeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarTopicosForm form){

        Optional<Topico> optional = topicosRepository.findById(id);
        if(optional.isPresent()){
            Topico topico = form.atualizar(id,topicosRepository);
            return  ResponseEntity.ok(new TopicoDto(topico));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listadeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){

        Optional<Topico> optional = topicosRepository.findById(id);
        if(optional.isPresent()){
            topicosRepository.deleteById(id);
            return  ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

    }

}