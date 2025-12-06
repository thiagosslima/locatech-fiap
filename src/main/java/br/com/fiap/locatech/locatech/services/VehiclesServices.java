package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesServices {

    private final VehicleRepository vehicleRepository;

    public VehiclesServices(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllVehicles(int page, int size) {
        int offset = (page - 1) * size;
        return vehicleRepository.findAll(size, offset);
    }

    public Optional<Vehicle> findVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public void saveVehicle(Vehicle vehicle) {
        var save = vehicleRepository.save(vehicle);
        Assert.state(save == 1, "Error saving vehicle " + vehicle.getModel());
    }

    public void updateVehicle(Vehicle vehicle, Long id) {
        var update = vehicleRepository.update(vehicle, id);
        if (update == 0) {
            throw new RuntimeException("Vehicle not found " + vehicle.getModel());
        }
    }

    public void deleteVehicle(Long id) {
        var delete = vehicleRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Vehicle not found " + id);
        }
    }
}
