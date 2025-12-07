package br.com.fiap.locatech.locatech.dtos.response;

import br.com.fiap.locatech.locatech.entities.Person;
import lombok.Getter;

@Getter
public class PersonResponseDto {

    private final Long id;
    private final String name;
    private final String document;
    private final String phone;
    private final String email;

    public PersonResponseDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.document = person.getDocument();
        this.phone = person.getDocument();
        this.email = person.getEmail();
    }

}