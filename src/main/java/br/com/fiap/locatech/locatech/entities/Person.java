package br.com.fiap.locatech.locatech.entities;

import br.com.fiap.locatech.locatech.dtos.request.PersonRequestDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Person {
    private Long id;
    private String name;
    private String document;
    private String phone;
    private String email;

    public Person(PersonRequestDto dto){
        this.name = dto.name();
        this.document = dto.document();
        this.phone = dto.phone();
        this.email = dto.email();
    }
}
