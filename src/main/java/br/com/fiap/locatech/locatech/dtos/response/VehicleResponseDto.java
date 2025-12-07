package br.com.fiap.locatech.locatech.dtos.response;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class VehicleResponseDto {
    private final Long id;
    private final String mark;
    private final String model;
    private final String plate;
    private final int manufactureYear;
    private final String color;
    private final BigDecimal dailyValue;

    public VehicleResponseDto(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.mark = vehicle.getMark();
        this.model = vehicle.getModel();
        this.plate = vehicle.getPlate();
        this.manufactureYear = vehicle.getManufactureYear();
        this.color = vehicle.getColor();
        this.dailyValue = vehicle.getDailyValue();
    }
}
