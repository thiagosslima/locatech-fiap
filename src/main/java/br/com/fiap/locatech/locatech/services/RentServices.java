package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.request.RentRequestDto;
import br.com.fiap.locatech.locatech.dtos.response.RentResponseDto;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.exceptions.EndDateGreaterThanStartDateException;
import br.com.fiap.locatech.locatech.exceptions.IsNotAvailableException;
import br.com.fiap.locatech.locatech.exceptions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.RentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentServices {

    private final RentRepository rentRepository;
    private final VehiclesServices vehiclesServices;
    private final PersonServices personServices;

    public RentServices(RentRepository rentRepository, VehiclesServices vehiclesServices, PersonServices personServices) {
        this.rentRepository = rentRepository;
        this.vehiclesServices = vehiclesServices;
        this.personServices = personServices;
    }

    public List<RentResponseDto> findAllRent(int page, int size) {
        int offset = (page - 1) * size;
        var rent = rentRepository.findAll(size, offset);
        return rent.stream()
                .map(RentResponseDto::new)
                .toList();
    }

    public Optional<RentResponseDto> findRentById(Long id) {
        return Optional.ofNullable(rentRepository.findById(id)
                .map(RentResponseDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Rent not found " + id)));
    }

    public void saveRent(RentRequestDto rent) {
        if (isAvailableForRent(rent.vehicleId(), rent.startDate(), rent.endDate())) {
            var rentEntity = calculateRentValue(rent);
            var save = rentRepository.save(rentEntity);
            Assert.state(save == 1, "Error saving rent " + rent.personId());
        } else {
            throw new IsNotAvailableException("Vehicle not available for rent " + rent.vehicleId());
        }
    }

    public void updateRent(Rent rent, Long id) {
        var update = rentRepository.update(rent, id);
        if (update == 0) {
            throw new ResourceNotFoundException("Rent not found " + rent.getId());
        }
    }

    public void deleteRent(Long id) {
        var delete = rentRepository.delete(id);
        if (delete == 0) {
            throw new ResourceNotFoundException("Rent not found " + id);
        }
    }

    private Rent calculateRentValue(RentRequestDto dto) {
        validateEndDateGreaterThanStartDate(dto);

        personServices.findPersonById(dto.personId()).orElseThrow();
        var vehicle = vehiclesServices.findVehicleById(dto.vehicleId()).orElseThrow();
        var quantityOfDays = BigDecimal.valueOf(dto.endDate().getDayOfYear() - dto.startDate().getDayOfYear());
        var price = vehicle.getDailyValue().multiply(quantityOfDays);
        return new Rent(dto, price);
    }

    private void validateEndDateGreaterThanStartDate(RentRequestDto dto) {
        if (dto.endDate().isBefore(dto.startDate())) {
            throw new EndDateGreaterThanStartDateException("End date must be after start date");
        }
    }

    public boolean isAvailableForRent(Long vehicleId, LocalDate startDate, LocalDate endDate) {
        return !rentRepository.existsByIdAndBetweenStartDateAndEndDate(vehicleId, startDate, endDate);
    }
}
