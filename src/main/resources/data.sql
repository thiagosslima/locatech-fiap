DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS rent;

CREATE TABLE vehicles
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    mark             VARCHAR(50),
    model            VARCHAR(50),
    plate            VARCHAR(10),
    manufacture_year INT,
    color            VARCHAR(30),
    daily_value      DECIMAL(10, 2)
);

CREATE TABLE persons
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    document VARCHAR(30),
    phone    VARCHAR(30),
    email    VARCHAR(100)
);

CREATE TABLE rent
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id  BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,
    start_date DATE   NOT NULL,
    end_date   DATE,
    price      DECIMAL(10, 2),
    FOREIGN KEY (person_id) REFERENCES persons (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);

INSERT INTO vehicles (mark, model, plate, manufacture_year, color, daily_value)
VALUES ('Toyota', 'Corolla', 'ABC-1234', 2020, 'White', 150.00),
       ('Volkswagen', 'Golf', 'DEF-5678', 2018, 'Black', 120.00),
       ('Honda', 'Civic', 'GHI-9012', 2019, 'Blue', 130.00),
       ('Ford', 'Ka', 'JKL-3456', 2017, 'Red', 90.00),
       ('Chevrolet', 'Onix', 'MNO-7890', 2021, 'Silver', 140.00);

INSERT INTO persons (name, document, phone, email)
VALUES ('Thiago Silva', '123.456.789-00', '+55 11 91234-5678', 'thiago@example.com'),
       ('Maria Souza', '987.654.321-00', '+55 21 99876-5432', 'maria@example.com'),
       ('Carlos Pereira', '111.222.333-44', '+55 31 91234-0000', 'carlos@example.com'),
       ('Ana Lima', '222.333.444-55', '+55 41 99876-0000', 'ana@example.com'),
       ('Bruno Rocha', '333.444.555-66', '+55 61 99777-0000', 'bruno@example.com');


INSERT INTO rent (person_id, vehicle_id, start_date, end_date, price)
VALUES (1, 1, DATE '2024-01-10', DATE '2024-01-15', 750.00),
       (2, 2, DATE '2024-02-01', DATE '2024-02-05', 480.00),
       (1, 2, DATE '2025-06-01', NULL, 300.00),
       (3, 3, DATE '2024-03-10', DATE '2024-03-12', 260.00),
       (4, 4, DATE '2024-04-05', DATE '2024-04-10', 450.00),
       (5, 5, DATE '2025-07-01', DATE '2025-07-03', 280.00),
       (2, 3, DATE '2025-08-15', NULL, 390.00),
       (1, 5, DATE '2025-09-01', DATE '2025-09-05', 700.00);


