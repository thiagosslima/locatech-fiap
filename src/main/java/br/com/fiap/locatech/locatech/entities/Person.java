package br.com.fiap.locatech.locatech.entities;

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
}
