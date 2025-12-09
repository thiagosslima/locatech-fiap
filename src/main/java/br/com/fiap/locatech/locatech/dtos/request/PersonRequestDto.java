package br.com.fiap.locatech.locatech.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record PersonRequestDto(

        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name must be 100 characters")
        String name,

        @CPF(message = "Document must be a valid CPF")
        @NotBlank(message = "Document cannot be blank")
        String document,

        String phone,

        @Email(message = "Email should be valid")
        String email
) {
}
