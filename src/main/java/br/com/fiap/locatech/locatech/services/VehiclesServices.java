package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.request.VehicleRequestDto;
import br.com.fiap.locatech.locatech.dtos.response.VehicleResponseDto;
import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
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

    public List<VehicleResponseDto> findAllVehicles(int page, int size) {
        int offset = (page - 1) * size;
        var vehicle = vehicleRepository.findAll(size, offset);
        return vehicle.stream()
                .map(VehicleResponseDto::new)
                .toList();
    }

    public Optional<VehicleResponseDto> findVehicleById(Long id) {
        return Optional.ofNullable(vehicleRepository.findById(id)
                .map(VehicleResponseDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found " + id)));
    }

    public void saveVehicle(VehicleRequestDto dto) {
        var save = vehicleRepository.save(new Vehicle(dto));
        Assert.state(save == 1, "Error saving vehicle " + dto.model());
    }

    public void updateVehicle(VehicleRequestDto dto, Long id) {
        var update = vehicleRepository.update(new Vehicle(dto), id);
        if (update == 0) {
            throw new ResourceNotFoundException("Vehicle not found " + id);
        }
    }

    public void deleteVehicle(Long id) {
        var delete = vehicleRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Vehicle not found " + id);
        }
    }
}
