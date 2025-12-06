DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS persons;

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

INSERT INTO vehicles (mark, model, plate, manufacture_year, color, daily_value)
VALUES ('Toyota', 'Corolla', 'ABC-1234', 2020, 'White', 150.00),
       ('Volkswagen', 'Golf', 'DEF-5678', 2018, 'Black', 120.00);

INSERT INTO persons (name, document, phone, email)
VALUES ('Thiago Silva', '123.456.789-00', '+55 11 91234-5678', 'thiago@example.com'),
       ('Maria Souza', '987.654.321-00', '+55 21 99876-5432', 'maria@example.com');
