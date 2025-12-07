package br.com.fiap.locatech.locatech.dtos.response;

import br.com.fiap.locatech.locatech.entities.Rent;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class RentResponseDto {

    private final Long id;
    private final Long personId;
    private final Long vehicleId;
    private final String modelVehicle;
    private final String personDocument;
    private final String personName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal price;

    public RentResponseDto(Rent rent) {
        this.id = rent.getPersonId();
        this.personId = rent.getPersonId();
        this.vehicleId = rent.getVehicleId();
        this.modelVehicle = rent.getModelVehicle();
        this.personDocument = rent.getPersonDocument();
        this.personName = rent.getPersonName();
        this.startDate = rent.getStartDate();
        this.endDate = rent.getEndDate();
        this.price = rent.getPrice();
    }
}
