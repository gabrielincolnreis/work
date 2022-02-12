package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.UserDto;
import br.com.alura.forum.controller.form.UserForm;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
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
}
