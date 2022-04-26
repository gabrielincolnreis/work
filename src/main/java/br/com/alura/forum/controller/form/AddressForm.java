package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Address;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.AddressRepository;
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

    private String complement;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(Long addressCode) {
        this.addressCode = addressCode;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Address converter(UsuarioRepository usuarioRepository){
        Usuario ownerUser = usuarioRepository.getById(ownerId);
        return new Address(country,city, street, number,ville,addressCode,complement,ownerUser) ;
    }

    public Address update(Long id, AddressRepository addressRepository){
        Address address = addressRepository.getById(id);

        address.setCountry(this.country);
        address.setCity(this.city);
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setVille(this.ville);
        address.setAddressCode(this.addressCode);
        address.setComplement(this.complement);

        return address;
    }
}
