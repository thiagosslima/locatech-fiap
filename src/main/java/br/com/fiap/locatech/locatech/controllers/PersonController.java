package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Person;
import br.com.fiap.locatech.locatech.services.PersonServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    private final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPerson(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("GET /person - With page: {} and size: {}", page, size);
        var person = personServices.findAllPerson(page, size);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Person>> findPersonById(
            @PathVariable("id") Long id
    ) {
        log.info("GET /person/{}", id);
        var person = personServices.findPersonById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Void> savePerson(
            @RequestBody Person person
    ) {
        log.info("POST /person - Person: {}", person);
        personServices.savePerson(person);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(
            @PathVariable("id") Long id,
            @RequestBody Person person
    ) {
        log.info("PUT /person/{}", id);
        personServices.updatePerson(person, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable("id") Long id
    ) {
        log.info("DELETE /person/{}", id);
        personServices.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}
