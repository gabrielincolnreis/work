package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
