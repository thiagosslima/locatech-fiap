package br.com.fiap.locatech.locatech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentRequestDto(
        @Schema(description = "Id of the person renting the vehicle", example = "1")
        @NotNull(message = "Person ID cannot be null")
        Long personId,
        @NotNull(message = "Vehicle ID cannot be null")
        Long vehicleId,
        @NotNull(message = "Start date cannot be null")
        LocalDate startDate,
        @NotNull(message = "End date cannot be null")
        LocalDate endDate) {
}
