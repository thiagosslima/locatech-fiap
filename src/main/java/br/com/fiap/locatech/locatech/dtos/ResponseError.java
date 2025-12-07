package br.com.fiap.locatech.locatech.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseError(List<String> message, HttpStatus httpStatus, LocalDateTime time) {
}
