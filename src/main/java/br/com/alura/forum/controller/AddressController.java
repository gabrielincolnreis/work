package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.AddressDto;
import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.UserDto;
import br.com.alura.forum.controller.form.AddressForm;
import br.com.alura.forum.controller.form.UpdateUserForm;
import br.com.alura.forum.controller.form.UserForm;
import br.com.alura.forum.modelo.Address;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.AddressRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Cacheable(value = "listAddress")
    public Page<AddressDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable pageable){

        Page<Address> addresses = addressRepository.findAll(pageable);
        return AddressDto.converter(addresses);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listAddress", allEntries = true)
    public ResponseEntity<AddressDto> save(@RequestBody @Valid AddressForm form, UriComponentsBuilder uriBuilder){

        Address address = form.converter(usuarioRepository);
        addressRepository.save(address);

        URI uri = uriBuilder.path("/address/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(new AddressDto(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> detail(@PathVariable Long id){

        Optional<Address> optional = addressRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(new AddressDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listAddress", allEntries = true)
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody @Valid AddressForm form){

        Optional<Address> optional = addressRepository.findById(id);
        if(optional.isPresent()){
            Address address = form.update(id, addressRepository);
            return ResponseEntity.ok(new AddressDto(address));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listAddress", allEntries = true)
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Address> optional = addressRepository.findById(id);
        if(optional.isPresent()){
            addressRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


}
