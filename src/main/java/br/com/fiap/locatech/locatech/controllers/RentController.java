package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.request.RentRequestDto;
import br.com.fiap.locatech.locatech.dtos.response.RentResponseDto;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.services.RentServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/rent")
@Tag(name = "Rent", description = "Controller responsible for rent operations - CRUD")
public class RentController {

    private static final Logger log = LoggerFactory.getLogger(RentController.class);
    private final RentServices rentServices;

    public RentController(RentServices rentServices) {
        this.rentServices = rentServices;
    }

    @GetMapping
    public ResponseEntity<List<RentResponseDto>> findAllRent(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("GET /rent - With page: {} and size: {}", page, size);
        var rent = rentServices.findAllRent(page, size);
        return ResponseEntity.ok(rent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RentResponseDto>> findRentById(
            @PathVariable("id") Long id
    ) {
        log.info("GET /rent/{}", id);
        var rent = rentServices.findRentById(id);
        return ResponseEntity.ok(rent);
    }

    @PostMapping
    public ResponseEntity<Void> saveRent(
            @Valid @RequestBody RentRequestDto rent
    ) {
        log.info("POST /rent - Rent: {}", rent);
        rentServices.saveRent(rent);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRent(
            @PathVariable("id") Long id,
            @RequestBody Rent rent
    ) {
        log.info("PUT /rent/{}", id);
        rentServices.updateRent(rent, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(
            @PathVariable("id") Long id
    ) {
        log.info("DELETE /rent/{}", id);
        rentServices.deleteRent(id);
        return ResponseEntity.ok().build();
    }
}
