package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.RentRequestDto;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.repositories.RentRepository;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import br.com.fiap.locatech.locatech.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RentServices {

    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;

    public RentServices(RentRepository rentRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Rent> findAllRent(int page, int size) {
        int offset = (page - 1) * size;
        return rentRepository.findAll(size, offset);
    }

    public Optional<Rent> findRentById(Long id) {
        return Optional.ofNullable(rentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found " + id)));
    }

    public void saveRent(RentRequestDto rent) {
        var rentEntity = calculateRentValue(rent);
        var save = rentRepository.save(rentEntity);
        Assert.state(save == 1, "Error saving rent " + rent.personId());
    }

    public void updateRent(Rent rent, Long id) {
        var update = rentRepository.update(rent, id);
        if (update == 0) {
            throw new RuntimeException("Rent not found " + rent.getId());
        }
    }

    public void deleteRent(Long id) {
        var delete = rentRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Rent not found " + id);
        }
    }

    private Rent calculateRentValue(RentRequestDto dto) {
        var vehicle = vehicleRepository.findById(dto.vehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found " + dto.vehicleId()));

        var quantityOfDays = BigDecimal.valueOf(dto.endDate().getDayOfYear() - dto.startDate().getDayOfYear());
        var price = vehicle.getDailyValue().multiply(quantityOfDays);
        return new Rent(dto, price);
    }
}
