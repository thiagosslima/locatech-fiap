package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Rent;

import java.util.List;
import java.util.Optional;

public interface RentRepository {

    Optional<Rent> findById(Long id);

    List<Rent> findAll(int size, int offset);

    Integer save(Rent rent);

    Integer update(Rent rent, Long id);

    Integer delete(Long id);

    boolean existsById(Long vehicleId);
}
