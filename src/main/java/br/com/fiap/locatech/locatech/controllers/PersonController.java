package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.request.PersonRequestDto;
import br.com.fiap.locatech.locatech.dtos.response.PersonResponseDto;
import br.com.fiap.locatech.locatech.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/person")
@Tag(name = "Person", description = "Controller responsible for person operations - CRUD")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);
    private final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @Operation(
            description = "Find all people with pagination",
            summary = "Find all people with pagination",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping
    public ResponseEntity<List<PersonResponseDto>> findAllPerson(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("GET /person - With page: {} and size: {}", page, size);
        var person = personServices.findAllPerson(page, size);
        return ResponseEntity.ok(person);
    }

    @Operation(
            description = "Find all person by their ID",
            summary = "Find all person by their ID",
            responses = {
                    @ApiResponse(description = "Ok", responseCode = "200")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PersonResponseDto>> findPersonById(
            @PathVariable("id") Long id
    ) {
        log.info("GET /person/{}", id);
        var person = personServices.findPersonById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Void> savePerson(
            @Valid @RequestBody PersonRequestDto dto
    ) {
        log.info("POST /person - Person: {}", dto);
        personServices.savePerson(dto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(
            @PathVariable("id") Long id,
            @Valid @RequestBody PersonRequestDto dto
    ) {
        log.info("PUT /person/{}", id);
        personServices.updatePerson(dto, id);
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
