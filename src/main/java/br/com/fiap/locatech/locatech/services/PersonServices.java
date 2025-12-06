package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Person;
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

    public List<Person> findAllPerson(int page, int size) {
        int offset = (page - 1) * size;
        return personRepository.findAll(size, offset);
    }

    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    public void savePerson(Person person) {
        var save = personRepository.save(person);
        Assert.state(save == 1, "Error saving person " + person.getName());
    }

    public void updatePerson(Person person, Long id) {
        var update = personRepository.update(person, id);
        if (update == 0) {
            throw new RuntimeException("Person not found " + person.getId());
        }
    }

    public void deletePerson(Long id) {
        var delete = personRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Person not found " + id);
        }
    }
}
