package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.request.PersonRequestDto;
import br.com.fiap.locatech.locatech.dtos.response.PersonResponseDto;
import br.com.fiap.locatech.locatech.entities.Person;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServices {

    private final PersonRepository personRepository;

    public PersonServices(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponseDto> findAllPerson(int page, int size) {
        int offset = (page - 1) * size;
        var person = personRepository.findAll(size, offset);
        return person.stream()
                .map(PersonResponseDto::new)
                .toList();
    }

    public Optional<PersonResponseDto> findPersonById(Long id) {
        return Optional.ofNullable(personRepository.findById(id)
                .map(PersonResponseDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found " + id)));
    }

    public void savePerson(PersonRequestDto dto) {
        var save = personRepository.save(new Person(dto));
        Assert.state(save == 1, "Error saving person " + dto.name());
    }

    public void updatePerson(PersonRequestDto dto, Long id) {
        var update = personRepository.update(new Person(dto), id);
        if (update == 0) {
            throw new ResourceNotFoundException("Person not found " + id);
        }
    }

    public void deletePerson(Long id) {
        var delete = personRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Person not found " + id);
        }
    }
}
