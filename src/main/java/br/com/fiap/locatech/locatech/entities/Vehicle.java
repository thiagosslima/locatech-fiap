package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.request.VehicleRequestDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vehicle {

    private Long id;
    private String mark;
    private String model;
    private String plate;
    private int manufactureYear;
    private String color;
    private BigDecimal dailyValue;

    public Vehicle(VehicleRequestDto dto) {
        this.mark = dto.mark();
        this.model = dto.model();
        this.plate = dto.plate();
        this.manufactureYear = dto.manufactureYear();
        this.color = dto.color();
        this.dailyValue = dto.dailyValue();
    }
}
