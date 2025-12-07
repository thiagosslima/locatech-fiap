package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.RentRequestDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Rent {
    private Long id;
    private Long personId;
    private Long vehicleId;
    private String modelVehicle;
    private String personDocument;
    private String personName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;

    public Rent(RentRequestDto dto, BigDecimal price) {
        this.personId = dto.personId();
        this.vehicleId = dto.vehicleId();
        this.startDate = dto.startDate();
        this.endDate = dto.endDate();
        this.price = price;
    }
}
