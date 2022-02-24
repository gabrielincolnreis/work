package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Address;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AddressForm {

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotNull
    private Long number;

    @NotNull
    private Long addressCode;

    @NotNull
    private Long ownerId;

    private String ville;

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Long getNumber() {
        return number;
    }

    public String getVille() {
        return ville;
    }

    public Long getAddressCode() {
        return addressCode;
    }

    public String getComplement() {
        return complement;
    }

    private String complement;

    public Long getOwnerId() {
        return ownerId;
    }

    public Address converter(UsuarioRepository usuarioRepository){
        Usuario ownerUser = usuarioRepository.getById(ownerId);
        return new Address(country,city, street, number,ville,addressCode,complement,ownerUser) ;

    }
}
