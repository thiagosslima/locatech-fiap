package br.com.fiap.locatech.locatech.dtos;

import java.util.List;

public record ValidationErrorDto(List<String> errors, int status) {
}
