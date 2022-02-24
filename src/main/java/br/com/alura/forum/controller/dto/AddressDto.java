package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Address;
import br.com.alura.forum.modelo.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public class AddressDto {
    private final Long id;
    private final String country;
    private final String city;
    private final String street;
    private final Long number;
    private final String ville;
    private final Long addressCode;
    private final String complement;
    private final LocalDateTime dateCreation = LocalDateTime.now();
    private final UserDto owner;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.country = address.getCountry();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.ville = address.getVille();
        this.addressCode = address.getAddressCode();
        this.complement = address.getComplement();
        this.owner = new UserDto(address.getOwner());
    }
    public static Page<AddressDto> converter(Page<Address> addresses){
        return addresses.map(AddressDto::new);
    }

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public UserDto getOwner() {
        return owner;
    }
}
