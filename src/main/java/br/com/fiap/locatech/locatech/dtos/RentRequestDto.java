package br.com.fiap.locatech.locatech.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentRequestDto(
        @NotNull(message = "Person ID cannot be null")
        Long personId,
        @NotNull(message = "Vehicle ID cannot be null")
        Long vehicleId,
        @NotNull(message = "Start date cannot be null")
        LocalDate startDate,
        @NotNull(message = "End date cannot be null")
        LocalDate endDate) {
}
