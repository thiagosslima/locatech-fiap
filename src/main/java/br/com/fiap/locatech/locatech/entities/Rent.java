package br.com.fiap.locatech.locatech.entities;

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
}
