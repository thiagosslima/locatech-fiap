package br.com.fiap.locatech.locatech.dtos.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PersonRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @CPF(message = "Document must be a valid CPF")
        @NotBlank(message = "Document cannot be blank")
        String document,
        String phone,
        String email
) {
}
