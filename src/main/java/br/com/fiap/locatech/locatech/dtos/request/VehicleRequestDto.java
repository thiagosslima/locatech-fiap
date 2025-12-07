package br.com.fiap.locatech.locatech.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record VehicleRequestDto(
        @NotBlank(message = "Mark is required")
        String mark,
        @NotBlank(message = "Model is required")
        String model,
        @NotBlank(message = "Plate is required")
        String plate,
        int manufactureYear,
        String color,
        @NotBlank(message = "Daily value is required")
        BigDecimal dailyValue) {
}
