package br.com.fiap.locatech.locatech.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VehicleRequestDto(

        @NotBlank(message = "Mark is required")
        @Size(min = 1, max = 50, message = "Mark must be between 1 and 50 characters")
        String mark,

        @NotBlank(message = "Model is required")
        @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
        String model,

        @NotBlank(message = "Plate is required")
        @Size(min = 1, max = 10, message = "Plate must be between 1 and 10 characters")
        String plate,

        int manufactureYear,

        @Size(min = 1, max = 10, message = "Color must be between 1 and 10 characters")
        String color,

        @Positive(message = "Daily value must be positive")
        BigDecimal dailyValue) {
}
