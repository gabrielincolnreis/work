package br.com.alura.forum.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String street;
    private Long number;
    private String ville;
    private Long addressCode;
    private String complement;
    private LocalDateTime dateCreation = LocalDateTime.now();

    @OneToOne
    private Usuario owner;

    public Address() {
    }

    public Address(String country, String city, String street, Long number, String ville, Long addressCode, String complement, Usuario owner) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.ville = ville;
        this.addressCode = addressCode;
        this.complement = complement;
        this.owner = owner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Long getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(Long addressCode) {
        this.addressCode = addressCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }
}
