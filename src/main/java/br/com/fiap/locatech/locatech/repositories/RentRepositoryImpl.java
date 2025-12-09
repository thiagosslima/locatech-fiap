package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Rent;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class RentRepositoryImpl implements RentRepository {

    private final JdbcClient jdbcClient;

    public RentRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Rent> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT r.id, r.person_id, r.vehicle_id, r.start_date, r.end_date, r.price, " +
                        "       p.name AS person_name, p.document AS person_document,  " +
                        "       v.model AS model_vehicle " +
                        "  FROM rent AS r " +
                        " INNER JOIN persons AS p ON r.person_id = p.id " +
                        " INNER JOIN vehicles AS v ON r.vehicle_id = v.id " +
                        " WHERE r.id = :id ")
                .param("id", id)
                .query(Rent.class)
                .optional();
    }

    @Override
    public List<Rent> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT r.id, r.person_id, r.vehicle_id, r.start_date, r.end_date, r.price, " +
                        "       p.name AS person_name, p.document AS person_document,  " +
                        "       v.model AS model_vehicle " +
                        "  FROM rent AS r " +
                        " INNER JOIN persons AS p ON r.person_id = p.id " +
                        " INNER JOIN vehicles AS v ON r.vehicle_id = v.id " +
                        " LIMIT :size OFFSET :offset ")
                .param("size", size)
                .param("offset", offset)
                .query(Rent.class)
                .list();
    }

    @Override
    public Integer save(Rent rent) {
        return this.jdbcClient
                .sql("INSERT INTO rent (person_id, vehicle_id, start_date, end_date, price) VALUES (:person_id, :vehicle_id, :start_date, :end_date, :price)")
                .param("person_id", rent.getPersonId())
                .param("vehicle_id", rent.getVehicleId())
                .param("start_date", rent.getStartDate())
                .param("end_date", rent.getEndDate())
                .param("price", rent.getPrice())
                .update();
    }

    @Override
    public Integer update(Rent rent, Long id) {
        return this.jdbcClient
                .sql("UPDATE rent SET person_id = :person_id, vehicle_id = :vehicle_id, start_date = :start_date, end_date = :end_date, price = :price WHERE id = :id")
                .param("person_id", rent.getPersonId())
                .param("vehicle_id", rent.getVehicleId())
                .param("start_date", rent.getStartDate())
                .param("end_date", rent.getEndDate())
                .param("price", rent.getPrice())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM rents WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean existsByIdAndBetweenStartDateAndEndDate(Long vehicleId, LocalDate startDate, LocalDate endDate) {
        return this.jdbcClient
                .sql("SELECT 1 " +
                        "FROM rent " +
                        "WHERE vehicle_id = :vehicleId " +
                        "  AND (start_date BETWEEN :startDate AND :endDate " +
                        "         OR end_date BETWEEN :startDate AND :endDate)" +
                        "LIMIT 1")
                .param("vehicleId", vehicleId)
                .param("startDate", startDate)
                .param("endDate", endDate)
                .query(Integer.class)
                .optional()
                .isPresent();
    }
}
