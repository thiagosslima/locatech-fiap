package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.services.VehiclesServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);
    private final VehiclesServices vehiclesServices;

    public VehicleController(VehiclesServices vehiclesServices) {
        this.vehiclesServices = vehiclesServices;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAllVehicles(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("GET /vehicles - With page: {} and size: {}", page, size);
        var vehicles = vehiclesServices.findAllVehicles(page, size);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vehicle>> findVehicleById(
            @PathVariable("id") Long id
    ) {
        log.info("GET /vehicles/{}", id);
        var vehicle = vehiclesServices.findVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping
    public ResponseEntity<Void> saveVehicle(
            @RequestBody Vehicle vehicle
    ) {
        log.info("POST /vehicles - Vehicle: {}", vehicle);
        vehiclesServices.saveVehicle(vehicle);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(
            @PathVariable("id") Long id,
            @RequestBody Vehicle vehicle
    ) {
        log.info("PUT /vehicles/{}", id);
        vehiclesServices.updateVehicle(vehicle, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(
            @PathVariable("id") Long id
    ) {
        log.info("DELETE /vehicles/{}", id);
        vehiclesServices.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }
}
