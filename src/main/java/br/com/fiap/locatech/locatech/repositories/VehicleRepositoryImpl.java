package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    private final JdbcClient jdbcClient;

    public VehicleRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM VEHICLES WHERE ID = :id")
                .param("id", id)
                .query(Vehicle.class)
                .optional();
    }

    @Override
    public List<Vehicle> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM vehicles LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Vehicle.class)
                .list();
    }

    @Override
    public Integer save(Vehicle vehicle) {
        return this.jdbcClient
                .sql("INSERT INTO vehicles (mark, model, plate, manufacture_year, color, daily_value) VALUES (:mark, :model, :plate, :manufactureYear, :color, :dailyValue)")
                .param("mark", vehicle.getMark())
                .param("model", vehicle.getModel())
                .param("plate", vehicle.getPlate())
                .param("manufactureYear", vehicle.getManufactureYear())
                .param("color", vehicle.getColor())
                .param("dailyValue", vehicle.getDailyValue())
                .update();
    }

    @Override
    public Integer update(Vehicle vehicle, Long id) {
        return this.jdbcClient
                .sql("UPDATE vehicles SET mark = :mark, model = :model, plate = :plate, manufacture_year = :manufactureYear, color = :color, daily_value = :dailyValue WHERE id = :id")
                .param("mark", vehicle.getMark())
                .param("model", vehicle.getModel())
                .param("plate", vehicle.getPlate())
                .param("manufactureYear", vehicle.getManufactureYear())
                .param("color", vehicle.getColor())
                .param("dailyValue", vehicle.getDailyValue())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM vehicles WHERE id = :id")
                .param("id", id)
                .update();
    }
}
