package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.UserDto;
import br.com.alura.forum.controller.form.UpdateUserForm;
import br.com.alura.forum.controller.form.UserForm;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import io.swagger.models.Response;
import org.h2.engine.User;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Cacheable(value = "listUsers")
    public Page<UserDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){

        Page<Usuario> users = usuarioRepository.findAll(pageable);
        return UserDto.converter(users);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listUsers", allEntries = true)
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder){

        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> detail(@PathVariable Long id){

        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(new UserDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listUsers", allEntries = true)
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UpdateUserForm form){

        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            Usuario usuario = form.update(id, usuarioRepository);
            return ResponseEntity.ok(new UserDto(usuario));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listUsers", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
