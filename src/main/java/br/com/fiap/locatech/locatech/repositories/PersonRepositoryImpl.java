package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Person;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final JdbcClient jdbcClient;

    public PersonRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM persons WHERE ID = :id")
                .param("id", id)
                .query(Person.class)
                .optional();
    }

    @Override
    public List<Person> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM persons LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Person.class)
                .list();
    }

    @Override
    public Integer save(Person person) {
        return this.jdbcClient
                .sql("INSERT INTO persons (name, document, phone, email) VALUES (:name, :document, :phone, :email)")
                .param("name", person.getName())
                .param("document", person.getDocument())
                .param("phone", person.getPhone())
                .param("email", person.getEmail())
                .update();
    }

    @Override
    public Integer update(Person person, Long id) {
        return this.jdbcClient
                .sql("UPDATE persons SET name =:name, document =:document, phone =:phone, email = :email WHERE id = :id")
                .param("name", person.getName())
                .param("document", person.getDocument())
                .param("phone", person.getPhone())
                .param("email", person.getEmail())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM persons WHERE id = :id")
                .param("id", id)
                .update();
    }
}
